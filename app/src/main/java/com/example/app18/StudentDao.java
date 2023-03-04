package com.example.app18;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void InsertStudent(Student student);
    @Update
    void UpdateStudent(Student student);
    @Delete
    void DeleteStudent(Student student);
    @Query("Select * from student where student_login = :login")
    Student getStudent(String login);
    @Query("Select * from student")
    List<Student> getAllStudent();
    @Query("Select * from student where student_login = :login and student_password = :password")
    Student AutoStudent(String login, String password);
    @Query("DELETE FROM student where id_student > -1")
    public void DeleteAllStudent();
}
