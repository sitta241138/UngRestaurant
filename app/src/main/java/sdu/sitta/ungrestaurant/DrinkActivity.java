package sdu.sitta.ungrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    // open database
    private UserTABLE objUserTable;
    private DrinkTable objDrinkTable;
    private CasePhone objCasePhone;


    private ListView DrinkListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        BindWidget();
        Cdatabase();
        createListView();
    }


    private void createListView() {
        DrinkTable objDrink = new DrinkTable(this);
        String[] strC_Name = objDrink.readALLDrink(1);
        String[] strC_Pic = objDrink.readALLDrink(3);
        String[] strC_Price = objDrink.readALLDrink(4);

        String[] o1 = {"ht","hy","hu"};
        String[] o2 = {"123","456","789"};
        String[] o3 = {"http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png","http://bitmouse.96.lt/GUPER/pic/iphone7.png"};
        //MyAdapter objMyAdapter = new MyAdapter(DrinkActivity.this, strC_Name,o3,strC_Price);
        MyAdapter objMyAdapter = new MyAdapter(DrinkActivity.this, o1,o3,o2);
        DrinkListView.setAdapter(objMyAdapter);
    }

    private void BindWidget() {
         DrinkListView = (ListView) findViewById(R.id.listviewDrink);
    }

    public void Cdatabase(){ // Open DataBase
        objUserTable = new UserTABLE(this);
        objDrinkTable = new DrinkTable(this);
        objCasePhone = new CasePhone(this);
    } // เปิดใช้งาน sqlite
}
