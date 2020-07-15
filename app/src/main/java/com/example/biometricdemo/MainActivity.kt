package com.example.biometricdemo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epson.epos2.printer.Printer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CAdjust.CmlAdajustListener {

    lateinit var oC_Printer: CPrinterCommand

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAGG", "onCreate: ")
        setContentView(R.layout.activity_main)
        oC_Printer = CPrinterCommand(this, this)
        oC_Printer.requestRuntimePermission()

/*
        demoFrag.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .add(R.id.demoFrag,CAdjust(CAdjust.CmlAdjust.ADJUST_PRICE, 10.00, this, this))
            .addToBackStack(null)
            .commit()
*/


        biometric_login.setOnClickListener {
            /*        demoFrag.visibility = View.VISIBLE
                      supportFragmentManager.beginTransaction()
                          .add(R.id.demoFrag,CAdjust(CAdjust.CmlAdjust.ADJUST_PRICE, 10.00, this, this))
                          .addToBackStack(null)
                          .commit()
          */
            startActivityForResult(Intent(this, DiscoveryActivity::class.java), 100)

        }

        runPrint2.setOnClickListener {
            oC_Printer.C_SETbConnectPrinter()
            oC_Printer.C_SETxSetDefaultFount(Printer.FONT_B)
            oC_Printer.C_SETxSetDefaultLength(50)
            oC_Printer.C_ADDxAddSingleLine("ใบเสร็จรับเงิน")
            oC_Printer.C_ADDxAddSingleLine("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890")
            oC_Printer.C_ADDxAddCut()
            oC_Printer.C_PRCbPrintData()
            oC_Printer.C_SETxDisconnectPrinter()
        }

        runPrint.setOnClickListener {
            if (oC_Printer != null) {
                oC_Printer.C_SETbConnectPrinter()
              /*  oC_Printer.C_SETxSetDefaultFount(Printer.FONT_B)
                oC_Printer.C_SETxSetDefaultLength(42)
                oC_Printer.C_ADDxAddSingleLine("ใบเสร็จรับเงิน")
                oC_Printer.C_ADDxAddSingleLine("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890")
                oC_Printer.C_ADDxAddLR("ID:1234567890", "USR:009 T:00004")
                oC_Printer.C_ADDxAddOrder("PR151-8080D กรองน้ำมันเชื้อเพลิง", 350.00, "")
                oC_Printer.C_ADDxAddSingleLine("L7311-82390 ใส้กรองอากาศ", Printer.ALIGN_LEFT, 1)
                oC_Printer.C_ADDxAddOrder("2.00 x 1,050.00", 2100.00, "")
                oC_Printer.C_ADDxAddOrder("  ลด(105)", (2100.00 - 105.00), "")
                oC_Printer.C_ADDxAddOrder("1W071-99291 น้ำมันเครื่อง CF4 ขนาด 6", 620.00, "")
                oC_Printer.C_ADDxAddOrder("ซื้อทั้ง A+B ได้ลด 5% ทั้ง 2 กลุ่ม", -117.25, "")
                oC_Printer.C_ADDxAddOrder("ลด e-Coupon 15%", -93.00, "")
                oC_Printer.C_ADDxAddUnderLine(' ')
                oC_Printer.C_ADDxAddOrder("รวมเงิน", 2405.00, "")
                oC_Printer.C_ADDxAddOrder("ลด (100.00)", 100.00, "")
                oC_Printer.C_ADDxAddOrder("แลกแต้มรับส่วนลก (50.00)", 50.00, "")
                oC_Printer.C_ADDxAddOrder("ยอดสุทธิ(รวมภาษีมูลค่าเพิ่ม)", 2255.00, "")
                oC_Printer.C_ADDxAddSingleLine(
                    "ก่อนภาษี : 2,107.48 ภาษี : 147.52",
                    Printer.ALIGN_LEFT,
                    1
                )
                oC_Printer.C_ADDxAddUnderLine(' ')
                oC_Printer.C_ADDxAddOrder("เงินสด", 2500.00, "")
                oC_Printer.C_ADDxAddOrder("เงินทอน", 245.00, "")
                oC_Printer.C_ADDxAddUnderLine(' ')
                oC_Printer.C_ADDxAddSingleLine(
                    "รหัสสมาชิก : 12345678901234567890",
                    Printer.ALIGN_LEFT,
                    1
                )
                oC_Printer.C_ADDxAddSingleLine(" กิติ", Printer.ALIGN_LEFT, 1)
                oC_Printer.C_ADDxAddSingleLine("วันหมดอายุ 21/4/7878", Printer.ALIGN_LEFT, 1)
                oC_Printer.C_ADDxAddColumnLine(
                    arrayListOf(
                        "แต้มเดิม",
                        "ใช้/ได้รับ",
                        "แต้มโปร",
                        "คงเหลือ"
                    )
                )
                oC_Printer.C_ADDxAddColumnLine(arrayListOf("1,000", "500", "0", "500"))
                oC_Printer.C_ADDxAddUnderLine(' ')
                oC_Printer.C_ADDxAddSingleLine("พนักงาน: นาย สมชาย ดีมาก", Printer.ALIGN_LEFT, 1)
                oC_Printer.C_ADDxAddBarcode(
                    "123456789",
                    Printer.BARCODE_CODE39,
                    Printer.HRI_BOTH,
                    Printer.FONT_A,
                    2,
                    100
                )
                oC_Printer.C_ADDxAddUnderLine(' ')
                oC_Printer.C_ADDxAddUnderLine('-')
                oC_Printer.C_ADDxAddSingleLine("ได้รับสิทธิ์ จับฉลาก 2 สิทธิ์")
                oC_Printer.C_ADDxAddUnderLine('-')
                oC_Printer.C_ADDxAddBarcode(
                    "56789",
                    Printer.BARCODE_CODE39,
                    Printer.HRI_BOTH,
                    Printer.FONT_A,
                    2,
                    100
                )*/
                oC_Printer.C_ADDxAddUnderLine('=')
                oC_Printer.C_ADDxAddOrderCol("11w", "น้ำไม่มีดี","00.1")
                oC_Printer.C_ADDxAddOrderCol("11w223545232หกฟหกฟ", "น้ำไม่มีดีอะไรเลนจริงจริงน่ะครับ ","11200.14487")
                oC_Printer.C_ADDxAddOrderCol("11wกฟห", "จริงน่ะครับ ","78", CmlPrinterLA(nAlignCol3rd = Printer.ALIGN_RIGHT))
                oC_Printer.C_ADDxAddOrderCol("11w223545232ฟหกฟหกฟห", "น้ำไม่มีดีอะไรเลนจริงจริงน่ะครับ ","00.12345678", CmlPrinterLA(nAlignCol3rd = Printer.ALIGN_RIGHT))
                oC_Printer.C_ADDxAddUnderLine('=')
                oC_Printer.C_ADDxAddCut()
                oC_Printer.C_PRCbPrintData()
                oC_Printer.C_SETxDisconnectPrinter()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && resultCode == Activity.RESULT_OK) {
            val target = data.getStringExtra("target")
            if (target != null) {
                Log.d("TAGG", "onActivityResult: $target")
                oC_Printer = CPrinterCommand(target, this, this)
                oC_Printer.C_SETxInitializePrinter(Printer.TM_P20, Printer.MODEL_ANK)
            }
        }
    }

    override fun C_GETxGetData(pcResult: Double) {
        Toast.makeText(this, "value =" + pcResult.toString(), Toast.LENGTH_SHORT).show()
        demoFrag.visibility = View.GONE

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
}