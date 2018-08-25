package com.example.abhay.walkitiverecorder.controllers.spot

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.abhay.walkitiverecorder.R
import com.example.abhay.walkitiverecorder.api.RetrofitClient
import com.example.abhay.walkitiverecorder.models.ErrorReply
import com.example.abhay.walkitiverecorder.models.Spot
import com.example.abhay.walkitiverecorder.views.ProgressBarView
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_add_spot.*
import kotlinx.android.synthetic.main.toolbar_add_spot.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val REQUEST_IMAGE = 200

class AddSpotActivity : AppCompatActivity() {

    lateinit var address: String
    lateinit var location: LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_spot)

        run {
            add_spot_name.setText("a spot")
        }

        if (intent.extras != null) {
            location = intent.extras?.get("location") as? LatLng ?: LatLng(0.0, 0.0)
            address = intent.extras?.get("address") as? String ?: ""
            add_spot_toolbar.display_location_address.text = address
        }

        with(add_spot_toolbar.action_button) {
            setImageResource(R.drawable.cancel)
            setOnClickListener {
                this@AddSpotActivity.supportFinishAfterTransition()
            }
        }

        setClickListeners()
    }

    private fun setClickListeners() {
        add_image_button.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val galleryIntent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            val chooserIntent = Intent.createChooser(galleryIntent, "Select")

            if (intent.resolveActivity(packageManager) != null) {
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
            }
            startActivityForResult(chooserIntent, REQUEST_IMAGE)
        }

        add_audio_button.setOnClickListener {
            val recordAudioDialog = RecordAudioDialog()
            recordAudioDialog.apply {
                isCancelable = true
                show(supportFragmentManager, "progress bar")
            }
        }

        send_button.setOnClickListener { _ ->
            val spot = Spot().apply {
                name = add_spot_name.text.toString().takeIf { it.isNotBlank() }
                latitude = location.latitude.toFloat()
                longitude = location.longitude.toFloat()
                category_id = 1
                address = this@AddSpotActivity.address.takeIf { it.isNotBlank() }
            }
            sendAddSpotRequest(spot)
        }

    }

    private fun sendAddSpotRequest(spot: Spot) {
        val call = RetrofitClient.client.addSpot(spot)

        val progressBar = ProgressBarView()
        progressBar.apply {
            isCancelable = false
            show(supportFragmentManager, "progress bar")
        }

        call.enqueue(object : Callback<JsonElement> {

            override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
                progressBar.dismiss()
                when (response?.code()) {
                    200 -> {
                        Log.d("success", response.body().toString())
                        this@AddSpotActivity.supportFinishAfterTransition()
                    }
                    422 -> {
                        Log.d("error", response.errorBody()?.string())
                        var error = Gson().fromJson(response.errorBody()?.string(), ErrorReply::class.java)
                    }
                    else -> Toast.makeText(this@AddSpotActivity, "Error occurred", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
                progressBar.dismiss()
                Toast.makeText(this@AddSpotActivity, "Error occurred", Toast.LENGTH_LONG).show()
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            image_cancel_button.visibility = View.VISIBLE
            add_image_button.setImageResource(R.drawable.add_image_btn_pressed)

            image_cancel_button.setOnClickListener {
                add_image_button.setImageResource(R.drawable.add_image_btn)
                image_cancel_button.visibility = View.GONE
            }
        }
    }
}