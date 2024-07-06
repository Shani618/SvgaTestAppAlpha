package com.example.svgatestappalpha

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.svgamodx.SvgaAnimationListener
import com.example.svgamodx.SvgaScreenDialog
import com.example.svgamodx.Util

class MainActivity : AppCompatActivity(), SvgaAnimationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var url = "https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true"
        Util.setSVGAParameters( url, 800, 800, true)
        val dialog = SvgaScreenDialog()
        dialog.setSvgaAnimationListener(this)
        dialog.show(supportFragmentManager, "SvgaScreenDialog")
    }

    override fun onSvgaPause() {
        Log.d("MainActivity", "SVGA Animation Paused")
    }

    override fun onSvgaFinished() {
        Log.d("MainActivity", "SVGA Animation Finished")
    }

    override fun onSvgaRepeat() {
        Log.d("MainActivity", "SVGA Animation Repeated")
    }

    override fun onSvgaStep(frame: Int, percentage: Double) {
       // Log.d("MainActivity", "SVGA Animation Step - Frame: $frame, Percentage: $percentage")
    }

    override fun onLoadError() {
        Log.d("MainActivity", "SVGA Animation Load Error")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}