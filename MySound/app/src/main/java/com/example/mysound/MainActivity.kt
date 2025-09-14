package com.example.mysound

import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var sp: SoundPool
    private var soundId: Int = 0
    private var spLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSound = findViewById<Button>(R.id.btn_sound_pool_play)
        val btnSwitch = findViewById<Button>(R.id.btn_swict)

        sp = SoundPool.Builder()
            .setMaxStreams(10)
            .build()

        sp.setOnLoadCompleteListener { _, _, status ->
            if (status == 0){
                spLoaded = true
            }else
                Toast.makeText(this@MainActivity, "Gagal load", Toast.LENGTH_SHORT).show()

        }

        soundId = sp.load(this, R.raw.clinking_glassesl, 1)

        btnSound.setOnClickListener {
            if (spLoaded)
                sp.play(soundId, 1f, 1f, 0, 0, 1f)
        }

        btnSwitch.setOnClickListener {
            val intent = Intent(this, MediaPlayerActivity::class.java)
            startActivity(intent)
        }
    }
}