package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalHD (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rtShpCode") val rtShpCode : String,
	@SerializedName("rnXshDocType") val rnXshDocType : Int,
	@SerializedName("rdXshDocDate") val rdXshDocDate : String,
	@SerializedName("rtXshCshOrCrd") val rtXshCshOrCrd : String,
	@SerializedName("rtXshVATInOrEx") val rtXshVATInOrEx : String,
	@SerializedName("rtDptCode") val rtDptCode : String,
	@SerializedName("rtWahCode") val rtWahCode : String,
	@SerializedName("rtPosCode") val rtPosCode : String,
	@SerializedName("rtShfCode") val rtShfCode : String,
	@SerializedName("rnSdtSeqNo") val rnSdtSeqNo : Int,
	@SerializedName("rtUsrCode") val rtUsrCode : String,
	@SerializedName("rtSpnCode") val rtSpnCode : String,
	@SerializedName("rtXshApvCode") val rtXshApvCode : String,
	@SerializedName("rtCstCode") val rtCstCode : String,
	@SerializedName("rtXshDocVatFull") val rtXshDocVatFull : String,
	@SerializedName("rtXshRefExt") val rtXshRefExt : String,
	@SerializedName("rdXshRefExtDate") val rdXshRefExtDate : String,
	@SerializedName("rtXshRefInt") val rtXshRefInt : String,
	@SerializedName("rdXshRefIntDate") val rdXshRefIntDate : String,
	@SerializedName("rtXshRefAE") val rtXshRefAE : String,
	@SerializedName("rnXshDocPrint") val rnXshDocPrint : Int,
	@SerializedName("rtRteCode") val rtRteCode : String,
	@SerializedName("rcXshRteFac") val rcXshRteFac : Int,
	@SerializedName("rcXshTotal") val rcXshTotal : Int,
	@SerializedName("rcXshTotalNV") val rcXshTotalNV : Int,
	@SerializedName("rcXshTotalNoDis") val rcXshTotalNoDis : Int,
	@SerializedName("rcXshTotalB4DisChgV") val rcXshTotalB4DisChgV : Int,
	@SerializedName("rcXshTotalB4DisChgNV") val rcXshTotalB4DisChgNV : Int,
	@SerializedName("rtXshDisChgTxt") val rtXshDisChgTxt : String,
	@SerializedName("rcXshDis") val rcXshDis : Int,
	@SerializedName("rcXshChg") val rcXshChg : Int,
	@SerializedName("rcXshTotalAfDisChgV") val rcXshTotalAfDisChgV : Int,
	@SerializedName("rcXshTotalAfDisChgNV") val rcXshTotalAfDisChgNV : Int,
	@SerializedName("rcXshRefAEAmt") val rcXshRefAEAmt : Int,
	@SerializedName("rcXshAmtV") val rcXshAmtV : Int,
	@SerializedName("rcXshAmtNV") val rcXshAmtNV : Int,
	@SerializedName("rcXshVat") val rcXshVat : Int,
	@SerializedName("rcXshVatable") val rcXshVatable : Int,
	@SerializedName("rtXshWpCode") val rtXshWpCode : String,
	@SerializedName("rcXshWpTax") val rcXshWpTax : Int,
	@SerializedName("rcXshGrand") val rcXshGrand : Int,
	@SerializedName("rcXshRnd") val rcXshRnd : Int,
	@SerializedName("rtXshGndText") val rtXshGndText : String,
	@SerializedName("rcXshPaid") val rcXshPaid : Int,
	@SerializedName("rcXshLeft") val rcXshLeft : Int,
	@SerializedName("rtXshRmk") val rtXshRmk : String,
	@SerializedName("rtXshStaRefund") val rtXshStaRefund : String,
	@SerializedName("rtXshStaDoc") val rtXshStaDoc : String,
	@SerializedName("rtXshStaApv") val rtXshStaApv : String,
	@SerializedName("rtXshStaPrcStk") val rtXshStaPrcStk : String,
	@SerializedName("rtXshStaPaid") val rtXshStaPaid : String,
	@SerializedName("rnXshStaDocAct") val rnXshStaDocAct : Int,
	@SerializedName("rnXshStaRef") val rnXshStaRef : Int,
	@SerializedName("rdLastUpdOn") val rdLastUpdOn : String,
	@SerializedName("rtLastUpdBy") val rtLastUpdBy : String,
	@SerializedName("rdCreateOn") val rdCreateOn : String,
	@SerializedName("rtCreateBy") val rtCreateBy : String
)