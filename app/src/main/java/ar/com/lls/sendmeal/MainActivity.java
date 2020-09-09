package ar.com.lls.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
    private Boolean esCredito;
    private CheckBox acepterminos;
    private int mesElegido,diferenciaM;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //alta variables EditText y botones
        email = (EditText)findViewById(R.id.ETEmail);
        clave = (EditText)findViewById(R.id.ETContraseña);
        clave2 = (EditText)findViewById(R.id.ETrpcontraseña);
        ccv = (EditText)findViewById(R.id.ETccv);
        tarjeta = (EditText)findViewById(R.id.numTarjeta);
        registrar = (Button)findViewById(R.id.buttonRegistrar);
        registrar.setEnabled(false);
        acepterminos=(CheckBox)findViewById(R.id.cBxAcept);

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
        //se fija si es credito o no y lo guarda
            if(rCredito.isChecked()){
                esCredito=true;
            }
            else if(rDebito.isChecked()){
                esCredito=false;
            }
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
            //guardo el mes que eligio en el spinner
            mesElegido= adapter.getPosition(mes);
        //spinner anio
        ArrayAdapter adapter2 = new ArrayAdapter<Integer>(this, simple_spinner_dropdown_item, anio);
        Spinner spinnerAnio = (Spinner) findViewById(R.id.spinnerFechaanio);
        spinnerMes.setAdapter(adapter);

        //si es credito es verdadero, sino falso
        if(rCredito.isChecked()){
            esCredito=true;
        }
        else{
            esCredito=false;
        }

        //slider carga (seekbar)
        seekBarCarga = (SeekBar) findViewById(R.id.seekBarCargaInicial);
        //Valor inicial
        seekBarCarga.setProgress(0);
        //Valor Final
        seekBarCarga.setMax(1500);
        seekBarCarga.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //hace un llamado a la perilla cuando se arrastra
            @Override
            public void onProgressChanged(SeekBar seekBarCarga, int progress, boolean fromUser) {
                carga.setText("$"+String.valueOf(progress));

            }
            //hace un llamado  cuando se toca la perilla
            @Override
            public void onStartTrackingTouch(SeekBar seekBarCarga) {

            }
            //hace un llamado  cuando se detiene la perilla
            @Override
            public void onStopTrackingTouch(SeekBar seekBarCarga) {
                //guardo el valor donde para de mover

            }
        });

        //si clickea el checkbox se creatodo eso
       acepterminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               //si el checkbox esta activado, el boton registrar tambien
               if(acepterminos.isChecked()){
                   registrar.setEnabled(true);
               }
           }
       });


    }


    public boolean validacion(){
            //Verificar que si ingresó una tarjeta de crédito la fecha de vencimiento por lo menos sea superior a los próximos 3 meses
                    if(esCredito == true){
                        //guardo el mes actual del dispositivo
                        final Calendar c = Calendar.getInstance();
                        int mMonth = c.get(Calendar.MONTH);

                        if(mesElegido< mMonth){
                            Toast.makeText(this,"Tarjeta vencida",Toast.LENGTH_LONG).show();
                            return false;
                        }
                            else if(mesElegido==mMonth){
                                Toast.makeText(this,"Tarjeta vencida",Toast.LENGTH_LONG).show();
                                return false;
                            }
                                else { //en teoria cuenta que los meses sean superiores a 3
                                    for(diferenciaM = 0 ; mesElegido < mMonth ; diferenciaM =  diferenciaM+1){ }
                                    if(diferenciaM < 3){
                                        Toast.makeText(this,"El mes de vencimiento debe ser superior a los proximos 3 meses",Toast.LENGTH_LONG).show();
                                        return false;
                                    }
                                    }
                    }
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
                    //Si se activo 'Realizar una carga inicial' el monto del slider debe ser mayor a 0 pesos !!! no anda !!!
                     if(sw.isActivated() && (seekBarCarga.getProgress() == 0)){
                         Toast.makeText(this,"Debe ingresar un monto",Toast.LENGTH_LONG).show();
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