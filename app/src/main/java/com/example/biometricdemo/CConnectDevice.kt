package com.example.biometricdemo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.epson.epos2.Epos2Exception
import com.epson.epos2.discovery.Discovery
import com.epson.epos2.discovery.DiscoveryListener
import com.epson.epos2.discovery.FilterOption

class CConnectDevice(var poCon: Context, var poAct: Activity) {

    private var aoPrinterList: ArrayList<HashMap<String, String>> = arrayListOf()

    fun C_GETaGetList(): ArrayList<HashMap<String, String>> {
        return aoPrinterList
    }

    fun C_STDaRestartDiscory(): ArrayList<HashMap<String, String>> {
        C_STPxStop()
        C_DISxDiscovery()
        return aoPrinterList
    }

    fun C_DISxDiscovery() {
        var oFilterOption = FilterOption().apply {
            deviceType = Discovery.TYPE_PRINTER
            epsonFilter = Discovery.FILTER_NAME
        }

        var oListener = DiscoveryListener { poDeviceInfo ->
            poAct.runOnUiThread(
                Runnable {
                    kotlin.run {
                        val item = java.util.HashMap<String, String>()
                        item["PrinterName"] = poDeviceInfo!!.deviceName
                        item["Target"] = poDeviceInfo!!.target
                        aoPrinterList.add(item)
                        if (aoPrinterList.size > 0) {
                            Log.d("TAGG", "onCreate: ${aoPrinterList[0]["PrinterName"]}")
                        }
                    }
                }
            )

        }

        try {
            Log.d("TAGG", "onCreate: ${aoPrinterList.size}")

            Discovery.start(poCon, oFilterOption, oListener)
            Discovery.stop()
        } catch (poe: Exception) {
            CShowMsg.showException(poe, "start", poCon)
        }
    }

    fun C_STPxStop() {
        while (true) {
            try {
                Discovery.stop()
                break
            } catch (poe: Epos2Exception) {
                if (poe.errorStatus != Epos2Exception.ERR_PROCESSING) {
                    break
                }
            }
        }
        aoPrinterList.clear()
    }

    fun requestRuntimePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        val permissionStorage: Int = ContextCompat.checkSelfPermission(
            poCon,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionLocation: Int = ContextCompat.checkSelfPermission(
            poCon,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        val requestPermissions: MutableList<String> =
            java.util.ArrayList()
        if (permissionStorage == PackageManager.PERMISSION_DENIED) {
            requestPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (permissionLocation == PackageManager.PERMISSION_DENIED) {
            requestPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!requestPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                poAct,
                requestPermissions.toTypedArray(),
                100
            )
        }
    }


}
