package com.example.svgamodx
interface SvgaAnimationListener {
    fun onSvgaPause()
    fun onSvgaFinished()
    fun onSvgaRepeat()
    fun onSvgaStep(frame: Int, percentage: Double)
    fun onLoadError()
}