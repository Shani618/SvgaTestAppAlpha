- Download "SvgaModX" Module from the Git Repository.
- Import this Module in your app.
- Use ```Util.setSVGAParameters(url, 800, 800, true)``` to set the parameters (url, width, height, isLoop)
- Use ```val dialog = SvgaScreenDialog()
        dialog.setSvgaAnimationListener(this)
        dialog.show(supportFragmentManager, "SvgaScreenDialog")``` to display the animation.
