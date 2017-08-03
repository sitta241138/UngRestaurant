package sdu.sitta.ungrestaurant;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
