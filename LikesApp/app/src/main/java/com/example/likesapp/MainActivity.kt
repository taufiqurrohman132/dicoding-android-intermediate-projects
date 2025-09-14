package com.example.likesapp

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.likesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val canvas = Canvas(mBitmap)
    private val mPaint = Paint()

    private val halfOfWidth = (mBitmap.width/2).toFloat()
    private val halfOfHeight = (mBitmap.height/2).toFloat()

    private val left = 150f
    private val top = 250f
    private val right = mBitmap.width - left
    private val bottom = mBitmap.height.toFloat() - 50f

    private val message = "Apakah kamu suka bermain?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setImageBitmap(mBitmap)
        showText()

        binding.like.setOnClickListener {
            showEars()
            showFace()
            showMouth(true)
            showEyes()
            showNose()
            showHair()
        }

        binding.dislike.setOnClickListener {
            showFace()
            showMouth(false)
            showEyes()
        }


    }

    // wajah
    private fun showFace(){
        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        canvas.drawArc(face, 90f, 180f, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        canvas.drawArc(face, 270f, 180f, false, mPaint)

    }

    // mata
    private fun showEyes(){
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 100f, halfOfHeight - 10f, 50f,mPaint)
        canvas.drawCircle(halfOfWidth + 100f, halfOfHeight - 10f, 50f,mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        canvas.drawCircle(halfOfWidth - 120f, halfOfHeight - 20f, 15f, mPaint)
        canvas.drawCircle(halfOfWidth + 80f, halfOfHeight - 20f, 15f, mPaint)
    }

    /// mulut
    private fun showMouth(isHappy: Boolean){
        when(isHappy){
            true -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(
                    halfOfWidth - 200f,
                    halfOfHeight - 100f,
                    halfOfWidth+ 200f,
                    halfOfHeight + 400f
                )
                canvas.drawArc(lip, 25f, 130f, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(
                    halfOfWidth - 180f,
                    halfOfHeight,
                    halfOfWidth +180f,
                    halfOfHeight + 380f
                )
                canvas.drawArc(mouth, 25f, 130f, false, mPaint)
            }
            false ->{
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight + 250F, halfOfWidth + 200F, halfOfHeight + 350F)
                canvas.drawArc(lip, 0F, -180F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight + 260F, halfOfWidth + 180F, halfOfHeight + 330F)
                canvas.drawArc(mouth, 0F, -180F, false, mPaint)
            }
        }
    }

    //  Agar layar tidak terlihat kosong, mari kita tambahkan terlebih dahulu pertanyaan yang sekiranya mendapatkan jawaban ya dan tidak.
    private fun showText(){
        val mPaintText = Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50f
            color = ResourcesCompat.getColor(resources, R.color.black, null)
        }

        val mBounds = Rect()
        mPaintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halfOfWidth - mBounds.centerX()
        val y = 50f
        canvas.drawText(message, x, y, mPaintText)
    }

    // hidung
    private fun showNose(){
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle(halfOfWidth - 40f, halfOfHeight + 140f, 15f, mPaint)
        canvas.drawCircle(halfOfWidth + 40f, halfOfHeight + 140f, 15f, mPaint)
    }

    // telinga
    private fun showEars(){
        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 100F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 100F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.red_ear, null)
        canvas.drawCircle(halfOfWidth - 300F, halfOfHeight - 100F, 60F, mPaint)
        canvas.drawCircle(halfOfWidth + 300F, halfOfHeight - 100F, 60F, mPaint)
    }

    private fun showHair(){
        canvas.save()

        val path = Path()

        path.addCircle(halfOfWidth - 100F,halfOfHeight - 10F, 150F, Path.Direction.CCW)
        path.addCircle(halfOfWidth + 100F,halfOfHeight - 10F, 150F, Path.Direction.CCW)

        val mouth = RectF(halfOfWidth - 250F, halfOfHeight, halfOfWidth + 250F, halfOfHeight + 500F)
        path.addOval(mouth, Path.Direction.CCW)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }

        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_left_hair, null)
        canvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.brown_right_hair, null)
        canvas.drawArc(face, 270F, 180F, false, mPaint)

        canvas.restore()
    }
}