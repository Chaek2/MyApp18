package com.example.app18;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface AccountDao {
    @Insert
    void InsertAccount(Account account);
    @Update
    void UpdateAccount(Account account);
    @Delete
    void DeleteAccount(Account account);
}
