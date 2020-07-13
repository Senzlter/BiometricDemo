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

    lateinit var oC_Printer : CPrinterCommand

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAGG", "onCreate: ")
        setContentView(R.layout.activity_main)
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

        runPrint.setOnClickListener {
            if (oC_Printer != null){
                oC_Printer.C_SETbConnectPrinter()
                oC_Printer.C_ADDxAddSingleLine("test single line and test printer")
                /*oC_Printer.C_ADDxAddSingleLine("Test Single line with aline and size", Printer.ALIGN_RIGHT, 2)
                oC_Printer.C_ADDxAddUnderLine('=')
                oC_Printer.C_ADDxAddOrder("order 1 try to write over limit", 10.12345,"Bath" )
                oC_Printer.C_ADDxAddOrder("order 2", 10.12345,"Bath" )
                oC_Printer.C_ADDxAddOrder("order 3 try to write limit", 10.12345,"Bath" )
                oC_Printer.C_ADDxAddOrder("order 4", 10.12345,"Bath" )
                oC_Printer.C_ADDxAddOrder("or", 10.1234567,"Bath" , 2)
                oC_Printer.C_ADDxAddOrder("order 3with increase size to 2", 10.1234567,"Bath" , 2)
                oC_Printer.C_ADDxAddOrder("o001", 10.1234567,"Bath" , 2)
                oC_Printer.C_ADDxAddOrder("order 5ith increase size to 2", 10.1234567,"Bath" , 2)

                var x = BitmapFactory.decodeResource(resources, R.drawable.p01life).resizeByWidth(400)
                oC_Printer.C_ADDxAddBitmap(x)
                 x = BitmapFactory.decodeResource(resources, R.drawable.p01pirates).resizeByWidth(400)
                oC_Printer.C_ADDxAddBitmap(x,2, Printer.ALIGN_RIGHT)
                oC_Printer.C_ADDxAddBarcode("12345", Printer.BARCODE_CODE39, Printer.HRI_BOTH, Printer.FONT_A, 2, 100)
                oC_Printer.C_ADDxAddBarcode(Printer.ALIGN_RIGHT,"12345123", Printer.BARCODE_CODE39, Printer.HRI_BOTH, Printer.FONT_A, 2, 100)
*/
                oC_Printer.C_ADDxAddColumnLine("123456789012345", arrayListOf("10", "20", "3123"))
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

    fun Bitmap.resizeByWidth(width:Int):Bitmap{
        val ratio:Float = this.width.toFloat() / this.height.toFloat()
        val height:Int = Math.round(width / ratio)

        return Bitmap.createScaledBitmap(
            this,
            width,
            height,
            false
        )
    }
}