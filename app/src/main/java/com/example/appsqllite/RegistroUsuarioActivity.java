package com.example.appsqllite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqllite.utilidades.Utilidades;

public class RegistroUsuarioActivity  extends AppCompatActivity {

    EditText campoId,campoNombre,campoTelefono;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        Log.println((int) 1,"RegistroUsuarioActivity","onCreate");
        Toast.makeText(this,"RegUsuaruiActivity",Toast.LENGTH_LONG).show();


        campoId= (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoTelefono= (EditText) findViewById(R.id.campoTelefono);
    }
    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios() {
        Toast.makeText(this,"RegUsua",Toast.LENGTH_LONG).show();
        Log.println((int) 1,"registrarUsuarios","inicia");

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"db_usuarios",null,1);
        SQLiteDatabase db=conn.getWritableDatabase();

        Log.println((int) 1,"registrarUsuarios","conn");

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        long idResultante =db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"id Registro"+idResultante,Toast.LENGTH_LONG).show();
        Log.println((int) 1,"registrarUsuarios","id Registro"+idResultante);
        db.close();
    }
}
