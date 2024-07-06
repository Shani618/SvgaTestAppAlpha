package com.example.svgamodx

import android.net.http.HttpResponseCache
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.opensource.svgaplayer.SVGACallback
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import com.opensource.svgaplayer.utils.log.SVGALogger
import java.io.File
import java.net.URL


class SvgaScreenDialog : DialogFragment() {

    private val svgaImageView: SVGAImageView? = null
    private var svgaAnimationListener: SvgaAnimationListener? = null

    companion object {
        fun newInstance(): SvgaScreenDialog {
            return SvgaScreenDialog()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_svga_screen, container, false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog?.setCanceledOnTouchOutside(false)
        isCancelable = false
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSvgaAnimation()
    }

    private fun loadSvgaAnimation() {
        val cacheDir = File(context?.applicationContext?.cacheDir, "http")
        HttpResponseCache.install(cacheDir, 1024 * 1024 * 128)
        val svgaImageView = view?.findViewById<SVGAImageView>(R.id.svgaImageView)
        // update imageView height and with
        svgaImageView?.layoutParams?.height = Util.animationHeight
        svgaImageView?.layoutParams?.width = Util.animationWidth
        // autoPlay off/on
        if (Util.isLooped) {
            svgaImageView?.loops = 0
        } else {
            svgaImageView?.loops = 1
        }
        val svgaAnimationListener = object : SVGACallback {
            override fun onPause() {
                this@SvgaScreenDialog.svgaAnimationListener?.onSvgaPause()
            }

            override fun onFinished() {
                this@SvgaScreenDialog.svgaAnimationListener?.onSvgaFinished()
            }

            override fun onRepeat() {
                this@SvgaScreenDialog.svgaAnimationListener?.onSvgaRepeat()
            }

            override fun onStep(frame: Int, percentage: Double) {
                this@SvgaScreenDialog.svgaAnimationListener?.onSvgaStep(frame, percentage)
            }
        }
        svgaImageView?.callback = svgaAnimationListener


        // By default, SVGA will not output any log, so you need to manually set it to true
        SVGALogger.setLogEnabled(true)


        val parser = SVGAParser(this.requireContext())
        val url = Util.url
        parser.decodeFromURL(URL(url), object : SVGAParser.ParseCompletion {
            override fun onComplete(videoItem: SVGAVideoEntity) {
                svgaImageView?.setVideoItem(videoItem)
                svgaImageView?.startAnimation()
            }

            override fun onError() {
                // Handle the error
                this@SvgaScreenDialog.svgaAnimationListener?.onLoadError()
            }
        })


    }

    fun setSvgaAnimationListener(listener: SvgaAnimationListener) {
        this.svgaAnimationListener = listener
    }

    override fun onDestroy() {
        super.onDestroy()
        svgaImageView?.stopAnimation()
    }
}