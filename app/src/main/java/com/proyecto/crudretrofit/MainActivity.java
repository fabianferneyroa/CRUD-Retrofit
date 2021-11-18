package com.proyecto.crudretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyecto.crudretrofit.Interfaz.DueñoApi;
import com.proyecto.crudretrofit.modelo.Dueño;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText idDueño;
    private EditText nombre;
    private EditText apellido;
    private EditText direccion;
    private EditText telefono;

    private Button btnRegistrar;
    private Button btnEditar;
    private Button btnEliminar;
    private Button btnConsultar;

    Retrofit retrofit;
    DueñoApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idDueño = findViewById(R.id.etvIdDueño);
        nombre = findViewById(R.id.etvNombre);
        apellido = findViewById(R.id.etvApellido);
        direccion = findViewById(R.id.etvDireccion);
        telefono = findViewById(R.id.etvTelefono);

        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnConsultar = findViewById(R.id.btnConsultar);

        Dueño due = new Dueño();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long id = Long.parseLong(idDueño.getText().toString());

                due.setIdDueño(id);
                due.setNombre(nombre.getText().toString());
                due.setApellidos(apellido.getText().toString());
                due.setDireccion(direccion.getText().toString());
                due.setTelefono(telefono.getText().toString());

                registrar(due);

            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long id = Long.parseLong(idDueño.getText().toString());

                due.setIdDueño(id);
                due.setNombre(nombre.getText().toString());
                due.setApellidos(apellido.getText().toString());
                due.setDireccion(direccion.getText().toString());
                due.setTelefono(telefono.getText().toString());

                editar(due);

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long id = Long.parseLong(idDueño.getText().toString());

                eliminar(id);

            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ReadActivity.class));

            }
        });

    }

    public void registrar(Dueño dueño){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://132.145.34.221:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DueñoApi dueñoapi = retrofit.create(DueñoApi.class);
        Call<Dueño> call = dueñoapi.save(dueño);

        call.enqueue(new Callback<Dueño>() {
            @Override
            public void onResponse(Call<Dueño> call, Response<Dueño> response) {

                try {


                    if (response.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Dueño Registrado", Toast.LENGTH_LONG).show();

                        limpiarCampos();

                    }
                }catch (Exception e){

                    Toast.makeText(MainActivity.this,
                            e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Dueño> call, Throwable t) {

                Toast.makeText(MainActivity.this,
                        "Error de conexion",Toast.LENGTH_LONG).show();

            }
        });


    }

    public void editar(Dueño dueño){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://132.145.34.221:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DueñoApi dueñoapi = retrofit.create(DueñoApi.class);
        Call<Dueño> call = dueñoapi.save(dueño);

        call.enqueue(new Callback<Dueño>() {
            @Override
            public void onResponse(Call<Dueño> call, Response<Dueño> response) {

                try {


                    if (response.isSuccessful()) {

                        Toast.makeText(MainActivity.this, "Dueño Actualizado", Toast.LENGTH_LONG).show();

                        limpiarCampos();

                    }
                }catch (Exception e){

                    Toast.makeText(MainActivity.this,
                            e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Dueño> call, Throwable t) {

                Toast.makeText(MainActivity.this,
                        "Error de conexion",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void eliminar(Long idDueño){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://132.145.34.221:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DueñoApi dueñoapi = retrofit.create(DueñoApi.class);
        Call<Void> call = dueñoapi.delete(idDueño);

        System.out.println("inicio");
        System.out.println(idDueño);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                try {

                    if (response.isSuccessful()){

                        response.code();

                        Toast.makeText(MainActivity.this,"Dueño Eliminado",Toast.LENGTH_LONG).show();

                        limpiarCampos();

                }
            }catch (Exception e){

                    System.out.println(e);

                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                System.out.println("failure");

            }
        });

    }

  public void limpiarCampos(){

        idDueño.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        telefono.setText("");

    }

}
