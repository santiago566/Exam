package com.example.rol_pagos.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREACIÓN DE LAS TABLAS
        db.execSQL("CREATE TABLE tblUsuarios"+"(" +
                "usu_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "usu_cedula INTEGER NOT NULL,"+
                "usu_cargo text NOT NULL,"+
                "usu_funcionario text NOT NULL,"+
                "usu_area text NOT NULL," +
                "usu_n_hijos text NOT NULL,"+
                "usu_n_Extra text NOT NULL,"+
                "usu_n_atraso text NOT NULL,"+
                "usu_Estado_civil NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //CAMBIE LA VERSIÓN DE LA TABLA DE LA BDD
        db.execSQL("DROP TABLE tblUsuarios");
        onCreate(db);
    }
}




