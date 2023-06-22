package com.example.rol_pagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class MostrarNew extends AppCompatActivity {
 EditText et_sueldoFijo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_new2);

        et_sueldoFijo=findViewById(R.id.txtResul);

        Bundle parametros = this.getIntent().getExtras();
        String sueldoFijo = parametros.getString("sueldoFijo");
        et_sueldoFijo.setText(sueldoFijo);
    }

}