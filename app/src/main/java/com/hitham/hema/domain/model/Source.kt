package com.hitham.hema.domain.model

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class Source (
    @ColumnInfo(defaultValue = " ") @SerializedName("id") val id : String,
    @ColumnInfo(defaultValue = " ") @SerializedName("name") val name : String
): Serializable