package com.outside.oshabelist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "neta")
data class Neta(@PrimaryKey val id: String = "", val neta: String = "")
