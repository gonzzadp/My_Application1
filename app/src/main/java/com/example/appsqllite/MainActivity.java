package com.example.appsqllite;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Button btnOpcionRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.println((int) 1,"principal","onCreate 1");
        //btnOpcionRegistro=findViewById(R.id.btmRegistro);

        Toast.makeText(this,"main onCreate",Toast.LENGTH_LONG).show();

        ConexionSQLiteHelper conn =new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        Log.println((int) 1,"principal","onCreate 2");

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void mainOnClick(View view) {
        Log.println((int) 1,"mainOnClick","entro al evento");
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.btn_add_user:
                miIntent =new Intent(MainActivity.this,RegistroUsuarioActivity.class);
                //Toast.makeText(this,"onClick add user",Toast.LENGTH_LONG).show();
                Log.i("mainOnClick","btn_add_user");
                break;
            case R.id.btn_get_user:
                miIntent =new Intent(MainActivity.this,ConsultarUsuarioActivity.class);
                //Toast.makeText(this,"onClick get user",Toast.LENGTH_LONG).show();
                Log.i("mainOnClick","btn_get_user");
                break;
            case R.id.btn_list_user_listview:
                miIntent =new Intent(MainActivity.this,ConsultarListViewActivity.class);
                //Toast.makeText(this,"onClick list user LT",Toast.LENGTH_LONG).show();
                Log.i("mainOnClick","btn_list_user_listview");
                break;
            case R.id.btn_list_user_spinner:
                miIntent =new Intent(MainActivity.this,ConsultarComboActivity.class);
                //Toast.makeText(this,"onClick list user S",Toast.LENGTH_LONG).show();
                Log.i("mainOnClick","btn_list_user_spinner");
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}