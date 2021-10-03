package com.outside.oshabelist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.outside.oshabelist.dao.NetaDao
import com.outside.oshabelist.data.Neta

@Database(entities = [Neta::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun netaDao(): NetaDao
}