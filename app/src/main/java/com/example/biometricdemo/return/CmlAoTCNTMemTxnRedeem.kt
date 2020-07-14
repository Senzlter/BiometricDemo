package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTCNTMemTxnRedeem (

	@SerializedName("rtCgpCode") val rtCgpCode : String,
	@SerializedName("rtMemCode") val rtMemCode : String,
	@SerializedName("rtRedRefDoc") val rtRedRefDoc : String,
	@SerializedName("rtRedRefSpl") val rtRedRefSpl : String,
	@SerializedName("rtRedRefInt") val rtRedRefInt : String,
	@SerializedName("rdRedRefDate") val rdRedRefDate : String,
	@SerializedName("rcRedPntB4Bill") val rcRedPntB4Bill : Int,
	@SerializedName("rcRedPntBillQty") val rcRedPntBillQty : Int,
	@SerializedName("rtRedPntStaClosed") val rtRedPntStaClosed : String,
	@SerializedName("rdRedPntStart") val rdRedPntStart : String,
	@SerializedName("rdRedPntExpired") val rdRedPntExpired : String,
	@SerializedName("rdLastUpdOn") val rdLastUpdOn : String,
	@SerializedName("rtLastUpdBy") val rtLastUpdBy : String,
	@SerializedName("rdCreateOn") val rdCreateOn : String,
	@SerializedName("rtCreateBy") val rtCreateBy : String,
	@SerializedName("rtRedPntDocType") val rtRedPntDocType : String
)