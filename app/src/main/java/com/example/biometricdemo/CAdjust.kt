package com.example.biometricdemo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.wcn_adjust.*
import kotlinx.android.synthetic.main.wcn_dis_chg.*
import kotlinx.android.synthetic.main.wcn_headbar.*
import kotlinx.android.synthetic.main.wcn_numpad.*
import java.lang.NumberFormatException

class CAdjust(
    var poViewType: CmlAdjust,
    var pnValue: Double,
    var poCon: Context,
    var poListener: CmlAdajustListener
) : Fragment(), View.OnClickListener {

    enum class CmlAdjust {
        ADJUST_PRICE, ADJUST_QTY
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wcn_adjust, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        oibCNChk.visibility = View.VISIBLE
        oibCNLeft.setImageResource(R.drawable.pcn_close)
        oetCNLast_Value.text = pnValue.toString().toEditable()
        when (poViewType) {
            CmlAdjust.ADJUST_PRICE -> {
                otvCNLast_Value.text = "Last Price"
                otvCNAdjust_To.text = "Adjust price to"
            }
            CmlAdjust.ADJUST_QTY -> {
                otvCNLast_Value.text = "Last Quality Value"
                otvCNAdjust_To.text = "Adjust quality to"
            }
        }

        ocmCNKey0.setOnClickListener(this)
        ocmCNKey1.setOnClickListener(this)
        ocmCNKey2.setOnClickListener(this)
        ocmCNKey3.setOnClickListener(this)
        ocmCNKey4.setOnClickListener(this)
        ocmCNKey5.setOnClickListener(this)
        ocmCNKey6.setOnClickListener(this)
        ocmCNKey7.setOnClickListener(this)
        ocmCNKey8.setOnClickListener(this)
        ocmCNKey9.setOnClickListener(this)
        ocmCNPoint.setOnClickListener(this)
        oibCNLeft.setOnClickListener(this)
        oibCNChk.setOnClickListener(this)
        oibCNKeyBackspace.setOnClickListener(this)

    }

    override fun onClick(poView: View?) {
        when (poView?.id) {
            R.id.ocmCNKey0 -> C_PRCxSetupView(0)
            R.id.ocmCNKey1 -> C_PRCxSetupView(1)
            R.id.ocmCNKey2 -> C_PRCxSetupView(2)
            R.id.ocmCNKey3 -> C_PRCxSetupView(3)
            R.id.ocmCNKey4 -> C_PRCxSetupView(4)
            R.id.ocmCNKey5 -> C_PRCxSetupView(5)
            R.id.ocmCNKey6 -> C_PRCxSetupView(6)
            R.id.ocmCNKey7 -> C_PRCxSetupView(7)
            R.id.ocmCNKey8 -> C_PRCxSetupView(8)
            R.id.ocmCNKey9 -> C_PRCxSetupView(9)
            R.id.ocmCNPoint -> C_PRCxSetupView(10)
            R.id.oibCNKeyBackspace -> C_PRCxSetupView(11)
            R.id.oibCNLeft -> {
                poListener.C_GETxGetData(pnValue)
                this.fragmentManager?.popBackStack()
            }
            R.id.oibCNChk -> {
                try {
                    if (oetCNAdjust_To.text.toString() != "") {
                        poListener.C_GETxGetData(oetCNAdjust_To.text.toString().toDouble())
                    }else{
                        poListener.C_GETxGetData(pnValue)
                    }
                    this.fragmentManager?.popBackStack()
                } catch (poe: NumberFormatException) {
                    Toast.makeText(
                        poCon,
                        "Please enter value!!$poe",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (poe: NotImplementedError) {
                    Toast.makeText(poCon, "Please set value!!$poe", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    private fun C_PRCxSetupView(pnNumber: Int) {
        var tTempString = oetCNAdjust_To.text.toString()
        oetCNAdjust_To.text = (
                if (pnNumber < 10) {
                    if (tTempString == "0") {
                        pnNumber.toString().toEditable()
                    } else {
                        (tTempString + pnNumber.toString()).toEditable()
                    }
                } else if (pnNumber == 10) {

                    if (tTempString.indexOfAny(".".toCharArray(), 0, true) != -1)
                        tTempString.toEditable()
                    else
                        ("$tTempString.").toEditable()

                } else if (pnNumber == 11) {
                    tTempString.dropLast(1).toEditable()
                } else
                    tTempString.toEditable()
                )
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)


    interface CmlAdajustListener {
        fun C_GETxGetData(pcResult: Double)
    }

}