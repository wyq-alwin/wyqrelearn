package com.example.wyqrelearn.daohelp

import androidx.room.*
import com.example.wyqrelearn.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users_2")
    fun getAll(): List<User>

    @Query("SELECT * FROM users_2 WHERE uid IN (:userIds)")
    fun getAllByIds(userIds: IntArray): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}
