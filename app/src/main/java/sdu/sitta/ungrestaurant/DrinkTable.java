package sdu.sitta.ungrestaurant;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;



/**
 * Created by kwan_raksasook on 24/4/2560.
 */

public class DrinkTable {
    private MySQLiteOpenHelper objMySQLiteOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase;
    private SQLiteDatabase readSqLiteDatabase;

    public static final String TABLE2 = "Drinktable";

    public static final String Drink_ID = "ID_Drink";
    public static final String Drink_Drink = "Name";
    public static final String Drink_Detail = "Detail";
    public static final String Drink_Source = "Source";
    public static final String Drink_Price = "Price";


    public DrinkTable(Context context){
        objMySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        writeSqLiteDatabase = objMySQLiteOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMySQLiteOpenHelper.getReadableDatabase();
    }

    public long addNewDrink(String drink,String detail,String source,String price){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(objMySQLiteOpenHelper.Drink_Drink,drink);
        objContentValues.put(objMySQLiteOpenHelper.Drink_Detail,detail);
        objContentValues.put(objMySQLiteOpenHelper.Drink_Source,source);
        objContentValues.put(objMySQLiteOpenHelper.Drink_Price,price);

        return readSqLiteDatabase.insert(objMySQLiteOpenHelper.TABLE2,null, objContentValues);
    }


    public String[] readALLDrink(int intColume){
        try {
            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE2, new String[]{Drink_ID,Drink_Drink,Drink_Detail,Drink_Source,Drink_Price},null,null,null,null,null);
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