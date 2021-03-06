package sdu.sitta.ungrestaurant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private UserTABLE objUserTable;
    private DrinkTable objDrinkTable;
    private CasePhone objCasePhone;
    private MenuTable objMenuTable;

    private EditText userEditText,passEditText;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cdatabase();
        deleteAllData();
        synJAONtoSQLite();

        //TestAdd();

        //deleteAllData();
    }
    public void btnLOGIN(View view){
        userEditText = (EditText) findViewById(R.id.Ed_ID);
        passEditText = (EditText) findViewById(R.id.Ed_PASS);

        String struser = userEditText.getText().toString().trim();
        String strpass = passEditText.getText().toString().trim();

        //Check char zero
        if(struser.equals("") || strpass.equals("")){
            errorDialog("มีช่องว่าง","กรุณากรอกให้ครบ ทุกช่อง ด้วยนะเห้ย" +
                    "\nไอหัวมันฝรั่ง");
        }else {
            checkUSERPASSWORD(struser,strpass);
        }
    }

    private void checkUSERPASSWORD(String struser, String strpass) {
        try{
            String[] strMyResult = objUserTable.searchUSERPASSWORD(struser);
            if(strpass.equals(strMyResult[2])){
                //password True
                welcomDialog(struser);

                sharedpreferences = getSharedPreferences("sessionUser", Context.MODE_PRIVATE); // สร้างไฟล์เก็บ session
                SharedPreferences.Editor editor = sharedpreferences.edit(); // เปิดการใช้งาน ตัวแก้ไข หรือ เพิ่มเติม
                editor.putString("ID", strMyResult[0]); // อัด ฟิว , ข้อมูล
                editor.putString("user", strMyResult[1]); // อัด ฟิว , ข้อมูล
                editor.putString("pass", strMyResult[2]); // อัด ฟิว , ข้อมูล
                editor.putString("email", strMyResult[3]); // อัด ฟิว , ข้อมูล
                editor.putString("address", strMyResult[4]); // อัด ฟิว , ข้อมูล
                editor.commit(); // จบการทำงาน


            }else {
                //password False
                errorDialog("Password ผิด","ลองใหม่ไอเจ้าคนนอก");
            }

        }catch(Exception e){
            errorDialog("User ผิด","ไม่มี "+struser+" ในฐานข้อมูลเผ่ามันฝรั่ง");
        }
    }

    private void welcomDialog(final String strname) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon);
        objBuilder.setTitle("เห้ย ยินดีๆ");
        objBuilder.setMessage("เข้ามาดิงงอะไร "+strname+" \nเข้ามาซักทีเถอะ กด OK ดิี");
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                intent.putExtra("Officer",strname);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        objBuilder.show();
    } //Builder ERROR

    private void errorDialog(String strTitle,String strMessage) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setCancelable(false);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    } //Builder ERROR

    private void synJAONtoSQLite() {
        //Setup Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //Loop 2 Time
        int intTimes = 0;
        while(intTimes <= 2){

            //Variable and Constant
            InputStream objInputStream = null;
            String strJSON = null;
            String strUserURL = "http://5711020660041.sci.dusit.ac.th/userTable.php";
            String strCasePhone = "http://5711020660041.sci.dusit.ac.th/casephoneTable.php";
            String strMenuURL = "http://5711020660011.sci.dusit.ac.th/getAllDataMenu.php";
            String strDrinkURL = "http://bitmouse.96.lt/GUPER/drink.php";
            HttpPost objHttpPost = null;

            //1.Create InputStream
            try{
                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTimes){
                    case 0:
                        objHttpPost = new HttpPost(strUserURL);
                        break;
                    case 1:
                        objHttpPost = new HttpPost(strCasePhone);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strMenuURL);
                        break;
                    default:
                        objHttpPost = new HttpPost(strDrinkURL);
                        break;
                }
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();
            }catch (Exception e){
                Log.d("masterUNG","InputStream ==> "+e.toString());
            }
            //2.Create strJSON
            try{
                InputStreamReader objInputStreamReader = new InputStreamReader(objInputStream,"UTF-8");
                BufferedReader objBufferedReader = new BufferedReader(objInputStreamReader);
                StringBuilder objStringBuilder = new StringBuilder();
                String strLine = null;
                while((strLine = objBufferedReader.readLine()) != null){
                    objStringBuilder.append(strLine);
                }
                objInputStream.close();
                strJSON = objStringBuilder.toString();
            }catch (Exception e){
                Log.d("masterUNG","strJSON"+e.toString());
            }
            //3.Update to SQLite
            try{
                JSONArray objJsonArray = new JSONArray(strJSON);
                for(int i =0;i<objJsonArray.length();i++){
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);
                    switch (intTimes){
                        case 0:
                            //update UserTABLE
                            String strID = jsonObject.getString("_ID");
                            String strUser = jsonObject.getString("User");
                            String strPassword = jsonObject.getString("Password");
                            String strEmail = jsonObject.getString("Email");
                            String strAddress = jsonObject.getString("Address");
                            objUserTable.AddNewUser(strID,strUser,strPassword,strEmail,strAddress);
                            break;
                        case 1:
                            //update casephoneTable
                            String cName = jsonObject.getString("c_Name");
                            String cPrice = jsonObject.getString("c_Price");
                            String cPic = jsonObject.getString("c_Pic");
                            objCasePhone.AddNewCasePhone(cName,cPrice,cPic);
                            break;
                        case 2:
                            //update MenuTable
                            String name_menu = jsonObject.getString("name_menu");
                            String detail_menu = jsonObject.getString("detail_menu");
                            String price_menu = jsonObject.getString("price_menu");
                            String picture_menu = jsonObject.getString("picture_menu");
                            String type = jsonObject.getString("type");
                            objMenuTable.AddNewMenuTable(name_menu,detail_menu,price_menu,picture_menu,type);
                            break;
                        default:
                            //update drinktable
                            String strdrink = jsonObject.getString("Name");
                            String strdetail = jsonObject.getString("Detail");
                            String strsource = jsonObject.getString("Source");
                            String strprice = jsonObject.getString("Price");
                            objDrinkTable.addNewDrink(strdrink,strdetail,strsource,strprice);
                            break;
                    }
                }
            }catch (Exception e){
                Log.d("masterUNG","strJSON"+e.toString());
            }
            //Increase intTimes
            intTimes += 1;
        }
    } // ซิ้งข้อมูลจาก mysql to sqlite

    public void Cdatabase(){ // Open DataBase
        objUserTable = new UserTABLE(this);
        objDrinkTable = new DrinkTable(this);
        objCasePhone = new CasePhone(this);
        objMenuTable = new MenuTable(this);
    } // เปิดใช้งาน sqlite

    public void TestAdd(){
        //objUserTable.AddNewUser("123","456","sitta");
        //objDrinkTable.addNewDrink("111","222","2311","3212");
    } // add data

    private void deleteAllData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase("Restaurant.db",MODE_APPEND,null);
        objSqLiteDatabase.delete("userTABLE",null,null);
        objSqLiteDatabase.delete("Drinktable",null,null);
        objSqLiteDatabase.delete("casephoneTABLE",null,null);
        objSqLiteDatabase.delete("menutable",null,null);
    } // delete data

}

