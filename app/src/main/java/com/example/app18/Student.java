package com.example.app18;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_student")
    private int id_st;
    @ColumnInfo(name = "student_login")
    private String login;
    @ColumnInfo(name = "student_password")
    private String password;

    public Student(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int getId_st() {
        return id_st;
    }

    public void setId_st(int id_st) {
        this.id_st = id_st;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
