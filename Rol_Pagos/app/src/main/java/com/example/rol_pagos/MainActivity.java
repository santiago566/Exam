package com.example.rol_pagos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rol_pagos.bdd.BDHelper;


public class MainActivity extends AppCompatActivity {
    EditText et_cedula, et_cargo,et_funcionario, et_area,et_hijos,et_extra,et_atrasado,et_estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    et_cedula=findViewById(R.id.ced);
    et_cargo=findViewById(R.id.car);
    et_funcionario=findViewById(R.id.fn);
    et_area=findViewById(R.id.area);
    et_hijos=findViewById(R.id.hijos);
    et_extra=findViewById(R.id.horas);
    et_atrasado=findViewById(R.id.atra);
    et_estado=findViewById(R.id.esatado);
}

    public void registrar(View view){
        BDHelper admin=new BDHelper(this,"registro.db",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        String cedula=et_cedula.getText().toString();
        String cargo=et_cargo.getText().toString();
        String funcionario=et_funcionario.getText().toString();
        String area=et_area.getText().toString();
        String hijos=et_hijos.getText().toString();
        String extra=et_extra.getText().toString();
        String atrasado=et_atrasado.getText().toString();
        String estado=et_estado.getText().toString();

        if(!cedula.isEmpty() && !cedula.isEmpty() && !cargo.isEmpty() && !funcionario.isEmpty()&& !area.isEmpty() && !hijos.isEmpty()  && !extra.isEmpty() && !atrasado.isEmpty()&& !estado.isEmpty()){
            ContentValues registro=new ContentValues();
            registro.put("usu_cedula",cedula);
            registro.put("usu_cargo",cargo);
            registro.put("usu_funcionario",funcionario);
            registro.put("usu_area",area);
            registro.put("usu_hijos",hijos);
            registro.put("usu_extra",extra);
            registro.put("usu_atrasado",atrasado);
            registro.put("usu_estado",estado);
            bd.insert("tblUsuarios",null,registro);
            Toast.makeText(this, "REGISTRO EXITOSO", Toast.LENGTH_SHORT).show();
            et_cedula.setText("");
            et_cargo.setText("");
            et_funcionario.setText("");
            et_area.setText("");
            et_hijos.setText("");
            et_extra.setText("");
            et_atrasado.setText("");
            et_estado.setText("");

            bd.close();
        }else{
            Toast.makeText(this,"FAVOR INGRESAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
        }

    }
}