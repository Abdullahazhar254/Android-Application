package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class userDatabase extends SQLiteOpenHelper {

    public static final String db_name="bankapp.db";
    public static final String table1_name="bankuser";
    public static final String t1_col1="u_id";
    public static final String t1_col2="u_name";
    public static final String t1_col3="u_email";
    public static final String t1_col4="u_password";
    public static final String t1_col5="u_contact";
    public static final String t1_col6="u_account";
    public static final String t1_col7="u_balance";

    public static final String table2_name="usertransaction";
    public static final String t2_col1="t_id";
    public static final String t2_col2="t_amount";
    public static final String t2_col3="t_sender";
    public static final String t2_col4="t_receiver";

    SQLiteDatabase db;

    public userDatabase(@Nullable Context context) {
        super(context, db_name,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+table1_name+" ("+t1_col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+t1_col2+" TEXT,"+t1_col3+" TEXT,"+t1_col4+" TEXT,"+t1_col5+" TEXT,"+t1_col6+" INTEGER,"+t1_col7+" INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+table2_name+" ("+t2_col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+t2_col2+" INTEGER,"+t2_col3+" INTEGER,"+t2_col4+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1_name);
        db.execSQL("DROP TABLE IF EXISTS "+table2_name);
        onCreate(db);
    }

    public boolean user_InsertData(String name,String email,String pass,String contact,int account,int balance){
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(t1_col2,name);
        contentValues.put(t1_col3,email);
        contentValues.put(t1_col4,pass);
        contentValues.put(t1_col5,contact);
        contentValues.put(t1_col6,account);
        contentValues.put(t1_col7,balance);

        long result=db.insert(table1_name,null,contentValues);
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor user_GetAllData()
    {
        db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+table1_name,null);
        return result;
    }

    public Cursor user_GetData(long userid)
    {
        db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+table1_name+" WHERE "+t1_col1+" = "+userid,null);
        return result;
    }

    public boolean user_Signin(String email,String pass){
        db=this.getWritableDatabase();
        String[] columns = {
                t1_col1
        };

        String selection = t1_col3 + " = ?" + " AND " + t1_col4 + " = ?";

        String[] selectionArgs = {email, pass};
        Cursor result= db.query(table1_name,columns,selection,selectionArgs,null,null,null);
        int cursorCount= result.getCount();
        if(cursorCount>0){
            return true;
        }
        else {
            return false;
        }
    }

    public long getUserId (String email){
        long userId = 0;
        db = this.getWritableDatabase();
        String[] columns = {
                t1_col1
        };
        String selection = t1_col3 + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(table1_name, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        userId = cursor.getLong(0);

        if (cursorCount > 0){
            return userId;
        }
        return userId;
    }

    public int getAccountNo (long userid){
        int accountNo = 0;
        db = this.getWritableDatabase();
        String[] columns = {
                t1_col6
        };
        String selection = t1_col1 + " = ?";
        String[] selectionArgs = { String.valueOf(userid) };

        Cursor cursor = db.query(table1_name, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        accountNo = cursor.getInt(0);

        if (cursorCount > 0){
            return accountNo;
        }
        return accountNo;
    }

    public int getAmount (long userid){
        int amount = 0;
        db = this.getWritableDatabase();
        String[] columns = {
                t1_col7
        };
        String selection = t1_col1 + " = ?";
        String[] selectionArgs = { String.valueOf(userid) };

        Cursor cursor = db.query(table1_name, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();
        amount = cursor.getInt(0);

        if (cursorCount > 0){
            return amount;
        }
        return amount;
    }

    public int getAmountOfReceiveUser (String account){
        int amount = 0;
        db = this.getWritableDatabase();
        String[] columns = {
                t1_col7
        };
        String selection = t1_col6 + " = ?";
        String[] selectionArgs = { account };

        Cursor cursor = db.query(table1_name, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.moveToFirst();

        if (cursorCount > 0){
            amount = cursor.getInt(0);
            return amount;
        }else {
            return -1;
        }

    }

    public boolean updateCurrentUser(int amount, long userid) {
        db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(t1_col7,amount);
        long result = db.update(table1_name,contentValues,t1_col1+"=?",new String[]{String.valueOf(userid)});
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public boolean updateReceiverUser(int amount, int accountno) {
        db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(t1_col7,amount);
        long result = db.update(table1_name,contentValues,t1_col6+"=?",new String[]{String.valueOf(accountno)});
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor user_count()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT COUNT(*) FROM "+table1_name,null);
        return cursor;
    }

    public boolean trans_InsertData(String amount,String sender,String receiver){
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(t2_col2,amount);
        contentValues.put(t2_col3,sender);
        contentValues.put(t2_col4,receiver);

        long result=db.insert(table2_name,null,contentValues);
        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor trans_Send(int sender)
    {
        db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+table2_name+" WHERE "+t2_col3+" = "+sender,null);
        return result;
    }

    public Cursor trans_receive(int receiver)
    {
        db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+table2_name+" WHERE "+t2_col4+" = "+receiver,null);
        return result;
    }
}
