package sdu.sitta.ungrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by kwan_raksasook on 24/4/2560.
 */

public class OrderTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String TABLE_order = "orderTABLE";
    public static final String order_ID = "order_ID";
    public static final String order_Name = "order_Name";
    public static final String User_ID2 = "_ID";
    public static final String order_CasephoneId = "c_ID";
    public static final String order_Item = "order_Item";


    public OrderTable(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }



    public long addNewDrink(String o_Name,String id_user,String id_casephone,String price){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(order_Name,o_Name);
        objContentValues.put(User_ID2,id_user);
        objContentValues.put(order_CasephoneId,id_casephone);
        objContentValues.put(order_Item,price);
        return readSqLiteDatabase.insert(TABLE_order,null, objContentValues);
    }

}