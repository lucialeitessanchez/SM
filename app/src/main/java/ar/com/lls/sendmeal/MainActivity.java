package ar.com.lls.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.R.layout.simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity {
    private final static Integer[] mes = {1,2,3,4,5,6,7,8,9,10,11,12 };
    private final static Integer[] anio = {2020,2021,2022,2023 };
    private SeekBar seekBarCarga;
    private TextView carga;
    private EditText email,clave,clave2,ccv,tarjeta;
    private RadioButton rCredito,rDebito;
    private Switch sw;
    private Button registrar;
    int p=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //alta variables EditText y el boton
        email = (EditText)findViewById(R.id.ETEmail);
        clave = (EditText)findViewById(R.id.ETContraseña);
        clave2 = (EditText)findViewById(R.id.ETrpcontraseña);
        ccv = (EditText)findViewById(R.id.ETccv);
        tarjeta = (EditText)findViewById(R.id.numTarjeta);
        registrar = (Button)findViewById(R.id.buttonRegistrar);
        registrar.setEnabled(false);
        //el correo no este vacio
        if(email.getText().toString().isEmpty()){
            email.setError("El correo esta vacio");
        }

        //la clave no sea vacia
        if(clave.getText().toString().isEmpty()){
            clave.setError("La clave es vacia ");
        }

        //la tarjeta vacia
        if(tarjeta.getText().toString().isEmpty()){
            tarjeta.setError("El numero de tarjeta es obligatorio ");
        }
        //ccv no vacio
        if(ccv.getText().toString().isEmpty()){
            ccv.setError("El codigo es obligatorio ");
        }
        //radiogroups
        rCredito=(RadioButton)findViewById(R.id.rBCredito);
        rDebito=(RadioButton)findViewById(R.id.rBDebito);
        //Switch pregunta carga inicial
        sw = (Switch)findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //se fija si esta activado, si es asi, lo muestra, junto con el valor y si no los mantiene ocultos, en el XML estan como ocultos ambos
                if(sw.isChecked()){
                    seekBarCarga.setVisibility(View.VISIBLE);
                    carga.setVisibility(View.VISIBLE);
                }
                else{
                    seekBarCarga.setVisibility(View.GONE);
                    carga.setVisibility(View.GONE);
                }
            }
        });
        //slider
        carga = (TextView)findViewById(R.id.txtCargar);
        //spinner mes
        ArrayAdapter adapter = new ArrayAdapter<Integer>(this, simple_spinner_dropdown_item, mes);
        Spinner spinnerMes = (Spinner) findViewById(R.id.spinnerFechames);
        spinnerMes.setAdapter(adapter);

        //spinner anio
        ArrayAdapter adapter2 = new ArrayAdapter<Integer>(this, simple_spinner_dropdown_item, anio);
        Spinner spinnerAnio = (Spinner) findViewById(R.id.spinnerFechaanio);
        spinnerMes.setAdapter(adapter);
        //slider carga (seekbar)
        seekBarCarga = (SeekBar) findViewById(R.id.seekBarCargaInicial);

        //Valor inicial
        seekBarCarga.setProgress(0);
        //Valor Final
        seekBarCarga.setMax(1500);
        seekBarCarga.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //hace un llamado a la perilla cuando se arrastra
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                carga.setText("$"+String.valueOf(progress));
            }
            //hace un llamado  cuando se toca la perilla
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //hace un llamado  cuando se detiene la perilla
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //guardo el valor donde para de mover
                p=seekBar.getProgress();
            }
        });

        //Botón Registrar

        if(Validar()){
            registrar.setEnabled(true);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Validar()){

                }
            }
        });
    }}


    public boolean Validar(){
            //Verificar que si ingresó una tarjeta de crédito la fecha de vencimiento por lo menos sea superior a los próximos 3 meses

            //llama a la funcion validarEmail para ver si tiene el arroba y los 3 strings detras
            if(!validarEmail()){
                Toast.makeText(this,"El correo es invalido",Toast.LENGTH_LONG).show();
                return false;
            }
                //valida que las claves sean iguales
                if((clave.getText().toString()).equals((clave2.getText().toString()))){
                    Toast.makeText(this,"Las claves no coinciden",Toast.LENGTH_LONG).show();
                    return false;
                }
                    //carga inicial mayor a 0
                    if(p == 0 && p<0 ){
                    return false;
                                        }


return true;
    }
    private boolean validarEmail() {
        if(email.getText().toString().contains("@")){
            int indice = email.getText().toString().indexOf("@");
            int j = 0;
            for(int i = (indice + 1); i < email.getText().length(); i++){
                j++;
            }
            return (j >= 3);
        }
        return false;
    }

}