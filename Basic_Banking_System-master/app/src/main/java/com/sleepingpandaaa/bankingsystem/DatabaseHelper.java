package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.dbms", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase dbms) {
        dbms.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        dbms.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        dbms.execSQL("insert into user_table values(9000000000,'Sudhanshoo',1500.01,'upadhyaysudhanshoo@gmail.com','XXXXXXXXXXXX8078','A2305318078')");
        dbms.execSQL("insert into user_table values(9111111111,'Rohini',74222.67,'rohini@gmail.com','XXXXXXXXXXXX8081','A2305318081')");
        dbms.execSQL("insert into user_table values(9222222222,'Vivisha',89313.56,'vivisha@gmail.com','XXXXXXXXXXXX7423','A2305317031')");
        dbms.execSQL("insert into user_table values(9333333333,'Ashir',60943.01,'ashir@gmail.com','XXXXXXXXXXXX8091','A2305318091')");
        dbms.execSQL("insert into user_table values(9444444444,'Shashank',132456.48,'shashank@gmail.com','XXXXXXXXXXXX8086','A2305316086')");
        dbms.execSQL("insert into user_table values(9555555555,'Issac',94511.16,'issac@gmail.com','XXXXXXXXXXXX5155','AB32145155')");
        dbms.execSQL("insert into user_table values(9666666666,'Safwan',59361.00,'safwan@gmail.com','XXXXXXXXXXXX3456','ACD123456')");
        dbms.execSQL("insert into user_table values(9777777777,'Vaishnavi',85327.22,'vaishnavi@gmail.com','XXXXXXXXXXXX9876','BCA32109876')");
        dbms.execSQL("insert into user_table values(9888888888,'Vanshika',43928.46,'vanshika@gmail.com','XXXXXXXXXXXX8765','CAB21098765')");
        dbms.execSQL("insert into user_table values(9999999999,'Deepak',27311.90,'deepak@gmail.com','XXXXXXXXXXXX7654','ABC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbms, int oldVersion, int newVersion) {
        dbms.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        dbms.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(dbms);
    }

    public Cursor readalldata(){
        SQLiteDatabase dbms = this.getWritableDatabase();
        Cursor cursor = dbms.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase dbms = this.getWritableDatabase();
        Cursor cursor = dbms.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase dbms = this.getWritableDatabase();
        Cursor cursor = dbms.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase dbms = this.getWritableDatabase();
        dbms.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase dbms = this.getWritableDatabase();
        Cursor cursor = dbms.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status) {
        SQLiteDatabase dbms = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = dbms.insert(TABLE_NAME1, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }}
