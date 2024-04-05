package com.coderbychance.sher_cheeta

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer

class ChargerReceiver: BroadcastReceiver() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action == Intent.ACTION_POWER_CONNECTED) {
            // Charger connected
            if (mediaPlayer == null || !mediaPlayer!!.isPlaying) {
                mediaPlayer?.release() // Release any existing MediaPlayer
                mediaPlayer = MediaPlayer.create(context, R.raw.sher)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    // Release MediaPlayer once music is finished
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            }
        } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
            // Charger disconnected
            if (mediaPlayer == null || !mediaPlayer!!.isPlaying) {
                mediaPlayer?.release() // Release any existing MediaPlayer
                mediaPlayer = MediaPlayer.create(context, R.raw.cheeta)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    // Release MediaPlayer once music is finished
                    mediaPlayer?.release()
                    mediaPlayer = null
                }
            }
        }
    }
}