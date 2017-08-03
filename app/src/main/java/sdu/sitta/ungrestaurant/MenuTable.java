package sdu.sitta.ungrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by khowoatt on 17/7/2560.
 */

public class MenuTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;


    public static final String Menu_TABLE = "menutable";
    public static final String Menu_ID = "id_menu";
    public static final String Menu_NAME = "name_menu";
    public static final String Menu_DETAIL = "detail_menu";
    public static final String Menu_PRICE = "price_menu";
    public static final String Menu_PICTURE = "picture_menu";
    public static final String Menu_TYPE = "type";

    public MenuTable(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewMenuTable(String name_menu, String detail_menu, String price_menu,String picture_menu,String type) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.Menu_NAME, name_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_DETAIL, detail_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_PRICE, price_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_PICTURE, picture_menu);
        objContentValues.put(objMySQLiteOpenHelper.Menu_TYPE, type);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.Menu_TABLE, null, objContentValues);
    }

    public String[] readALLMenuTable(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(Menu_TABLE, new String[]{Menu_ID,Menu_NAME,Menu_DETAIL,Menu_PRICE,Menu_PICTURE,Menu_TYPE},null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    strResult = new String[3];
                    // strResult[0] = objCursor.getString(0);
                    // strResult[1] = objCursor.getString(1);
                    // strResult[2] = objCursor.getString(2);
                    // strResult[3] = objCursor.getString(3);
                    for(int i =0;i<3;i++){
                        strResult[i] = objCursor.getString(intColume);
                        objCursor.moveToNext();
                    }
                }
            }
            objCursor.close();
            return strResult;
        }catch (Exception e){
            return null;
        }
        //return new String[0];
    }
}