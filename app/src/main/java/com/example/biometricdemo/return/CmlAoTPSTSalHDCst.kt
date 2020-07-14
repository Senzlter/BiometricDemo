package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalHDCst (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rtXshCardID") val rtXshCardID : String,
	@SerializedName("rtXshCardNo") val rtXshCardNo : String,
	@SerializedName("rnXshCrTerm") val rnXshCrTerm : Int,
	@SerializedName("rdXshDueDate") val rdXshDueDate : String,
	@SerializedName("rdXshBillDue") val rdXshBillDue : String,
	@SerializedName("rtXshCtrName") val rtXshCtrName : String,
	@SerializedName("rdXshTnfDate") val rdXshTnfDate : String,
	@SerializedName("rtXshRefTnfID") val rtXshRefTnfID : String,
	@SerializedName("rnXshAddrShip") val rnXshAddrShip : Int,
	@SerializedName("rnXshAddrTax") val rnXshAddrTax : Int,
	@SerializedName("rtXshCstName") val rtXshCstName : String,
	@SerializedName("rtXshCstTel") val rtXshCstTel : String,
	@SerializedName("rcXshCstPnt") val rcXshCstPnt : Int,
	@SerializedName("rcXshCstPntPmt") val rcXshCstPntPmt : Int
)