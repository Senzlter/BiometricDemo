package com.adasoft.adapos5mobile.model.`return`

import com.google.gson.annotations.SerializedName

data class CmlReturnBase (

    @SerializedName("roItem") val roItem : CmlRoItem,
    @SerializedName("rtCode") val rtCode : String,
    @SerializedName("rtDesc") val rtDesc : String
)