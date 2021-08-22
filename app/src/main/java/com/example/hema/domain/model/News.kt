package com.example.hema.domain.model


import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Nullable

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true) var pk: Int,
    @Embedded @SerializedName("source") var source : Source,
    @ColumnInfo(defaultValue = " ") @SerializedName("author") var author : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("title") var title : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("description") var description : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("url") var url : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("urlToImage") var urlToImage : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("publishedAt") var publishedAt : String?,
    @ColumnInfo(defaultValue = " ") @SerializedName("content") var content : String?
    ):Serializable{
        constructor() : this(0,Source("",""),"","","","","","","",)
    }
