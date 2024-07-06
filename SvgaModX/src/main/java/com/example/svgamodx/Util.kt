package com.example.svgamodx

import androidx.fragment.app.FragmentManager

object Util {
    private var svgaDialog: SvgaScreenDialog = SvgaScreenDialog.newInstance()
    var url = "https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true"
    var animationHeight = 200
    var animationWidth = 200
    var isLooped = false

    fun setSVGAParameters(url: String, width: Int, height: Int, isLooped: Boolean) {
        this.url = url
        animationHeight = height
        animationWidth = width
        this.isLooped = isLooped
    }

    fun showSvgaAnimation(supportFragmentManager: FragmentManager, width: Int, height: Int, isLooped: Boolean) {
        animationHeight = height
        animationWidth = width
        this.isLooped = isLooped
        if (svgaDialog.isAdded) return
        svgaDialog.show(supportFragmentManager, "celebrationDialog")
    }

    fun hideSvgaAnimation() {
        svgaDialog.dismiss()
    }
}