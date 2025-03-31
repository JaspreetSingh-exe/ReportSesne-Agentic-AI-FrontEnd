package com.example.reportsesneagenticai

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class SignInActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        videoView = findViewById(R.id.videoView)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.signin_intro_video}")
        videoView.setVideoURI(videoUri)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.setVolume(0f, 0f)
            videoView.start()
        }

        // Handle delay between loops
        videoView.setOnCompletionListener {
            handler.postDelayed({
                videoView.start()
            }, 10_000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Prevent memory leaks
    }
}