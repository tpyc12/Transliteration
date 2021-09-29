package com.myhome.android.transliteration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myhome.android.transliteration.databinding.ActivityMainBinding

private lateinit var ui: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val translit = Translit()
    private var string: String = ""
    private var res: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.buttonLatToRus.setOnClickListener {
            string = ui.etLat.text.toString()
            res = translit.lat2cyr(string)
            ui.tvResult.text = res
        }

        ui.buttonRusToLat.setOnClickListener {
            string = ui.etRus.text.toString()
            res = translit.cyr2lat(string)
            ui.tvResult.text = res
        }
    }
}