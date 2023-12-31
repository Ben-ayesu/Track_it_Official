package com.example.trackitofficial.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trackitofficial.data.db.dao.WorkoutDao
import com.example.trackitofficial.data.db.entities.Workout
import com.example.trackitofficial.utils.DateObjectConverter

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Workout::class], version = 2, exportSchema = false)
@TypeConverters(DateObjectConverter::class)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao

    companion object {
        @Volatile
        private var Instance: WorkoutDatabase? = null

        fun getDatabase(context: Context): WorkoutDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, WorkoutDatabase::class.java, "workout_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}