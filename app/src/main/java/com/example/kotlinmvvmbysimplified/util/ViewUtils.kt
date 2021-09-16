package com.example.kotlinmvvmbysimplified.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun ProgressBar.show(){
  visibility= View.VISIBLE
}
fun ProgressBar.hide(){
    visibility = View.GONE
}
fun View.snackBar(message:String){  //we need coordinator layout for using snackBar
    Snackbar.make(this,message,Snackbar.LENGTH_INDEFINITE).also { snackbar->
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }

    }.show()
}