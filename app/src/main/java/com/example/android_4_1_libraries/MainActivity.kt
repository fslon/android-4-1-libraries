package com.example.android_4_1_libraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android_4_1_libraries.databinding.ActivityMainBinding
import com.example.android_4_1_libraries.presenter.MainPresenter
import com.example.android_4_1_libraries.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.counterClickButton1() }
        vb?.btnCounter2?.setOnClickListener { presenter.counterClickButton2() }
        vb?.btnCounter3?.setOnClickListener { presenter.counterClickButton3() }
    }

    override fun setButton1Text(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        vb?.btnCounter3?.text = text
    }


}
