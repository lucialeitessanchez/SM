package ar.com.lls.sendmeal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ar.com.lls.sendmeal.REPOSITORY.AppRepository;
import ar.com.lls.sendmeal.REPOSITORY.OnInsertarPedidoPlatoCallback;
import ar.com.lls.sendmeal.REPOSITORY.OnInsertarPedidoResult;
import ar.com.lls.sendmeal.model.NotificacionPedido;
import ar.com.lls.sendmeal.model.Pedido;
import ar.com.lls.sendmeal.model.PedidoPlato;

public class PedidoActivity extends AppCompatActivity implements OnInsertarPedidoResult, OnInsertarPedidoPlatoCallback
{

    private Toolbar toolbar;
    private EditText email, direccion, altura, piso, dpto;
    private TextView dondeEnviamos;
    private RadioGroup takeAwayoNo;
    private RadioButton envioDomicilio,takeAway;
    private RadioButton casa, departamento;
    private Button encargarPlatos,finalizarPedido;
    private ListView vistaPlatosEncargados;
    private TextView montoTotal;

    public ArrayList<String> listaPlatosSeleccionados = new ArrayList<>();
    public ArrayList<Long> listaIdPlatosSeleccionados = new ArrayList<>();
    public Double totalPedido;

    private ProgressBar progressBarPedido;
    public final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 1;

    private AppRepository repository;

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
        casa = findViewById(R.id.RBcasa);
        departamento = findViewById(R.id.RBdepartamento);
        direccion = findViewById(R.id.ETdireccion);
        altura = findViewById(R.id.ETaltura);
        piso = findViewById(R.id.ETpiso);
        dpto = findViewById(R.id.ETdpto);
        encargarPlatos = findViewById(R.id.BTNencargarPlato);
        vistaPlatosEncargados = findViewById(R.id.LVplatosEncargados);
        montoTotal = findViewById(R.id.montoTotal);
        finalizarPedido = (Button) findViewById(R.id.finalizarPedido);
        progressBarPedido = (ProgressBar) findViewById(R.id.progressBarPedido);

        totalPedido = 0.0; //La sumatoria del total del pedido

        casa.setVisibility(View.GONE);
        dondeEnviamos.setVisibility(View.GONE);
        vistaPlatosEncargados.setVisibility(View.GONE);
        montoTotal.setVisibility(View.GONE);
        departamento.setVisibility(View.GONE);
        piso.setVisibility(View.GONE);
        altura.setVisibility(View.GONE);
        direccion.setVisibility(View.GONE);
        dpto.setVisibility(View.GONE);
        finalizarPedido.setVisibility(View.GONE);
        progressBarPedido.setVisibility(View.GONE);


        envioDomicilio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (envioDomicilio.isChecked()) {
                    dondeEnviamos.setVisibility(View.VISIBLE);
                    casa.setVisibility(View.VISIBLE);
                    departamento.setVisibility(View.VISIBLE);
                    direccion.setVisibility(View.VISIBLE);
                    altura.setVisibility(View.VISIBLE);

                    departamento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (departamento.isChecked()) {
                                piso.setVisibility(View.VISIBLE);
                                dpto.setVisibility(View.VISIBLE);

                            } else {
                                piso.setVisibility(View.GONE);
                                dpto.setVisibility(View.GONE);
                            }
                        }
                    });

                } else {
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
                if(validacionOk()) {
                    //int LAUNCH_LISTA_PLATOS_ACTIVITY = 1;
                    Intent i = new Intent(getApplicationContext(), ListaPlatos.class);
                    //Seteo una key en el putExtra para referenciar desde qué actividad estoy llamando
                    i.putExtra("desde pedidoActivity a ListaPlatos", LAUNCH_LISTA_PLATOS_ACTIVITY);
                    startActivityForResult(i, LAUNCH_LISTA_PLATOS_ACTIVITY);
                    vistaPlatosEncargados.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(PedidoActivity.this, "error en los datos ingresados", Toast.LENGTH_LONG).show();
                }
            }
        });
        repository = new AppRepository(this.getApplication());
    }
    @Override
    public Long onResult(Long idPedido) {
        // Mensaje plato creado
        repository.insertarPlatoPedido(listaIdPlatosSeleccionados, idPedido, PedidoActivity.this);
        Toast.makeText(PedidoActivity.this, "Se a creado el pedido solicitado!", Toast.LENGTH_LONG).show();
        return idPedido;
    }

    @Override
    public void onResultPedidoPlatoCallback(){
        Toast.makeText(PedidoActivity.this, "pedido plato creado!", Toast.LENGTH_LONG).show();
    }


    private boolean validacionOk() {
        if(takeAway.isChecked()){
            if(email.getText().toString().isEmpty()){
                return false;
            }
        }
        else {
            if (email.getText().toString().isEmpty()) {
                return false;
            }
            else {
                if (casa.isChecked()) {
                    if (direccion.getText().toString().isEmpty() || altura.getText().toString().isEmpty()) {
                        return false;
                    }
                } else {
                    if (departamento.isChecked()) {
                        if (direccion.getText().toString().isEmpty() || altura.getText().toString().isEmpty() || dpto.getText().toString().isEmpty() || altura.getText().toString().isEmpty()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Comprobamos si el resultado de la segunda actividad es "RESULT_ok",entonces se agarra el resultado de la segunda actividad.
        if(resultCode == RESULT_OK){
            String platoSeleccionado;

            String idPlatoString = data.getExtras().getString("idPlato");
            Long idPlatoLong = Long.valueOf(idPlatoString);
            platoSeleccionado = data.getExtras().getString("nombrePlato"); //trae el nombre y el precio desde la actividad plato adapter
            Double precioSeleccionado = (data.getExtras().getDouble("precioPlato"));

            //aca hay que ir armando la lista y la suma de los precios
            listaPlatosSeleccionados.add(platoSeleccionado);
            listaIdPlatosSeleccionados.add(idPlatoLong);
            totalPedido = totalPedido+precioSeleccionado;

            montoTotal.setVisibility(View.VISIBLE);
            montoTotal.setText("Total: $" + totalPedido.toString());


            ArrayAdapter<String> adapterPedido = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaPlatosSeleccionados);
            vistaPlatosEncargados.setAdapter(adapterPedido);
            //una vez que cargo al menos un plato ya hago visible el boton de finalizar pedido

            finalizarPedido.setVisibility(View.VISIBLE);
            finalizarPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pedido unPedido = new Pedido();
                    unPedido.setEmail(email.getText().toString());
                    unPedido.setEnvioDomicilio(envioDomicilio.isChecked());
                    unPedido.setTakeAway(takeAway.isChecked());
                    unPedido.setCasa(casa.isChecked());
                    unPedido.setDepartamento(departamento.isChecked());
                    unPedido.setDireccion(direccion.getText().toString());
                    unPedido.setAltura(altura.getText().toString());
                    unPedido.setDpto(dpto.getText().toString());
                    unPedido.setPiso(piso.getText().toString());
                    unPedido.setTotalPedido(totalPedido);

                    repository.insertarPedido(unPedido,PedidoActivity.this);

                    new Task(v.getContext()).execute(listaPlatosSeleccionados.toString());
                }
            });

        }

        else{
           // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class Task extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
           progressBarPedido.setVisibility(View.VISIBLE);
           finalizarPedido.setEnabled(false);
        }

        Context ctx;

        Task(Context ctx) { //este es el constructor le seteo el contexto para la notificacion
        this.ctx= ctx;
        }
        // para despues no se si se usa o no, pero la clase que recibe el "pedido" toma el pedido mediante String pedido = getIntent().getStringExtra("laclavepedido");

        @Override
        protected String doInBackground(String... strings) {
            try{
                Thread.sleep(5000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            return  strings[0];
        }
        @Override
        protected void onPostExecute(String s) {

            progressBarPedido.setVisibility(View.INVISIBLE);
            finalizarPedido.setEnabled(true);

            Intent intent = new Intent(ctx,NotificacionPedido.class);
            sendBroadcast(intent);

            Intent intent2 = new Intent(PedidoActivity.this,PedidoActivity.class);
            startActivity(intent2);
        }
    }
}