package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalRD (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rnXrdSeqNo") val rnXrdSeqNo : Int,
	@SerializedName("rtRdhDocType") val rtRdhDocType : String,
	@SerializedName("rnXrdRefSeq") val rnXrdRefSeq : Int,
	@SerializedName("rtXrdRefCode") val rtXrdRefCode : String,
	@SerializedName("rcXrdPdtQty") val rcXrdPdtQty : Int,
	@SerializedName("rnXrdPntUse") val rnXrdPntUse : Int
)