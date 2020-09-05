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
    private EditText email,clave,clave2,ccv,correo;
    private RadioButton rCredito,rDebito;
    private Switch sw;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //alta variables EditText
        email = (EditText)findViewById(R.id.ETEmail);
        clave = (EditText)findViewById(R.id.ETContraseña);
        clave2 = (EditText)findViewById(R.id.ETrpcontraseña);
        ccv = (EditText)findViewById(R.id.ETccv);
        correo = (EditText)findViewById(R.id.ETEmail);
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
        //tv
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

            }
        });

        //Botón Registrar

    }


    public boolean Validar(){
        //Valida que el correo no este vacio
        if(correo.getText().toString().isEmpty()){
            correo.setError("El correo esta vacio");
            return false;
        }
            //llama a la funcion validarEmail para ver si tiene el arroba y los 3 strings detras
            if(!validarEmail()){
                Toast.makeText(this,"El correo es invalido",Toast.LENGTH_LONG).show();
                return false;
            }
                //valida que la clave no sea vacia
                if(clave.getText().toString().isEmpty()){
                    Toast.makeText(this,"La clave es vacia ",Toast.LENGTH_LONG).show();
                    return false;
                }
                //valida que las claves sean iguales
                if((clave.getText().toString()).equals((clave2.getText().toString()))){
                    Toast.makeText(this,"Las claves no coinciden",Toast.LENGTH_LONG).show();
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