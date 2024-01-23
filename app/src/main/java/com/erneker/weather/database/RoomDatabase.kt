package com.erneker.weather.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("select * from location WHERE name = :name")
    fun getLocationByName(name: String): Flow<LocationDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( location: LocationDTO)
}

@Database(entities = [LocationDTO::class], version = 1)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao
}

private lateinit var INSTANCE: MyRoomDatabase

fun getDatabase(context: Context): MyRoomDatabase {
    synchronized(MyRoomDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MyRoomDatabase::class.java,
                "my_room_database").build()
        }
    }
    return INSTANCE
}