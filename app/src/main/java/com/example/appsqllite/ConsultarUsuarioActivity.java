package com.example.appsqllite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqllite.utilidades.Utilidades;

public class ConsultarUsuarioActivity extends AppCompatActivity {

    EditText campoIdAct,campoNombreAct,campoTelefonoAct;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

//            Log.println((int) 1,"RegistroUsuarioActivity","onCreate");
  //          Toast.makeText(this,"RegUsuaruiActivity",Toast.LENGTH_LONG).show();

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_usuarios",null,1);

            campoIdAct= (EditText) findViewById(R.id.campoBuscar);
            campoNombreAct= (EditText) findViewById(R.id.campoNombreAct);
            campoTelefonoAct= (EditText) findViewById(R.id.campoTelefonoAct);

    }
    public void onClick(View view){

        switch (view.getId()){
        case R.id.btn_search:
            consultar();
            break;
        case R.id.btn_put_user:
            actualizarUsuario();
            break;
        case R.id.btn_delete_user:
            eliminarUsiario();
            break;
    }

    }

    private void eliminarUsiario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoIdAct.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino el usuario",Toast.LENGTH_LONG).show();
        campoIdAct.setText("");
        limpiar();
        db.close();
    }

    private void actualizarUsuario() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoIdAct.getText().toString()}; campoNombreAct.setText("");
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,campoNombreAct.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefonoAct.getText().toString());
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"ya se actualizo ...",Toast.LENGTH_LONG).show();
        db.close();
    }

    private void consultar() {

        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoIdAct.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try {

            Cursor cursor=db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombreAct.setText(cursor.getString(0));
            campoTelefonoAct.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"el docu no existe",Toast.LENGTH_SHORT).show();
        limpiar();
        }
    }

    private void limpiar() {
        campoNombreAct.setText("");
        campoTelefonoAct.setText("");
    }

}
