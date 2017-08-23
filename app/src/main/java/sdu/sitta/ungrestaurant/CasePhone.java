package sdu.sitta.ungrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sitta on 29/4/2560.
 */

public class CasePhone {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;


    public static final String TABLE_casephone = "casephoneTABLE";
    public static final String casephone_ID = "c_ID";
    public static final String casephone_Name = "c_Name";
    public static final String casephone_Price = "c_Price";
    public static final String casephone_Pic = "c_Pic";

    public CasePhone(Context context) {
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewCasePhone(String cName, String cPrice, String cPic) {
        ContentValues objContentValues = new ContentValues();

        objContentValues.put(objMySQLiteOpenHelper.casephone_Name, cName);
        objContentValues.put(objMySQLiteOpenHelper.casephone_Price, cPrice);
        objContentValues.put(objMySQLiteOpenHelper.casephone_Pic, cPic);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.TABLE_casephone, null, objContentValues);
    }

    public String[] readALLCasePhone(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_casephone, new String[]{casephone_ID,casephone_Name,casephone_Price,casephone_Pic},null,null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    int num = objCursor.getCount();
                    strResult = new String[num];
                    for(int i =0;i<num;i++){
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


