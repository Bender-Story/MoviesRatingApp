package com.android.rahul.movies.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.android.rahul.movies.model.Result

@Dao
interface ResultDao {
    @Query("SELECT * FROM result")
    fun getAll(): List<Result>

    @Insert
    fun insertAll(vararg result: Result?)

    @Delete
    fun delete(result: Result)

    @Query("DELETE FROM result")
    fun deleteAll()
}