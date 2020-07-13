package com.example.biometricdemo

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import com.epson.epos2.Epos2Exception
import com.epson.epos2.printer.Printer
import com.epson.epos2.printer.PrinterStatusInfo
import com.epson.epos2.printer.ReceiveListener

class CPrinterCommand(
    private val poPrinterSerial: String,
    val poCon: Context,
    var poAct: Activity
) : ReceiveListener {

    private val REQUEST_PERMISSION = 100
    private val DISCONNECT_INTERVAL = 500
    private val DEFAULT_MARGIN = 10

    private var oC_Printer: Printer? = null
    private var nC_DefaultAlign = Printer.ALIGN_CENTER
    private var nC_DefaultFont = Printer.FONT_A
    private var nC_DefaultSize = 1
    private var nC_DefaultLength = 32


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
        var tOrder =
            if (ptText.length > nC_DefaultLength) ptText.substring(0, nC_DefaultLength) else ptText

        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addTextAlign(nC_DefaultAlign)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addText("$tOrder\n")
    }

    fun C_ADDxAddSingleLine(ptText: String, pnAlign: Int, pnSize: Int) {
        var tOrder = if (ptText.length > nC_DefaultLength / pnSize) ptText.substring(
            0,
            nC_DefaultLength / pnSize
        ) else ptText

        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addTextAlign(pnAlign)
        oC_Printer?.addTextSize(pnSize, pnSize)
        oC_Printer?.addText("$tOrder\n")
    }

    fun C_ADDxAddOrder(ptOrder: String, pnValue: Double, ptCurNme: String) {
        var nMaxLength = nC_DefaultLength

        var tValue = String.format("%.2f", pnValue)
        var tCurNme = ptCurNme[0]

        var nMaxOrderLength = (nMaxLength - (tValue.length + 3))
        var tOrder =
            if (ptOrder.length > nMaxOrderLength)
                ptOrder.substring(0, nMaxOrderLength)
            else ptOrder

        var tText = "$tOrder${C_GETtGetCharacter(
            nMaxLength - (tOrder.length + tValue.length + 3)
            , ' '
        )} $tValue$tCurNme"

        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addTextFont(nC_DefaultFont)
        oC_Printer?.addText("$tText\n")
    }

    fun C_ADDxAddOrder(ptOrder: String, pnValue: Double, ptCurNme: String, pnSize: Int) {
        var nMaxLength = (nC_DefaultLength / pnSize) - 1

        var tValue = String.format("%.2f", pnValue)
        var tCurNme = ptCurNme[0]

        var nMaxOrderLength = (nMaxLength - (tValue.length + 2))
        var tOrder =
            if (ptOrder.length > nMaxOrderLength)
                ptOrder.substring(0, nMaxOrderLength)
            else ptOrder

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

    fun C_ADDxAddLine() {
        oC_Printer?.addTextAlign(Printer.ALIGN_CENTER)
        oC_Printer?.addTextSize(nC_DefaultSize, nC_DefaultSize)
        oC_Printer?.addText("\n" + C_GETtGetCharacter(nC_DefaultLength, '-') + "\n")
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

    private fun C_SETxDisconnectPrinter() {
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
                        poAct.runOnUiThread(Runnable {
                            CShowMsg.showException(
                                poe,
                                "disconnect",
                                poCon
                            )
                        })
                        break
                    }
                } else {
                    poAct.runOnUiThread(Runnable {
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
        if (pnChar >= 0){
        for (x in 0..pnChar) {
            tTemp += ptChar
        }}else{}
        return tTemp
    }

    override fun onPtrReceive(
        poPrinter: Printer?,
        pnCode: Int,
        poStatus: PrinterStatusInfo?,
        ptJobId: String?
    ) {
        poAct.runOnUiThread(Runnable {
            CShowMsg.showResult(pnCode, poStatus?.let { C_SETtMakeErrorMessage(it) }, poCon)
            Thread(Runnable {
                C_SETxDisconnectPrinter()
            })
        })
    }

    fun Bitmap.resizeByWidth(width:Int, height: Int):Bitmap{
        val ratio:Float = this.width.toFloat() / this.height.toFloat()
        val height:Int = Math.round(width / ratio)

        return Bitmap.createScaledBitmap(
            this,
            width,
            height,
            false
        )
    }

    fun Bitmap.resizeByHeight(height:Int):Bitmap{
        val ratio:Float = this.height.toFloat() / this.width.toFloat()
        val width:Int = Math.round(height / ratio)

        return Bitmap.createScaledBitmap(
            this,
            width,
            height,
            false
        )
    }
}