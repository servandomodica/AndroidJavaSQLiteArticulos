package com.example.basededatos1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//IMPORTANTE: PRIMERO, EN SRC, MISMO PAQUETE, CREO NEW, CLASS, TIPO SQLiteOpenHelper
// En browse, hereda de android.database.sqlite.SQLiteOpenHelper

public class MainActivity extends Activity {
	EditText et1, et2, et3;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
	}

	public void agregar(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		// BASE1 ES EL NOMBRE DE LA BASE DE DATOS QUE SE CREARA EN DATA / DATA / COM... / DATABASES
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		ContentValues registro = new ContentValues();		
		registro.put("descripcion", et1.getText().toString());
		registro.put("precio", et2.getText().toString());
		
		bd.insert("articulos", null, registro);
		bd.close();
		
		et1.setText("");
		et2.setText("");
		Toast.makeText(this, "Se almaceno el articulo correctamente", Toast.LENGTH_LONG).show();
	}

	public void consultarCod(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		// BASE1 ES EL NOMBRE DE LA BASE DE DATOS QUE SE CREARA EN DATA / DATA / COM... / DATABASES
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		Cursor registro = bd.rawQuery("select descripcion, precio from articulos where codigo=" + et3.getText().toString(), null);
		
		if (registro.moveToFirst()) { // BOOLEAN, MOVETOFIRST VA A DEVOLVER LO PRIMERO QUE CUMPLA CON EL WHERE DEL SELECT, POR MAS QUE MUCHOS LO CUMPLAN
			et1.setText(registro.getString(0)); // COLUMNA 0, VIENE A SER DESCRIPCION
			et2.setText(registro.getString(1)); // COLUMNA 1, VIENE A SER PRECIO
		} else {
			Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_LONG).show();
			bd.close();
		}
	}

	public void consultarDescr(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		Cursor registro = bd.rawQuery("select codigo, precio from articulos where descripcion='" + et1.getText().toString() + "'", null);
		
		if (registro.moveToFirst()) {
			et3.setText(registro.getString(0));
			et2.setText(registro.getString(1));
		} else {
			Toast.makeText(this, "No existe un articulo con dicho nombre", Toast.LENGTH_LONG).show();
			bd.close();
		}
	}

	public void borrarArt(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		int cant = bd.delete("articulos", "codigo=" + et3.getText().toString(), null);
		
		if (cant == 1) {
			Toast.makeText(this, "Se elimino el articulo", Toast.LENGTH_LONG).show();
			et1.setText("");
			et2.setText("");
			et3.setText("");
		} else {
			Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_LONG).show();
		}
		bd.close();
	}

	public void modificar(View v) {
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "base1", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		
		ContentValues registro = new ContentValues();
		registro.put("descripcion", et1.getText().toString());
		registro.put("precio", et2.getText().toString());
		
		int cant = bd.update("articulos", registro, "codigo=" + et3.getText().toString(), null);
		
		if (cant == 1) {
			Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_LONG).show();
			et1.setText("");
			et2.setText("");
			et3.setText("");
		} else {
			Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_LONG).show();
		}
		bd.close();
	}

}
