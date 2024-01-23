package com.erneker.weather.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("select * from location WHERE name = :name")
    fun getLocationByName(name: String): Flow<LocationDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( location: LocationDTO)
}

@Database(entities = [LocationDTO::class], version = 2)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao
}

private lateinit var INSTANCE: MyRoomDatabase

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since we are adding columns, we can use the SQL ALTER TABLE command
        database.execSQL("ALTER TABLE location ADD COLUMN temp REAL NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE location ADD COLUMN pressure INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE location ADD COLUMN humidity INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE location ADD COLUMN windSpeed REAL NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE location ADD COLUMN visibility INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE location ADD COLUMN timestamp TEXT NOT NULL DEFAULT ''")
    }
}

fun getDatabase(context: Context): MyRoomDatabase {
    synchronized(MyRoomDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MyRoomDatabase::class.java,
                "my_room_database")
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }
    return INSTANCE
}