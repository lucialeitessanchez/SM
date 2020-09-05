package ar.com.lls.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.layout.simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity {
    private final static Integer[] mes = {1,2,3,4,5,6,7,8,9,10,11,12 };
    private final static Integer[] anio = {2020,2021,2022,2023 };
    private SeekBar seekBarCarga;
    private TextView carga;
    private RadioButton rCredito,rDebito;
    private Switch sw;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //radiogroups
        rCredito=(RadioButton)findViewById(R.id.rBCredito);
        rDebito=(RadioButton)findViewById(R.id.rBDebito);
        //Switch pregunta carga inicial
        sw = (Switch)findViewById(R.id.switch1);
        if(sw.isActivated()){
            seekBarCarga.setVisibility(View.VISIBLE);
        }
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
                carga.setText(String.valueOf(progress)+"$");
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


    }
}