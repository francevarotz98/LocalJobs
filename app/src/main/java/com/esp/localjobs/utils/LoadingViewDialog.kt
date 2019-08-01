package com.esp.localjobs.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.esp.localjobs.R

class LoadingViewDialog(private val activity: Activity) {
    private lateinit var dialog: Dialog

    fun showDialog() {
        dialog = Dialog(activity).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.loading)
        }

        val gifImageView: ImageView = dialog.findViewById(R.id.loading_gif)
        Glide.with(activity)
            .asGif()
            .load(R.drawable.loading)
            .placeholder(R.drawable.loading)
            .centerCrop()
            .into(gifImageView)
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }
}
