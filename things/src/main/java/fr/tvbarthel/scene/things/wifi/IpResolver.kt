package fr.tvbarthel.scene.things.wifi

import android.net.wifi.WifiManager

class IpResolver(private val wifiManager: WifiManager) {

    fun resolveIpAddress(): String {
        val connectionInfo = wifiManager.connectionInfo
        val ip = connectionInfo.ipAddress

        return String.format("%d.%d.%d.%d",
                ip.and(0xff),
                ip.shr(8).and(0xff),
                ip.shr(16).and(0xff),
                ip.shr(24).and(0xff))
    }
}