package com.sega.weatherreport.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class SysEntity(
    val type: Long,
    @ColumnInfo(name = "sys_id")
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
