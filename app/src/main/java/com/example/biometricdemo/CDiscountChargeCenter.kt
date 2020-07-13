package com.example.biometricdemo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.biometricdemo.CDiscountChargeCenter.CmlDCType.*
import kotlinx.android.synthetic.main.wcn_dis_chg.*
import kotlinx.android.synthetic.main.wcn_headbar.*
import kotlinx.android.synthetic.main.wcn_numpad.*
import java.lang.NumberFormatException

class CDiscountChargeCenter(
    var poViewType: CmlDCType,
    var pnValue: Double,
    var poCon: Context,
    var poListener: CmlDCListener
) : Fragment(), View.OnClickListener,
    TextWatcher {

    enum class CmlDCType {
        DISCOUNT_PERCENT, DISCOUNT_VALUE, CHARGE_PERCENT, CHARGE_VALUE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.wcn_dis_chg, container, false)
    }

    override fun onViewCreated(poView: View, savedInstanceState: Bundle?) {
        oetCNValue_b4_Edit.text = pnValue.toEditable()
        oibCNChk.visibility = View.VISIBLE
        otvCNValue_4_Cal.text = "0"
        oibCNLeft.setImageResource(R.drawable.pcn_close)
        when (poViewType) {
            DISCOUNT_PERCENT -> {
                otvCNHead.text = "Discount with Percent"
                otvCNValue_b4_Edit.text = "Value before discount"
                otvCNValue_4_Edit.text = "Discount %"
                otvCNValue_af_Edit.text = "Value after discount"
            }
            DISCOUNT_VALUE -> {
                otvCNHead.text = "Discount with Value"
                otvCNValue_b4_Edit.text = "Value before discount"
                otvCNValue_4_Edit.text = "Discount value"
                otvCNValue_af_Edit.text = "Value after discount"
            }
            CHARGE_PERCENT -> {
                otvCNHead.text = "Charge with Percent"
                otvCNValue_b4_Edit.text = "Value before Charge"
                otvCNValue_4_Edit.text = "Charge %"
                otvCNValue_af_Edit.text = "Value after Charge"
            }
            CHARGE_VALUE -> {
                otvCNHead.text = "Charge with Value"
                otvCNValue_b4_Edit.text = "Value before Charge"
                otvCNValue_4_Edit.text = "Charge Value"
                otvCNValue_af_Edit.text = "Value after Charge"
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
        oibCNKeyBackspace.setOnClickListener(this)
        oibCNChk.setOnClickListener(this)
        oetCNValue_4_Edit.addTextChangedListener(this)


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
                    poListener.C_GETxGetData(oetCNValue_af_Edit.text.toString().toDouble())
                    this.fragmentManager?.popBackStack()
                } catch (poe: NumberFormatException) {
                    Toast.makeText(
                        poCon,
                        "Please enter value!!" + poe.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (poe: NotImplementedError) {
                    Toast.makeText(poCon, "Please set value!!" + poe.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    override fun onTextChanged(pcChar: CharSequence?, pnStart: Int, pnBefore: Int, pnCount: Int) {
        var nValue: Double = 0.0
        if (pcChar?.length!! > 0) {
            var tFValue = pcChar.toString()
            nValue = if (tFValue.isEmpty()) 0.00 else {
                if (tFValue.equals("."))
                    0.0
                else
                    tFValue.toDouble()
            }
            tFValue.toEditable()
            oetCNValue_af_Edit.text =
                if (nValue > 0) (pnValue - (pnValue * (nValue / 100))).toEditable()
                else pnValue.toEditable()
        } else {
            oetCNValue_af_Edit.text = pnValue.toEditable()
        }

        otvCNValue_4_Cal.text = if (nValue > 0) {
            var nTempValue = 0.0
            when (poViewType) {
                DISCOUNT_PERCENT -> nTempValue = (pnValue * (nValue / 100))
                DISCOUNT_VALUE -> nTempValue = (nValue)
                CHARGE_PERCENT -> nTempValue = (pnValue * (nValue / 100))
                CHARGE_VALUE -> nTempValue = (nValue)
                else -> nValue.toEditable()
            }

            String.format("%.2f", nTempValue)
        } else "0".toEditable()
    }

    private fun C_PRCxSetupView(pnNumber: Int) {
        var nValue: Double = 0.0
        var tTempString = oetCNValue_4_Edit.text.toString()
        oetCNValue_4_Edit.text = (
                if (pnNumber < 10) {
                    if (tTempString == "0") {
                        nValue += pnNumber.toDouble()
                        pnNumber.toString().toEditable()
                    } else {
                        nValue =
                            (tTempString + pnNumber.toString()).toDouble()
                        Log.d("TAGG", "C_SETxSetNumtoView: nValue :$nValue")
                        (tTempString + pnNumber.toString()).toEditable()
                    }
                } else if (pnNumber == 10) {

                    if (tTempString.indexOfAny(".".toCharArray(), 0, true) != -1
                    )
                        tTempString.toEditable()
                    else
                        ("$tTempString.").toEditable()

                } else if (pnNumber == 11) {
                    var tFValue = tTempString.dropLast(1)
                    nValue = if (tFValue.isEmpty()) 0.00 else {
                        if (tFValue == ".") 0.0 else tFValue.toDouble()
                    }
                    tFValue.toEditable()
                } else {
                    var tFValue = tTempString
                    nValue = if (tFValue.isEmpty()) 0.00 else {
                        if (tFValue == ".")
                            0.0
                        else
                            tFValue.toDouble()
                    }
                    tFValue.toEditable()
                })

        oetCNValue_af_Edit.text = if (nValue > 0) {
            var nTempValue = when (poViewType) {
                DISCOUNT_PERCENT -> (pnValue - (pnValue * (nValue / 100)))
                DISCOUNT_VALUE -> (pnValue - nValue)
                CHARGE_PERCENT -> (pnValue + (pnValue * (nValue / 100)))
                CHARGE_VALUE -> (pnValue + nValue)
                else -> pnValue
            }
            String.format("%.2f", nTempValue).toEditable()

        } else pnValue.toEditable()

        otvCNValue_4_Cal.text = if (nValue > 0) {
            var nTempValue = when (poViewType) {
                DISCOUNT_PERCENT -> (pnValue * (nValue / 100))
                DISCOUNT_VALUE -> (nValue)
                CHARGE_PERCENT -> (pnValue * (nValue / 100))
                CHARGE_VALUE -> (nValue)
                else -> nValue
            }

            String.format("%.2f", nTempValue)
        } else "0".toEditable()

        oetCNValue_4_Edit.setSelection(oetCNValue_4_Edit.text.length)
    }

    private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun Double.toEditable(): Editable =
        Editable.Factory.getInstance().newEditable(this.toString())

    interface CmlDCListener {
        fun C_GETxGetData(pcResult: Double)
    }

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}