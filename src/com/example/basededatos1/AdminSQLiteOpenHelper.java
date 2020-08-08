package com.example.basededatos1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


	public AdminSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}


	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table articulos (codigo integer primary key autoincrement," +
				"descripcion text," +
				"precio real" +
				")");
		// arg0.execSQL("insert into articulos (descripcion, precio) values ('papas', 333.40)"); PODRIA DAR MAS COMANDOS
	}


	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// ESTO SE USARIA SI TIEMPO DESPUES MODIFICO LA VERSION DE LA BASE DE DATOS 

	}

}
