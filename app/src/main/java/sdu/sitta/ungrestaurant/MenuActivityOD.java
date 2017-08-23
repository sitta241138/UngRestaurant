package sdu.sitta.ungrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MenuActivityOD extends AppCompatActivity {

    // open database
    private UserTABLE objUserTable;
    private DrinkTable objDrinkTable;
    private CasePhone objCasePhone;
    private MenuTable objMenuTable;


    private ListView MenuListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuod);

        BindWidget();
        Cdatabase();
        createListView();
    }


    private void createListView() {
        MenuTable objDrink = new MenuTable(this);
        String[] strC_Name = objDrink.readALLMenuTable(1);
        String[] strC_Pic = objDrink.readALLMenuTable(4);
        String[] strC_Price = objDrink.readALLMenuTable(2);

        String[] o1 = {"ht","hy","hu"};
        String[] o2 = {"123","456","789"};
        String[] o3 = {"http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png"};
        MyAdapter objMyAdapter = new MyAdapter(MenuActivityOD.this, strC_Name,strC_Pic,strC_Price);
        //MyAdapter objMyAdapter = new MyAdapter(DrinkActivity.this, o1,o3,o2);
        MenuListView.setAdapter(objMyAdapter);
    }

    private void BindWidget() {
        MenuListView = (ListView) findViewById(R.id.listviewOD);
    }

    public void Cdatabase(){ // Open DataBase
        objUserTable = new UserTABLE(this);
        objDrinkTable = new DrinkTable(this);
        objCasePhone = new CasePhone(this);
        objMenuTable = new MenuTable(this);
    } // เปิดใช้งาน sqlite
}
