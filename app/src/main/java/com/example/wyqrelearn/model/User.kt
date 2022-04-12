package com.example.wyqrelearn.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users_2")
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "first_name") var firstName: String = "",
    @ColumnInfo(name = "last_name_1") var lastName: String = "",
    @ColumnInfo(name = "age_1") var age: Int = 1,
    @Ignore var ignoreAge: Int = 2
)

