package sdu.sitta.ungrestaurant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by sitta on 25/4/2560.
 */

public class TodoListDAO {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    // test tam web
    public TodoListDAO(Context context){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
    }
    public void open(){
        db = mySQLiteOpenHelper.getWritableDatabase();
    }
    public void close(){
        db.close();
    }
    public ArrayList<String> getALLTodoList(){
        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from casephoneTABLE;",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            todoList.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }
}
