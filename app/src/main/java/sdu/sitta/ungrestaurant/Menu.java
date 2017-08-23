package sdu.sitta.ungrestaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        SharedPreferences shared = getSharedPreferences("sessionUser", new MainActivity().MODE_PRIVATE);
        String stringValue = shared.getString("ID", "not found!0");
        String stringValue1 = shared.getString("user", "not found!1");
        String stringValue2 = shared.getString("pass", "not found!2");
        String stringValue3 = shared.getString("email", "not found!3");
        String stringValue4 = shared.getString("address", "not found!4");

        TextView tv = (TextView)findViewById(R.id.oop);
        TextView tv1 = (TextView)findViewById(R.id.oop1);
        TextView tv2 = (TextView)findViewById(R.id.oop2);
        TextView tv3 = (TextView)findViewById(R.id.oop3);
        TextView tv4 = (TextView)findViewById(R.id.oop4);

        tv.setText(stringValue);
        tv1.setText(stringValue1);
        tv2.setText(stringValue2);
        tv4.setText(stringValue4);

    }

    public void onClickOrder(View view){
        Intent intent = new Intent(Menu.this,OrderActivity.class);
        startActivity(intent);
    }

    public void onClickDrink(View view){
        Intent intent = new Intent(Menu.this,DrinkActivity.class);
        startActivity(intent);
    }

    public void onClickMenu(View view){
        Intent intent = new Intent(Menu.this,MenuActivityOD.class);
        startActivity(intent);
    }
}
