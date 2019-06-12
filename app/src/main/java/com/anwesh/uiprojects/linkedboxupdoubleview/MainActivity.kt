package com.anwesh.uiprojects.linkedboxupdoubleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.boxupdoubleview.BoxUpDoubleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BoxUpDoubleView.create(this)
    }
}
