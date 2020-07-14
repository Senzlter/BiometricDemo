package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTCNTMemTxnSale (

	@SerializedName("rtCgpCode") val rtCgpCode : String,
	@SerializedName("rtMemCode") val rtMemCode : String,
	@SerializedName("rtTxnRefDoc") val rtTxnRefDoc : String,
	@SerializedName("rtTxnRefInt") val rtTxnRefInt : String,
	@SerializedName("rtTxnRefSpl") val rtTxnRefSpl : String,
	@SerializedName("rdTxnRefDate") val rdTxnRefDate : String,
	@SerializedName("rcTxnRefGrand") val rcTxnRefGrand : Int,
	@SerializedName("rcTxnPntOptBuyAmt") val rcTxnPntOptBuyAmt : Int,
	@SerializedName("rcTxnPntOptGetQty") val rcTxnPntOptGetQty : Int,
	@SerializedName("rcTxnPntB4Bill") val rcTxnPntB4Bill : Int,
	@SerializedName("rdTxnPntStart") val rdTxnPntStart : String,
	@SerializedName("rdTxnPntExpired") val rdTxnPntExpired : String,
	@SerializedName("rcTxnPntBillQty") val rcTxnPntBillQty : Int,
	@SerializedName("rcTxnPntUsed") val rcTxnPntUsed : Int,
	@SerializedName("rcTxnPntExpired") val rcTxnPntExpired : Int,
	@SerializedName("rtTxnPntStaClosed") val rtTxnPntStaClosed : String,
	@SerializedName("rdLastUpdOn") val rdLastUpdOn : String,
	@SerializedName("rtLastUpdBy") val rtLastUpdBy : String,
	@SerializedName("rdCreateOn") val rdCreateOn : String,
	@SerializedName("rtCreateBy") val rtCreateBy : String,
	@SerializedName("rtTxnPntDocType") val rtTxnPntDocType : String
)