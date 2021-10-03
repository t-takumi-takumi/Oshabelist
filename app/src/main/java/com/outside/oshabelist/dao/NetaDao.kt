package com.outside.oshabelist.dao

import androidx.room.*
import com.outside.oshabelist.data.Neta

@Dao
interface NetaDao {
    @Query("select * from neta where id = :id")
    fun getNeta(id: String): Neta

    @Insert
    fun insert(neta: Neta)

    @Update
    fun update(neta: Neta)

    @Delete
    fun delete(neta: Neta)
}