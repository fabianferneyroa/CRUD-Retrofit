package com.proyecto.crudretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.crudretrofit.Interfaz.DueñoApi;
import com.proyecto.crudretrofit.modelo.Dueño;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadActivity extends AppCompatActivity {

    private EditText idDueño;

    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvDireccion;
    private TextView tvTelefono;

    private Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        idDueño = findViewById(R.id.etvIdDueño);

        tvNombre = findViewById(R.id.tvNombre);
        tvApellido = findViewById(R.id.tvApellido);
        tvDireccion = findViewById(R.id.tvDireccion);
        tvTelefono = findViewById(R.id.tvTelefono);

        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long idDue = Long.parseLong(idDueño.getText().toString());

                buscarDueñoPorID(idDue);

            }
        });
    }

    private void buscarDueñoPorID(Long idDueño){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://132.145.34.221:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        DueñoApi dueñoapi = retrofit.create(DueñoApi.class);
        Call<Dueño> call =  dueñoapi.find(idDueño);

        call.enqueue(new Callback<Dueño>() {
            @Override
            public void onResponse(Call<Dueño> call, Response<Dueño> response) {

                try {

                    if (response.isSuccessful()){

                        Dueño due = response.body();

                        tvNombre.setText(due.getNombre());
                        tvApellido.setText(due.getApellidos());
                        tvDireccion.setText(due.getDireccion());
                        tvTelefono.setText(due.getTelefono());

                    }

                }catch (Exception e){

                    Toast.makeText(ReadActivity.this,
                            e.getMessage(),Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Dueño> call, Throwable t) {

                Toast.makeText(ReadActivity.this,
                        "Error de conexion",Toast.LENGTH_LONG).show();

            }
        });

    }
}