package com.dicoding.mystudentdata.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dicoding.mystudentdata.helper.InitialDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Student::class, University::class, Course::class, CourseStudentCrossRef::class],
    version = 5,
    autoMigrations = [
        AutoMigration(from = 3, to = 4),
        AutoMigration(from = 4, to = 5, spec = StudentDatabase.MyAutoMigration::class),
                     ],
    exportSchema = true // syarat untuk bisa migrasi
)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    @RenameColumn(tableName = "Student", fromColumnName = "graduate", toColumnName = "isGraduate")
    class MyAutoMigration : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: StudentDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): StudentDatabase {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            StudentDatabase::class.java, "student_databases")
                        .fallbackToDestructiveMigration()
                        .createFromAsset("student_databases.db")
//                        .addCallback(object : Callback(){
//                            override fun onCreate(db: SupportSQLiteDatabase) {
//                                super.onCreate(db)
//                                INSTANCE?.let { dataBase ->
//                                    applicationScope.launch {
//                                        val studentDao = dataBase.studentDao()
//                                        studentDao.insertStudent(InitialDataSource.getStudents())
//                                        studentDao.insertUniversity(InitialDataSource.getUniversities())
//                                        studentDao.insertCourse(InitialDataSource.getCourses())
//                                        studentDao.insertCourseStudentCrossRef(InitialDataSource.getCourseStudentRelation())
//                                    }
//
//                                }
//                            }
//                        })
                        .build()
                }
            }
            return INSTANCE as StudentDatabase
        }

    }
}