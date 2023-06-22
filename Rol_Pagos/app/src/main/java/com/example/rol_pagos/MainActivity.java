package com.example.rol_pagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rol_pagos.bdd.BDHelper;


public class MainActivity extends AppCompatActivity {
    double sueldoFijo=0.00;
    double subsidio=0.00;
    double descuento=0.00;
    double horasExtras=0.00;
    EditText et_descuento,et_horasExtras,et_sueldoTotal,et_subsidio,et_sueldo,et_cedula, et_cargo,et_funcionario, et_area,et_hijos,et_extra,et_atrasado,et_estado;
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
    et_sueldo=findViewById(R.id.txtSueldo);
    et_subsidio=findViewById(R.id.txtHijos);
    et_descuento=findViewById(R.id.txtAtraso);
    et_horasExtras=findViewById(R.id.txtHorasExtras);
    et_sueldoTotal=findViewById(R.id.txtSueldoTotal);
}
    public double determinarSueldo(String cargo){

        //String cargo=et_cargo.getText().toString();
        if (cargo.equals("Administrativo")==true) {
            sueldoFijo=880.00;
        }else if(cargo.equals("Docente")==true){

            sueldoFijo= 1000.00;
        }
        return sueldoFijo;
    }

    public double subsidio(int numero){
        double sub=0.00;

        if(numero>0){
            sub=numero*50;
        }else{
            sub=0;
            System.out.println("-----------------"+numero);
        }
        return sub;
    }
    public double descuentoAtraso(String item,double sueldo){
        double des=0.00;
        if(item.equals("Si")==true){
            des=sueldo*0.08;
        }else{
            des=0.00;
        }
        return des;
    }

    public double horasExtras(int numHoras){
        double pagoHoras=0.00;
        if(numHoras>0){
            pagoHoras=numHoras*12.00;
        }else{
            pagoHoras=0.00;
        }

        return pagoHoras;
    }

    public double sueldoRecibir(double sueldoFijo,double subsidio,double descuento,double horasExtras){
        return sueldoFijo+subsidio+horasExtras-descuento;
    }

    public void irMostrar(View view){
        //El Intent representa la intención que tiene una app de realizar una tarea.
        // Puedes usar intents para varias tareas; pero, en esta lección, tu intent inicia otra actividad.

        Bundle parametros=new Bundle();
        parametros.putString("sueldoFijo",sueldoFijo+"");


        Intent intent = new Intent(this, MostrarNew.class);
        intent.putExtras(parametros);
        startActivity(intent);
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
            sueldoFijo=this.determinarSueldo(cargo);
            int numHijos=Integer.parseInt(hijos);
            et_sueldo.setText(sueldoFijo+"");
            subsidio=this.subsidio(numHijos);
            et_subsidio.setText(subsidio+"");
            descuento=this.descuentoAtraso(atrasado,sueldoFijo);
            et_descuento.setText(descuento+"");
            int horas=Integer.parseInt(extra);
            horasExtras=this.horasExtras(horas);
            et_horasExtras.setText(horasExtras+"");
            et_sueldoTotal.setText(this.sueldoRecibir(sueldoFijo,subsidio,descuento,horasExtras)+"");
            this.irMostrar(view);



        }else{
            Toast.makeText(this,"FAVOR INGRESAR TODOS LOS CAMPOS",Toast.LENGTH_SHORT).show();
        }

    }
}