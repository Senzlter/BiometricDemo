package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlRoItem (

	@SerializedName("aoTPSTSalHD") val aoTPSTSalHD : ArrayList<CmlAoTPSTSalHD>,
	@SerializedName("aoTPSTSalHDCst") val aoTPSTSalHDCst : ArrayList<CmlAoTPSTSalHDCst>,
	@SerializedName("aoTPSTSalHDDis") val aoTPSTSalHDDis : ArrayList<CmlAoTPSTSalHDDis>,
	@SerializedName("aoTPSTSalDT") val aoTPSTSalDT : ArrayList<CmlAoTPSTSalDT>,
	@SerializedName("aoTPSTSalDTDis") val aoTPSTSalDTDis : ArrayList<CmlAoTPSTSalDTDis>,
	@SerializedName("aoTPSTSalRC") val aoTPSTSalRC : ArrayList<CmlAoTPSTSalRC>,
	@SerializedName("aoTPSTSalRD") val aoTPSTSalRD : ArrayList<CmlAoTPSTSalRD>,
	@SerializedName("aoTPSTSalPD") val aoTPSTSalPD : ArrayList<CmlAoTPSTSalPD>,
	@SerializedName("aoTCNTMemTxnSale") val aoTCNTMemTxnSale : ArrayList<CmlAoTCNTMemTxnSale>,
	@SerializedName("aoTCNTMemTxnRedeem") val aoTCNTMemTxnRedeem : ArrayList<CmlAoTCNTMemTxnRedeem>
)