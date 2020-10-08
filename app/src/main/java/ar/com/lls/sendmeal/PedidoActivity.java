package ar.com.lls.sendmeal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email, direccion, altura, piso, dpto;
    private TextView dondeEnviamos;
    private RadioGroup takeAwayoNo;
    private RadioButton envioDomicilio,takeAway;
    private RadioButton casa, departamento;
    private Button encargarPlatos;
    public ArrayList<String> listaPlatosSeleccionados = new ArrayList<>();
    public Double totalPedido;
    private ListView vistaPlatosEncargados;
    private TextView montoTotal;

    public static final int LAUNCH_LISTA_PLATOS_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //Flechita para volver atrás
        actionBar.setTitle(R.string.realizarPedido);

        email = findViewById(R.id.ETEmail);
        envioDomicilio = findViewById(R.id.RBenvio);
        takeAway = findViewById(R.id.RBtakeAway);
        dondeEnviamos = findViewById(R.id.TVdondeEnviamos);
        dondeEnviamos.setVisibility(View.GONE);
        casa = findViewById(R.id.RBcasa);
        casa.setVisibility(View.GONE);
        departamento = findViewById(R.id.RBdepartamento);
        departamento.setVisibility(View.GONE);
        direccion = findViewById(R.id.ETdireccion);
        direccion.setVisibility(View.GONE);
        altura = findViewById(R.id.ETaltura);
        altura.setVisibility(View.GONE);
        piso = findViewById(R.id.ETpiso);
        piso.setVisibility(View.GONE);
        dpto = findViewById(R.id.ETdpto);
        dpto.setVisibility(View.GONE);
        encargarPlatos = findViewById(R.id.BTNencargarPlato);
        totalPedido = 0.0;
        vistaPlatosEncargados = findViewById(R.id.LVplatosEncargados);
        vistaPlatosEncargados.setVisibility(View.GONE);
        montoTotal = findViewById(R.id.montoTotal);
        montoTotal.setVisibility(View.GONE);


        envioDomicilio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(envioDomicilio.isChecked()){
                    dondeEnviamos.setVisibility(View.VISIBLE);
                    casa.setVisibility(View.VISIBLE);
                    departamento.setVisibility(View.VISIBLE);
                    direccion.setVisibility(View.VISIBLE);
                    altura.setVisibility(View.VISIBLE);
                    departamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(departamento.isChecked()){
                                piso.setVisibility(View.VISIBLE);
                                dpto.setVisibility(View.VISIBLE);
                            }
                            else{
                                piso.setVisibility(View.GONE);
                                dpto.setVisibility(View.GONE);
                            }
                        }
                    });

                }
                else{
                    dondeEnviamos.setVisibility(View.GONE);
                    casa.setVisibility(View.GONE);
                    departamento.setVisibility(View.GONE);
                    direccion.setVisibility(View.GONE);
                    altura.setVisibility(View.GONE);
                }
            }
        });

        encargarPlatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int LAUNCH_LISTA_PLATOS_ACTIVITY = 1;
                Intent i = new Intent(getApplicationContext(),ListaPlatos.class);
                //Seteo una key en el putExtra para referenciar desde qué actividad estoy llamando
                i.putExtra("desde pedidoActivity a ListaPlatos", LAUNCH_LISTA_PLATOS_ACTIVITY);
                startActivityForResult(i,LAUNCH_LISTA_PLATOS_ACTIVITY);
                vistaPlatosEncargados.setVisibility(View.VISIBLE);
                montoTotal.setVisibility(View.VISIBLE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Comprobamos si el resultado de la segunda actividad es "RESULT_ok",entonces se agarra el resultado de la segunda actividad.
        if(resultCode == RESULT_OK){
            String platoSeleccionado;

            platoSeleccionado = data.getExtras().getString("nombrePlato"); //trae el nombre y el precio desde la actividad plato adapter
            Double precioSeleccionado = (data.getExtras().getDouble("precioPlato"));

            //aca hay que ir armando la lista y la suma de los precios
            listaPlatosSeleccionados.add(platoSeleccionado);
            totalPedido = totalPedido+precioSeleccionado;

            montoTotal.setText("Total: $" + totalPedido.toString());


            ArrayAdapter<String> adapterPedido = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaPlatosSeleccionados);
            vistaPlatosEncargados.setAdapter(adapterPedido);

        }

        else{
           // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}