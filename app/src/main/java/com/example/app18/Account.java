package com.example.app18;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_account")
    private int id_account;
    @ColumnInfo(name = "student_id")
    private int student_id;
    @ColumnInfo(name = "account_replenishment")
    private boolean replenishment; //true - пополнение \ false - расходы
    @ColumnInfo(name = "account_category")
    private int category; //0-Наличные,1-Переводы,2-Организации,3-Инвестиции
    @ColumnInfo(name = "account_money")
    private int money;

    public Account(int student_id, boolean replenishment, int category, int money) {
        this.student_id = student_id;
        this.replenishment = replenishment;
        this.category = category;
        this.money = money;
    }

    public int getId_account() {
        return id_account;
    }

    public void setId_account(int id_account) {
        this.id_account = id_account;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public boolean isReplenishment() {
        return replenishment;
    }

    public void setReplenishment(boolean replenishment) {
        this.replenishment = replenishment;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
