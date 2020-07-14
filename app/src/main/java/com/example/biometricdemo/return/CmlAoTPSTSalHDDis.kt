package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalHDDis (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rdXhdDateIns") val rdXhdDateIns : String,
	@SerializedName("rtXhdDisChgTxt") val rtXhdDisChgTxt : String,
	@SerializedName("rtXhdDisChgType") val rtXhdDisChgType : String,
	@SerializedName("rcXhdTotalAfDisChg") val rcXhdTotalAfDisChg : Int,
	@SerializedName("rcXhdDisChg") val rcXhdDisChg : Int,
	@SerializedName("rcXhdAmt") val rcXhdAmt : Int,
	@SerializedName("rtXhdRefCode") val rtXhdRefCode : String,
	@SerializedName("rtDisCode") val rtDisCode : String,
	@SerializedName("rtRsnCode") val rtRsnCode : String
)