package com.example.biometricdemo

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.epson.epos2.Epos2Exception
import com.epson.epos2.printer.Printer
import com.epson.epos2.printer.PrinterStatusInfo
import com.epson.epos2.printer.ReceiveListener
import java.text.DecimalFormat

class CPrinterCommand() : ReceiveListener {

    private var poPrinterSerial: String? = null
    private var poCon: Context? = null
    private var poAct: Activity? = null


    constructor(
        poPassingCon: Context,
        poPassingAct: Activity
    ) : this() {
        poCon = poPassingCon
        poAct = poPassingAct
    }

    constructor(
        poPassingPrinterSerial: String,
        poPassingCon: Context,
        poPassingAct: Activity
    ) : this() {
        poPrinterSerial = poPassingPrinterSerial
        poCon = poPassingCon
        poAct = poPassingAct
    }


    private val REQUEST_PERMISSION = 100
    private val DISCONNECT_INTERVAL = 500
    private val DEFAULT_MARGIN = 10

    private var oC_Printer: Printer? = null
    private var nC_DefaultAlign = Printer.ALIGN_CENTER
    private var nC_DefaultFont = Printer.FONT_B
    private var nC_DefaultSize = 1
    private var nC_DefaultLength = 42


    fun C_PRCbPrintData(): Boolean {
        if (oC_Printer == null) {
            return false
        }

        if (!C_SETbConnectPrinter()) {
            oC_Printer?.clearCommandBuffer()
            return false
        }

        try {
            oC_Printer?.sendData(Printer.PARAM_DEFAULT)

        } catch (poe: Exception) {
            oC_Printer?.clearCommandBuffer()
            CShowMsg.showException(poe, "sendData", poCon)
            try {
                oC_Printer?.disconnect()
            } catch (poe: Exception) {

            }
            return false
        }
        return true
    }

    fun C_ADDxAddSingleLine(ptText: String) {
        var nVowel = C_GETnGetNumberOfThaiChar(ptText)
        var tOrder =
            if (ptText.length > nC_DefaultLength + nVowel) ptText.substring(
                0,
                nC_DefaultLength + nVowel
            ) else ptText

        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addTextAlign(nC_DefaultAlign)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addText("$tOrder\n")
    }

    fun C_ADDxAddSingleLine(ptText: String, pnAlign: Int, pnSize: Int) {
        var nVowel = C_GETnGetNumberOfThaiChar(ptText)

        var tOrder = if (ptText.length > (nC_DefaultLength + nVowel) / pnSize) ptText.substring(
            0,
            (nC_DefaultLength + nVowel) / pnSize
        ) else ptText

        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addTextAlign(pnAlign)
        oC_Printer?.addTextSize(pnSize, pnSize)
        oC_Printer?.addText("$tOrder\n")
    }

    fun C_ADDxAddLR(ptLeft: String, ptRight: String) {
        var tText = ""
        var tLeft = if (ptLeft.length > nC_DefaultLength / 2) ptLeft.substring(
            0,
            (nC_DefaultLength / 2) - 1
        )
        else ptLeft
        var tRight = if (ptRight.length > nC_DefaultLength / 2) ptRight.substring(
            0,
            (nC_DefaultLength / 2) - 1
        )
        else ptRight

        var nSpec = nC_DefaultLength - (tLeft.length + tRight.length)
        tText += tLeft + C_GETtGetCharacter(nSpec - 1, ' ') + tRight


        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddLR(ptLeft: String, ptRight: String, pnNumRight: Int) {
        var tText = ""
        var tLeft = if (ptLeft.length > nC_DefaultLength - pnNumRight) ptLeft.substring(
            0,
            (nC_DefaultLength - pnNumRight) - 1
        )
        else ptLeft
        var tRight = if (ptRight.length > -pnNumRight) ptRight.substring(0, pnNumRight)
        else ptRight

        var nSpec = nC_DefaultLength - (tLeft.length + tRight.length)
        tText += tLeft + C_GETtGetCharacter(nSpec - 1, ' ') + tRight


        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddOrderCol(tText1st: String, tText2nd: String, tText3rd: String) {
        var tText = ""
        var oPrinterLA = CmlPrinterLA()


        var tText1 = if (tText1st.length > oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st))
            tText1st.substring(0, oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st))
        else tText1st + C_GETtGetCharacter((oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st)) - tText1st.length, ' ')

        var tText2 = if (tText2nd.length > oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd))
            tText2nd.substring(0, oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd))
        else tText2nd + C_GETtGetCharacter((oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd)) - tText2nd.length, ' ')

        var tText3 = if (tText3rd.length > oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd))
            tText3rd.substring(0, oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd))
        else tText3rd + C_GETtGetCharacter((oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd)) - tText3rd.length, ' ')

        tText += "$tText1 $tText2 $tText3"

        oC_Printer?.addTextAlign(Printer.ALIGN_LEFT)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddOrderCol(
        tText1st: String,
        tText2nd: String,
        tText3rd: String,
        poPrinterLA: CmlPrinterLA
    ) {
        var tText = ""
        var oPrinterLA = poPrinterLA

        var tText1 = if (tText1st.length > oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st))
            tText1st.substring(0, oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st))
        else {
            when (oPrinterLA.nAlignCol1st) {
                Printer.ALIGN_RIGHT ->   C_GETtGetCharacter((oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st)) - tText1st.length, ' ') + tText1st
                Printer.ALIGN_LEFT ->    tText1st + C_GETtGetCharacter((oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st)) - tText1st.length, ' ')
                else ->                  tText1st + C_GETtGetCharacter((oPrinterLA.nLengthCol1st + C_GETnGetNumberOfThaiChar(tText1st)) - tText1st.length, ' ')
            }
        }

        var tText2 = if (tText2nd.length > oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd))
            tText2nd.substring(0, oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd))
        else {
            when (oPrinterLA.nAlignCol2nd) {
                Printer.ALIGN_RIGHT ->   C_GETtGetCharacter((oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd)) - tText2nd.length, ' ') + tText2nd
                Printer.ALIGN_LEFT ->    tText2nd + C_GETtGetCharacter((oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd)) - tText2nd.length, ' ')
                else ->                  tText2nd + C_GETtGetCharacter((oPrinterLA.nLengthCol2nd + C_GETnGetNumberOfThaiChar(tText2nd)) - tText2nd.length, ' ')
            }
        }

        var tText3 = if (tText3rd.length > oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd))
            tText3rd.substring(0, oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd))
        else {
            when (oPrinterLA.nAlignCol3rd) {
                Printer.ALIGN_RIGHT ->  C_GETtGetCharacter((oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd)) - tText3rd.length, ' ') + tText3rd
                Printer.ALIGN_LEFT ->   tText3rd + C_GETtGetCharacter((oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd)) - tText3rd.length,' ')
                else ->                 tText3rd + C_GETtGetCharacter((oPrinterLA.nLengthCol3rd + C_GETnGetNumberOfThaiChar(tText3rd)) - tText3rd.length,' ')

            }
        }

        tText += "$tText1 $tText2 $tText3"

        oC_Printer?.addTextAlign(Printer.ALIGN_LEFT)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddOrder(ptOrder: String, pnValue: Double, ptCurNme: String) {
        var nVowel = C_GETnGetNumberOfThaiChar(ptOrder)
        var nMaxLength = nC_DefaultLength + nVowel

        var tValue = C_GETtCurForm(pnValue)
        var tCurNme = if (ptCurNme.isNotEmpty()) ptCurNme[0] else ""

        var nMaxOrderLength = (nMaxLength - (tValue?.length?.plus(2))!!)
        var tOrder =
            if (ptOrder.length > nMaxOrderLength) {
                var nTemp = C_GETnGetNumberOfThaiChar(
                    ptOrder.substring(
                        nMaxOrderLength - 1,
                        ptOrder.length
                    )
                )
                ptOrder.substring(0, nMaxOrderLength - nTemp)
            } else ptOrder

        var tText = "$tOrder${C_GETtGetCharacter(
            nMaxLength - (tOrder.length + tValue.length + 2)
            , ' '
        )} $tValue$tCurNme"

        oC_Printer?.addTextAlign(Printer.ALIGN_LEFT)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddOrder(ptOrder: String, pnValue: Double, ptCurNme: String, pnSize: Int) {
        var nVowel = C_GETnGetNumberOfThaiChar(ptOrder)
        var nMaxLength = ((nC_DefaultLength / pnSize) - 1) + nVowel

        var tValue = C_GETtCurForm(pnValue)
        var tCurNme = if (ptCurNme.isNotEmpty()) ptCurNme[0] else ""

        var nMaxOrderLength = (nMaxLength - (tValue?.length?.plus(2))!!)
        var tOrder =
            if (ptOrder.length > nMaxOrderLength) {
                var nTemp = C_GETnGetNumberOfThaiChar(
                    ptOrder.substring(
                        nMaxOrderLength - 1,
                        ptOrder.length
                    )
                )
                ptOrder.substring(0, nMaxOrderLength - nTemp)
            } else ptOrder

        var tText = "$tOrder${C_GETtGetCharacter(
            nMaxLength - (tOrder.length + tValue.length + 2)
            , ' '
        )} $tValue$tCurNme"

        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(pnSize, pnSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddBitmap(poBitmap: Bitmap) {
        oC_Printer?.addTextAlign(nC_DefaultAlign)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addImage(
            poBitmap,
            0,
            0,
            poBitmap.width,
            poBitmap.height,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT.toDouble(),
            Printer.PARAM_DEFAULT
        )
    }

    fun C_ADDxAddBitmap(poBitmap: Bitmap, pnAlign: Int, pnSize: Int) {
        oC_Printer?.addTextAlign(pnAlign)
        oC_Printer?.addTextSize(pnSize, pnSize)
        oC_Printer?.addImage(
            poBitmap,
            0,
            0,
            poBitmap.width,
            poBitmap.height,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT,
            Printer.PARAM_DEFAULT.toDouble(),
            Printer.PARAM_DEFAULT
        )
    }

    fun C_ADDxAddBarcode(
        ptData: String,
        pnType: Int,
        pnHri: Int,
        pnFont: Int,
        pnBarcodeWeight: Int,
        pnBarcodeHeight: Int
    ) {
        oC_Printer?.addTextAlign(nC_DefaultAlign)
        oC_Printer?.addBarcode(ptData, pnType, pnHri, pnFont, pnBarcodeWeight, pnBarcodeHeight)
        oC_Printer?.addText("\n")
    }

    fun C_ADDxAddBarcode(
        pnAlign: Int,
        ptData: String,
        pnType: Int,
        pnHri: Int,
        pnFont: Int,
        pnBarcodeWeight: Int,
        pnBarcodeHeight: Int
    ) {
        oC_Printer?.addTextAlign(pnAlign)
        oC_Printer?.addBarcode(ptData, pnType, pnHri, pnFont, pnBarcodeWeight, pnBarcodeHeight)
        oC_Printer?.addText("\n")
    }

    fun C_ADDxAddColumnLine(paArray: ArrayList<String>) {
        var tText = ""
        var nMaxColLength = nC_DefaultLength / paArray.size

        for (tItem in paArray) {
            var nColLength = nMaxColLength + C_GETnGetNumberOfThaiChar(tItem) - 1
            tText += if (tItem.length > nColLength) tItem.substring(0, nColLength)
            else tItem
            tText += C_GETtGetCharacter(
                if (tItem.length > nColLength) 0
                else nColLength - tItem.length
                , ' '
            )
        }

        oC_Printer?.addTextAlign(Printer.ALIGN_LEFT)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddUnderLine(ptChar: Char) {
        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addText("\n" + C_GETtGetCharacter(nC_DefaultLength - 1, ptChar) + "\n")
    }

    fun C_ADDxAddCut() {
        oC_Printer?.addCut(Printer.CUT_FEED)
    }

    fun C_SETxSetDefaultAlign(pnAlign: Int) {
        nC_DefaultAlign = pnAlign
    }

    fun C_SETxSetDefaultFount(pnFont: Int) {
        nC_DefaultFont = pnFont
    }

    fun C_SETxSetDefaultSize(pnWeight: Int) {
        nC_DefaultSize = pnWeight
    }

    fun C_SETxSetDefaultLength(pnLength: Int) {
        nC_DefaultLength = pnLength
    }

    fun C_SETxInitializePrinter(pnSeries: Int, pnLang: Int) {
        C_SETxFinalizePrinter()

        try {
            oC_Printer = Printer(pnSeries, pnLang, poCon)
        } catch (poe: Exception) {
            CShowMsg.showException(poe, "Printer", poCon)
        }
        oC_Printer?.setReceiveEventListener(this)
    }

    fun C_SETxFinalizePrinter() {
        if (oC_Printer == null) {
            return
        }

        oC_Printer?.setReceiveEventListener(null)
        oC_Printer = null
    }

    fun C_SETbConnectPrinter(): Boolean {
        if (oC_Printer == null) {
            return false
        }

        try {
            oC_Printer?.connect(poPrinterSerial, Printer.PARAM_DEFAULT)
        } catch (poe: Exception) {
//            CShowMsg.showException(poe, "connect", poCon)
        }

        return true
    }

    fun C_SETxDisconnectPrinter() {
        if (oC_Printer == null) {
            return
        }
        while (true) {
            try {
                oC_Printer?.disconnect()
                break
            } catch (poe: Exception) {
                if (poe is Epos2Exception) {
                    if (poe.errorStatus == Epos2Exception.ERR_PROCESSING) {
                        try {
                            Thread.sleep(DISCONNECT_INTERVAL.toLong())
                        } catch (ex: Exception) {
                        }
                    } else {
                        poAct?.runOnUiThread(Runnable {
                            CShowMsg.showException(
                                poe,
                                "disconnect",
                                poCon
                            )
                        })
                        break
                    }
                } else {
                    poAct?.runOnUiThread(Runnable {
                        CShowMsg.showException(
                            poe,
                            "disconnect",
                            poCon
                        )
                    })
                    break
                }
            }
        }
        oC_Printer?.clearCommandBuffer()
    }

    private fun C_SETtMakeErrorMessage(poStatus: PrinterStatusInfo): String? {
        var tMsg: String? = ""
        if (poStatus.online == Printer.FALSE) {
            tMsg += "Printer is offline.\n"
        }
        if (poStatus.connection == Printer.FALSE) {
            tMsg += "Please check the connection of the printer and the mobile terminal.\nConnection get lost.\n"
        }
        if (poStatus.coverOpen == Printer.TRUE) {
            tMsg += "Please close roll paper cover.\n"
        }
        if (poStatus.paper == Printer.PAPER_EMPTY) {
            tMsg += "Please check roll paper.\n"
        }
        if (poStatus.paperFeed == Printer.TRUE || poStatus.panelSwitch == Printer.SWITCH_ON) {
            tMsg += "Please release a paper feed switch.\n"
        }
        if (poStatus.errorStatus == Printer.MECHANICAL_ERR || poStatus.errorStatus == Printer.AUTOCUTTER_ERR) {
            tMsg += "Please remove jammed paper and close roll paper cover.\nRemove any jammed paper or foreign substances in the printer, and then turn the printer off and turn the printer on again.\n"
            tMsg += "Then, If the printer doesn\\'t recover from error, please cycle the power switch.\n"
        }
        if (poStatus.errorStatus == Printer.UNRECOVER_ERR) {
            tMsg += "Please cycle the power switch of the printer.\nIf same errors occurred even power cycled, the printer may out of order."
        }
        if (poStatus.errorStatus == Printer.AUTORECOVER_ERR) {
            if (poStatus.autoRecoverError == Printer.HEAD_OVERHEAT) {
                tMsg += "Please wait until error LED of the printer turns off. \n"
                tMsg += "Print head of printer is hot.\n"
            }
            if (poStatus.autoRecoverError == Printer.MOTOR_OVERHEAT) {
                tMsg += "Please wait until error LED of the printer turns off. \n"
                tMsg += "Motor Driver IC of printer is hot.\n"
            }
            if (poStatus.autoRecoverError == Printer.BATTERY_OVERHEAT) {
                tMsg += "Please wait until error LED of the printer turns off. \n"
                tMsg += "Battery of printer is hot.\n"
            }
            if (poStatus.autoRecoverError == Printer.WRONG_PAPER) {
                tMsg += "Please set correct roll paper.\n"
            }
        }
        if (poStatus.batteryLevel == Printer.BATTERY_LEVEL_0) {
            tMsg += "Battery level of printer is low.\n"
        }
        return tMsg
    }

    private fun C_GETtGetCharacter(pnChar: Int, ptChar: Char): String {
        var tTemp = ""
        if (pnChar >= 0) {
            for (x in 0..pnChar) {
                tTemp += ptChar
            }
        } else {
        }
        return tTemp
    }

    private fun C_GETnGetNumberOfThaiChar(ptText: String): Int {
        var nVowel = 0
        var atVowel = arrayOf('ุ', 'ู', 'ิ', 'ื', '์', '่', '้', '๊', '๋', '็', 'ี', 'ั', 'ฺ', 'ึ')
        for (tTemp in ptText) {
            if (atVowel.contains(tTemp))
                nVowel++
        }
        return nVowel
    }

    private fun C_GETtCurForm(pnNum: Double): String? {
        val oFormatter = DecimalFormat("###,###,###.00")
        return oFormatter.format(pnNum)
    }

    override fun onPtrReceive(
        poPrinter: Printer?,
        pnCode: Int,
        poStatus: PrinterStatusInfo?,
        ptJobId: String?
    ) {
        poAct?.runOnUiThread(Runnable {
            CShowMsg.showResult(pnCode, poStatus?.let { C_SETtMakeErrorMessage(it) }, poCon)
            Thread(Runnable {
                C_SETxDisconnectPrinter()
            })
        })
    }

    fun requestRuntimePermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        val permissionStorage: Int = ContextCompat.checkSelfPermission(
            poCon!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permissionLocation: Int = ContextCompat.checkSelfPermission(
            poCon!!,
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
                poAct!!,
                requestPermissions.toTypedArray(), 100
            )
        }
    }

    fun Bitmap.resizeByWidth(width: Int): Bitmap {
        val ratio: Float = this.width.toFloat() / this.height.toFloat()
        val height: Int = Math.round(width / ratio)

        return Bitmap.createScaledBitmap(
            this,
            width,
            height,
            false
        )
    }

    fun Bitmap.resizeByHeight(height: Int): Bitmap {
        val ratio: Float = this.height.toFloat() / this.width.toFloat()
        val width: Int = Math.round(height / ratio)

        return Bitmap.createScaledBitmap(
            this,
            width,
            height,
            false
        )
    }
}