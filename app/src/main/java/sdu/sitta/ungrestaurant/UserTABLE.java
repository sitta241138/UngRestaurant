package sdu.sitta.ungrestaurant;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by sitta on 22/4/2560.
 */

public class UserTABLE {

    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSQLiteDataBase, readSQLiteDataBase;

    public static final String TABLE = "userTABLE";
    public static final String User_ID = "BaseColumns";
    public static final String User_USER = "User";
    public static final String User_PASSWORD = "Password";
    public static final String User_NAME = "Email";
    public static final String User_NAME2 = "Address";

    public UserTABLE(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSQLiteDataBase = objMySQLiteOpenHelper.getWritableDatabase();
        readSQLiteDataBase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long AddNewUser(String strID,String strUser,String strPassword,String strName,String strName2){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.User_ID,strID);
        objContentValues.put(objMySQLiteOpenHelper.User_USER,strUser);
        objContentValues.put(objMySQLiteOpenHelper.User_PASSWORD,strPassword);
        objContentValues.put(objMySQLiteOpenHelper.User_NAME,strName);
        objContentValues.put(objMySQLiteOpenHelper.User_NAME2,strName2);
        return readSQLiteDataBase.insert(objMySQLiteOpenHelper.TABLE,null,objContentValues);
    }

    public String[] searchUSERPASSWORD(String strUser){
        try {
            String[] strResult = null;
            Cursor objCursor = readSQLiteDataBase.query(TABLE, new String[]{User_ID,User_USER,User_PASSWORD,User_NAME,User_NAME2},User_USER + "=?",new String[]{String.valueOf(strUser)},null,null,null,null);
            if(objCursor != null){
                if(objCursor.moveToFirst()){
                    strResult = new String[5];
                   // strResult[0] = objCursor.getString(0);
                   // strResult[1] = objCursor.getString(1);
                   // strResult[2] = objCursor.getString(2);
                   // strResult[3] = objCursor.getString(3);
                    for(int i =0;i<5;i++){
                        strResult[i] = objCursor.getString(i);
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
