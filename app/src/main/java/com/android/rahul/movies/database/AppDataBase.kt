package com.android.rahul.movies.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.rahul.movies.model.Result

@Database(entities = [Result::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao
}