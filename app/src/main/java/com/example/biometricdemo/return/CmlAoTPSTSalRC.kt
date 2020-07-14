package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlAoTPSTSalRC (

	@SerializedName("rtBchCode") val rtBchCode : String,
	@SerializedName("rtXshDocNo") val rtXshDocNo : String,
	@SerializedName("rnXrcSeqNo") val rnXrcSeqNo : Int,
	@SerializedName("rtRcvCode") val rtRcvCode : String,
	@SerializedName("rtRcvName") val rtRcvName : String,
	@SerializedName("rtXrcRefNo1") val rtXrcRefNo1 : String,
	@SerializedName("rtXrcRefNo2") val rtXrcRefNo2 : String,
	@SerializedName("rdXrcRefDate") val rdXrcRefDate : String,
	@SerializedName("rtXrcRefDesc") val rtXrcRefDesc : String,
	@SerializedName("rtBnkCode") val rtBnkCode : String,
	@SerializedName("rtRteCode") val rtRteCode : String,
	@SerializedName("rcXrcRteFac") val rcXrcRteFac : Int,
	@SerializedName("rcXrcFrmLeftAmt") val rcXrcFrmLeftAmt : Int,
	@SerializedName("rcXrcUsrPayAmt") val rcXrcUsrPayAmt : Int,
	@SerializedName("rcXrcDep") val rcXrcDep : Int,
	@SerializedName("rcXrcNet") val rcXrcNet : Int,
	@SerializedName("rcXrcChg") val rcXrcChg : Int,
	@SerializedName("rtXrcRmk") val rtXrcRmk : String,
	@SerializedName("rtPhwCode") val rtPhwCode : String,
	@SerializedName("rtXrcRetDocRef") val rtXrcRetDocRef : String,
	@SerializedName("rtXrcStaPayOffline") val rtXrcStaPayOffline : String,
	@SerializedName("rdLastUpdOn") val rdLastUpdOn : String,
	@SerializedName("rtLastUpdBy") val rtLastUpdBy : String,
	@SerializedName("rdCreateOn") val rdCreateOn : String,
	@SerializedName("rtCreateBy") val rtCreateBy : String
)