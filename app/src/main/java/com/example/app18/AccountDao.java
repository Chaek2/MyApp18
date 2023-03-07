package com.example.app18;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    void InsertAccount(Account account);
    @Update
    void UpdateAccount(Account account);
    @Delete
    void DeleteAccount(Account account);
    @Query("Select * from account where account_replenishment = :replenishment and student_id = :student_id")
    List<Account>  getAccountReplenishment(boolean replenishment,int student_id);
    @Query("Select * from account  where student_id = :student_id")
    List<Account> getAccountStudentID(int student_id);

}
