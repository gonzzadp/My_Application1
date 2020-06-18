package com.example.appsqllite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsqllite.entidades.Usuario;
import com.example.appsqllite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarListViewActivity extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_list_view);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"db_usuarios",null,1);

        listViewPersonas= (ListView) findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getWritableDatabase();
        Usuario persona=null;
        listaUsuarios= new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()){
            persona=new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre());
            Log.i("Telefono",persona.getTelefono());

            listaUsuarios.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion=new ArrayList<String>();


        for(int i=0;i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "+listaUsuarios.get(i).getNombre());
        }
    }
}
