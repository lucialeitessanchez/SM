package ar.com.lls.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email, direccion, altura, piso, dpto;
    private TextView dondeEnviamos;
    private RadioGroup takeAwayoNo;
    private RadioButton envioDomicilio,takeAway;
    private RadioButton casa, departamento;
    private Button encargarPlatos;

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

                Intent i = new Intent(getApplicationContext(),ListaPlatos.class);
                startActivityForResult(i,1);
            }
        });

    }
}