package com.example.games_r_us

object Utils {

    fun getPlatformIcon(platform: String): Int {
        when (platform) {
            "PC (Windows)" -> {
                return R.drawable.ic_windows
            }
            "Web Browser" -> {
                return R.drawable.ic_web
            }
            else -> {
                return R.drawable.ic_pc
            }
        }
    }
}