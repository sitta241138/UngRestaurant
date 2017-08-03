package sdu.sitta.ungrestaurant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private UserTABLE objUserTable;
    private DrinkTable objDrinkTable;
    private CasePhone objCasePhone;

    private TextView officerTextView;
    private Spinner deskSpinner;
    private ListView casephoneListView;
    // DB // DB// DB// DB// DB// DB// DB// DB
    SQLiteDatabase sqliteMyDB ;
    MySQLiteOpenHelper mHelper;
    Cursor myDBCursor;
    // DB // DB// DB// DB// DB// DB// DB// DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);



        BindWidget();

        /*
        todoListView = (ListView)findViewById(R.id.listview);
        TodoListDAO todoListDAO = new TodoListDAO(getApplicationContext());
        todoListDAO.open();
        ArrayList<String> mylist = todoListDAO.getALLTodoList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,mylist);
        todoListView.setAdapter(adapter);
        todoListDAO.close();
        */
        Cdatabase();
        createListView();

        /*
        // DB // DB// DB// DB// DB// DB// DB// DB
        ListView listViewMovies = (ListView)findViewById(R.id.listview);
        mHelper = new MySQLiteOpenHelper(this);

        sqliteMyDB = mHelper.getWritableDatabase();

        myDBCursor = sqliteMyDB.rawQuery("select * from casephoneTABLE;",null);

        ArrayList<String> dirArray = new ArrayList<String>();
        myDBCursor.moveToFirst();

        while ( !myDBCursor.isAfterLast() ){
            dirArray.add(myDBCursor.getString(myDBCursor.getColumnIndex(MySQLiteOpenHelper.casephone_Pic)));
            myDBCursor.moveToNext();
        }

        ArrayAdapter<String> adapterDir = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dirArray);
        listViewMovies.setAdapter(adapterDir);
        // DB// DB// DB// DB// DB// DB// DB// DB// DB// DB// DB// DB// DB// DB// DB
        */
    }
    @Override
    public void onPause() {
        super.onPause();
        mHelper.close();
        sqliteMyDB.close();
    }

    public void Cdatabase(){ // Open DataBase
        objUserTable = new UserTABLE(this);
        objDrinkTable = new DrinkTable(this);
        objCasePhone = new CasePhone(this);
    } // เปิดใช้งาน sqlite

    private void createListView() {
        CasePhone objCasephone = new CasePhone(this);
        String[] strC_Name = objCasephone.readALLCasePhone(1);
        String[] strC_Pic = objCasephone.readALLCasePhone(2);
        String[] strC_Price = objCasephone.readALLCasePhone(3);

        //String[] o1 = {"ht","hy","hu"};
        //String[] o2 = {"123","456","789"};
        //String[] o3 = {"http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png"};
        MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, strC_Name,strC_Price,strC_Pic);
        //MyAdapter objMyAdapter = new MyAdapter(OrderActivity.this, o1,o3,o2);
        casephoneListView.setAdapter(objMyAdapter);
    }

   private void BindWidget() {
       officerTextView = (TextView) findViewById(R.id.textview);
       deskSpinner = (Spinner) findViewById(R.id.spinner);
       casephoneListView = (ListView) findViewById(R.id.listview);
   }

    public void onClickOrder(View view){
        Intent intent = new Intent(OrderActivity.this,Menu.class);
        startActivity(intent);
    }

}
