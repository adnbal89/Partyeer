package com.partyeer.presentation.ui.main.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

object IntentUtil {
    val formatter = SimpleDateFormat("yyyy_MM_dd", Locale.getDefault())
    val now = Date()
    val fileName = formatter.format(now)
    var storage: FirebaseStorage = FirebaseStorage.getInstance()
    var storageRef = storage.getReference("images/${fileName}")

    fun uploadImage(activity: Activity) {

    }

    fun shareImage(activity: Activity, uri: Uri?) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(Intent.EXTRA_STREAM, uri)

        }
        storageRef.putFile(uri!!).addOnSuccessListener {
            Toast.makeText(activity, "Storage used", Toast.LENGTH_LONG)
        }.addOnFailureListener {
            Toast.makeText(activity, it.localizedMessage, Toast.LENGTH_LONG)

        }
        activity.startActivity(intent)
    }

}