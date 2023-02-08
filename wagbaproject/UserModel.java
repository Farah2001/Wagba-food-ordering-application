package com.example.wagbaproject;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {
    //pk
    @PrimaryKey(autoGenerate = true)
    private int key;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "userName")
    private String Usrname;

    @ColumnInfo(name = "Phone Number")
    private String Pnum;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsrname() {
        return Usrname;
    }

    public void setUsrname(String usrname) {
        Usrname = usrname;
    }

    public String getPnum() {
        return Pnum;
    }

    public void setPnum(String pnum) {
        Pnum = pnum;
    }
}
