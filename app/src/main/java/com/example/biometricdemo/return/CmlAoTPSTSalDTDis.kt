package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName


data class CmlAoTPSTSalDTDis (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rnXsdSeqNo") val rnXsdSeqNo : Int,
	@SerializedName("rdXddDateIns") val rdXddDateIns : String,
	@SerializedName("rnXddStaDis") val rnXddStaDis : Int,
	@SerializedName("rtXddDisChgTxt") val rtXddDisChgTxt : String,
	@SerializedName("rtXddDisChgType") val rtXddDisChgType : String,
	@SerializedName("rcXddNet") val rcXddNet : Int,
	@SerializedName("rcXddValue") val rcXddValue : Int,
	@SerializedName("rtXddRefCode") val rtXddRefCode : String,
	@SerializedName("rtDisCode") val rtDisCode : String,
	@SerializedName("rtRsnCode") val rtRsnCode : String
)