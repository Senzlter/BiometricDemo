package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalPD (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rtPmhDocNo") val rtPmhDocNo : String,
	@SerializedName("rnXsdSeqNo") val rnXsdSeqNo : Int,
	@SerializedName("rtPmdGrpName") val rtPmdGrpName : String,
	@SerializedName("rtPdtCode") val rtPdtCode : String,
	@SerializedName("rtPunCode") val rtPunCode : String,
	@SerializedName("rcXsdQty") val rcXsdQty : Int,
	@SerializedName("rcXsdQtyAll") val rcXsdQtyAll : Int,
	@SerializedName("rcXsdSetPrice") val rcXsdSetPrice : Int,
	@SerializedName("rcXsdNet") val rcXsdNet : Int,
	@SerializedName("rcXpdGetQtyDiv") val rcXpdGetQtyDiv : Int,
	@SerializedName("rtXpdGetType") val rtXpdGetType : String,
	@SerializedName("rcXpdGetValue") val rcXpdGetValue : Int,
	@SerializedName("rcXpdDis") val rcXpdDis : Int,
	@SerializedName("rcXpdPerDisAvg") val rcXpdPerDisAvg : Int,
	@SerializedName("rcXpdDisAvg") val rcXpdDisAvg : Int,
	@SerializedName("rcXpdPoint") val rcXpdPoint : Int,
	@SerializedName("rtXpdStaRcv") val rtXpdStaRcv : String,
	@SerializedName("rtPplCode") val rtPplCode : String,
	@SerializedName("rtXpdCpnText") val rtXpdCpnText : String,
	@SerializedName("rtCpdBarCpn") val rtCpdBarCpn : String
)