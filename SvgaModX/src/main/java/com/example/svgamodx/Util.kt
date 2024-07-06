package com.example.svgamodx

import androidx.fragment.app.FragmentManager

object Util {
    private var svgaDialog: SvgaScreenDialog = SvgaScreenDialog.newInstance()
    var animationHeight = 200
    var animationWidth = 200
    var isLooped = false

    fun setSVGAParameters(width: Int, height: Int, isLooped: Boolean) {
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