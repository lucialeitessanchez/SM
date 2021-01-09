package ar.com.lls.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import ar.com.lls.sendmeal.REPOSITORY.AppRepository;
import ar.com.lls.sendmeal.REPOSITORY.OnInsertarPlatoResult;
import ar.com.lls.sendmeal.SERVICES.PlatoService;
import ar.com.lls.sendmeal.model.Plato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlatoActivity extends AppCompatActivity implements OnInsertarPlatoResult {

    private Toolbar toolbar;
    private EditText tituloPlato;
    private EditText descripcionPlato;
    private EditText precioPlato;
    private EditText caloriasPlato;
    private Button btnGuardar;


    Double precioPlatoD;
    Integer caloriasPlatoInt;
    String tituloPlatoStr;
    String descripcionPlatoStr;

    private AppRepository repository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plato);

        //toolbar en crear plato
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //boton atras
        // le pongo el titulo de la actividad
        actionBar.setTitle(R.string.crearPlato);

        repository = new AppRepository(this.getApplication());







        tituloPlato = (EditText)findViewById(R.id.ETtituloPlato) ;
        descripcionPlato = (EditText)findViewById(R.id.ETdescripcionPlato);
        precioPlato = (EditText)findViewById(R.id.ETPrecioPlato);
        caloriasPlato = (EditText)findViewById(R.id.ETcaloriasPlato);



        btnGuardar = (Button)findViewById(R.id.btnGuardarPlato);
        btnGuardar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                if(validarCamposPlato()){
                    tituloPlatoStr = tituloPlato.getText().toString();
                    descripcionPlatoStr = descripcionPlato.getText().toString();
                    precioPlatoD = Double.parseDouble(precioPlato.getText().toString());
                    caloriasPlatoInt = Integer.parseInt(caloriasPlato.getText().toString());

                    Plato platoCreado = new Plato(null,tituloPlatoStr,descripcionPlatoStr,precioPlatoD,caloriasPlatoInt);
                    repository.insertarPlato(platoCreado, PlatoActivity.this);
                    Gson gson = new GsonBuilder().setLenient().create();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.0.45/")
                            // En la siguiente linea, le especificamos a Retrofit que tiene que usar Gson para deserializar nuestros objetos
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
                    PlatoService platoService = retrofit.create(PlatoService.class);
                    Call<Plato> callPlatos = platoService.createPlato(platoCreado);

                    callPlatos.enqueue(
                            new Callback<Plato>() {
                                @Override
                                public void onResponse(Call<Plato> call, Response<Plato> response) {
                                    if (response.code() == 200) {
                                        Log.d("DEBUG", "Returno Exitoso");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Plato> call, Throwable t) {
                                    Log.d("DEBUG", "Returno Fallido");
                                }
                            }
                    );
                    //limpio para que pueda ingresar otro nuevo plato si quiere
                    tituloPlato.setText("");
                    descripcionPlato.setText("");
                    precioPlato.setText("");
                    caloriasPlato.setText("");
                }
                else{
                    Toast.makeText(PlatoActivity.this,"Faltan ingresar campos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onResult(Long idPlato) {
        // Mensaje plato creado
        Toast.makeText(PlatoActivity.this, "(PlatoActivity) plato creado!", Toast.LENGTH_LONG).show();
    }

    public boolean validarCamposPlato(){
        if(tituloPlato.getText().toString().isEmpty()){
            Toast.makeText(PlatoActivity.this,"Debe ingresar un titulo",Toast.LENGTH_LONG).show();
            return false;
        }
        if(descripcionPlato.getText().toString().isEmpty()){
            Toast.makeText(PlatoActivity.this,"Debe ingresar una descripcion",Toast.LENGTH_LONG).show();
            return false;
        }
        if(precioPlato.getText().toString().isEmpty()){
            Toast.makeText(PlatoActivity.this,"Debe ingresar un precio valido",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}