package sdu.sitta.ungrestaurant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sitta on 22/4/2560.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;


    public static final String DataBase_Name = "Restaurant.db";
    public static final int DataBase_Version = 1;

    public static final String TABLE = "userTABLE";
    public static final String User_ID = BaseColumns._ID;
    public static final String User_USER = "User";
    public static final String User_PASSWORD = "Password";
    public static final String User_NAME = "Email";
    public static final String User_NAME2 = "Address";
    public String CREATE_FRIEND_TABLE ="create table "+TABLE+" ("+User_ID+" integer primary key, "+User_USER+" text, "+User_PASSWORD+" text, "+User_NAME+" text, "+User_NAME2+" text);";

    public static final String TABLE2 = "Drinktable";
    public static final String Drink_ID = "ID_Drink";
    public static final String Drink_Drink = "Name";
    public static final String Drink_Detail = "Detail";
    public static final String Drink_Source = "Source";
    public static final String Drink_Price = "Price";
    private static final String CREATE_DRINK_TABLE = "create table "+TABLE2+" ("+Drink_ID+" integer primary key, "+Drink_Drink+" text,"+Drink_Detail+" text, "+Drink_Source+" text, "+Drink_Price+" text);";

    public static final String TABLE_casephone = "casephoneTABLE";
    public static final String casephone_ID = "c_ID";
    public static final String casephone_Name = "c_Name";
    public static final String casephone_Price = "c_Price";
    public static final String casephone_Pic = "c_Pic";
    public String CREATE_CASEPHONE_TABLE ="create table "+TABLE_casephone+" ("+casephone_ID+" integer primary key , "+casephone_Name+" text, "+casephone_Pic+" text, "+casephone_Price+" text);";

    public MySQLiteOpenHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, CREATE_FRIEND_TABLE);
        Log.i(TAG, CREATE_DRINK_TABLE);
        Log.i(TAG, CREATE_CASEPHONE_TABLE);
        db.execSQL(CREATE_FRIEND_TABLE);
        db.execSQL(CREATE_DRINK_TABLE); // ขวัญ
        db.execSQL(CREATE_CASEPHONE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // copy เค้ามา อย่าสงสับ
        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS " + TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }
}
