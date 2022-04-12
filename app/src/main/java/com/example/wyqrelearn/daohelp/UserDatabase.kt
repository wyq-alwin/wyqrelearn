package com.example.wyqrelearn.daohelp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.wyqrelearn.model.User


@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private var instance: UserDatabase? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(UserDatabase::class) {
                instance ?: Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "users-db"
                ).allowMainThreadQueries()
                    .addMigrations(MIGRATION_3_4)
                    .build().also { instance = it }
            }
    }

    abstract fun userDao(): UserDao
}

val MIGRATION_3_4: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //修改表名称
        database.execSQL("ALTER TABLE `users` RENAME TO `users_2`")
    }
}
