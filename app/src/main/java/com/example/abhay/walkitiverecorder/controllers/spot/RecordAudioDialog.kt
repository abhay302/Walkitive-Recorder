package com.example.abhay.walkitiverecorder.controllers.spot

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.abhay.walkitiverecorder.R
import kotlinx.android.synthetic.main.dialog_record_audio.*
import kotlinx.android.synthetic.main.dialog_record_audio.view.*
import java.util.*

class RecordAudioDialog : DialogFragment() {
    private var timerTask: TimerTask? = null
    var minute: Int = 0
    var second: Int = 0
    //var file = File((arguments?.get("context") as Context).filesDir,    "audio")
    /*private val mediaRecorder: MediaRecorder = MediaRecorder().apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        prepare()
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_record_audio, container, false)

        view.audio_record_start_button.setOnClickListener {
            minute = 0
            second = 0
            Handler(Looper.getMainLooper()).post {
                //mediaRecorder.start()
            }
            timerTask = object : TimerTask() {
                override fun run() {
                    second++
                    if (second >= 60) {
                        minute++
                        second = 0
                    }
                    Handler(Looper.getMainLooper()).post {
                        record_audio_timer.text = ((if (minute / 10 == 0) "0" else "").plus(minute)).plus(":")
                                .plus((if (second / 10 == 0) "0" else "").plus(second))
                    }
                }
            }
            Timer().schedule(timerTask, 1000, 1000)
            view.audio_record_start_button.visibility = View.GONE
        }

        view.audio_record_stop_button.setOnClickListener {
            timerTask?.cancel()
            //mediaRecorder.stop()
        }
        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        Log.d("audio", "dialog created")
        return dialog
    }

    override fun onCancel(dialog: DialogInterface?) {
        timerTask?.cancel()
        super.onCancel(dialog)
        Log.d("audio1", "cancel")
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        Log.d("audio2", "dismiss")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}