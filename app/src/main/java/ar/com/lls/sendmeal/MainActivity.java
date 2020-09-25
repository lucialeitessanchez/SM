package ar.com.lls.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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



import java.util.Calendar;

import static android.R.layout.simple_spinner_dropdown_item;

public class MainActivity extends AppCompatActivity {
    private final static Integer[] mes = {1,2,3,4,5,6,7,8,9,10,11,12};
    private final static Integer[] anio = {2020,2021,2022,2023};
    private SeekBar mSeekBarCarga;
    private TextView mTextCarga;

    private EditText mEmail, mClave, mClave2, mCcv, mTarjeta;
    private RadioButton rCredito,rDebito;
    private Switch mSwitch1;
    private Button mButtonRegistrar;
    private CheckBox mCheckAcepTerminos;
    private int mMesElegido, mAnioElegido;
    private Spinner spinnerAnio, spinnerMes;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //alta variables EditText y botones

        mEmail = (EditText)findViewById(R.id.ETEmail);
        mClave = (EditText)findViewById(R.id.ETContraseña);
        mClave2 = (EditText)findViewById(R.id.ETrpcontraseña);
        mCcv = (EditText)findViewById(R.id.ETccv);
        mTarjeta = (EditText)findViewById(R.id.numTarjeta);
        mButtonRegistrar = (Button)findViewById(R.id.buttonRegistrar);
        mButtonRegistrar.setEnabled(false);
        mCheckAcepTerminos =(CheckBox)findViewById(R.id.cBxAcept);
        //radiogroups
        rCredito=(RadioButton)findViewById(R.id.rBCredito);
        rDebito=(RadioButton)findViewById(R.id.rBDebito);

        //toolbar
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //boton atras
        // le pongo el titulo de la actividad
        actionBar.setTitle(R.string.Registrar);


        //el correo no este vacio
        if(mEmail.getText().toString().isEmpty()) {
            mEmail.setError("El correo esta vacio");
        }

        //la clave no sea vacia
        if(mClave.getText().toString().isEmpty()) {
            mClave.setError("La clave es vacia ");
        }

        //la tarjeta vacia
        if(mTarjeta.getText().toString().isEmpty()){
            mTarjeta.setError("El numero de tarjeta es obligatorio ");
        }
        //ccv no vacio
        if(mCcv.getText().toString().isEmpty()){
            mCcv.setError("El codigo es obligatorio ");
        }
        //los radiobutton seleccionado alguno de los dos
        if(!(rCredito.isChecked()) && !(rDebito.isChecked())){
            Toast.makeText(MainActivity.this, "Ingresar un tipo de tarjeta", Toast.LENGTH_LONG).show();
        }
        
        //Switch pregunta carga inicial
        mSwitch1 = (Switch)findViewById(R.id.switch1);
        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //se fija si esta activado, si es asi, lo muestra, junto con el valor y si no los mantiene ocultos, en el XML estan como ocultos ambos
                if(mSwitch1.isChecked()){
                    mSeekBarCarga.setVisibility(View.VISIBLE);
                    mTextCarga.setVisibility(View.VISIBLE);
                }
                else{
                    mSeekBarCarga.setVisibility(View.GONE);
                    mTextCarga.setVisibility(View.GONE);
                }
            }
        });
        //slider
        mTextCarga = (TextView)findViewById(R.id.txtCargar);
        //spinner mes
        ArrayAdapter adapter = new ArrayAdapter<Integer>(this, simple_spinner_dropdown_item, mes);
        spinnerMes = (Spinner) findViewById(R.id.spinnerFechames);
        spinnerMes.setAdapter(adapter);
        //guardo el mes que eligio en el spinner
        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mMesElegido = mes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

            //spinner anio
        ArrayAdapter adapter2 = new ArrayAdapter<Integer>(this, simple_spinner_dropdown_item, anio);
        spinnerAnio = (Spinner) findViewById(R.id.spinnerFechaanio);
        spinnerAnio.setAdapter(adapter2);

        spinnerAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAnioElegido = anio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //slider carga (seekbar)
        mSeekBarCarga = (SeekBar) findViewById(R.id.seekBarCargaInicial);
        //Valor inicial
        mSeekBarCarga.setProgress(0);
        //Valor Final
        mSeekBarCarga.setMax(1500);
        mSeekBarCarga.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //hace un llamado a la perilla cuando se arrastra
            @Override
            public void onProgressChanged(SeekBar seekBarCarga, int progress, boolean fromUser) {
                mTextCarga.setText("$"+String.valueOf(progress));

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
       mCheckAcepTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
           //si el checkbox esta activado, el boton registrar tambien y entra a la validacion
           if(mCheckAcepTerminos.isChecked()){
               mButtonRegistrar.setEnabled(true);
                   mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           //lo que haga el boton registrar
                           if(validacion()) {
                               Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                           }
                           else{
                               Toast.makeText(MainActivity.this, "ERROR: Verifique los datos ingresados. Puede faltar campos por completar.", Toast.LENGTH_LONG).show();
                           }
                       }
                   });
           }
           else { // si no se desctiva el boton registrar
               Toast.makeText(MainActivity.this,"No acepto terminos y condiciones",Toast.LENGTH_LONG).show();
               mButtonRegistrar.setEnabled(false);
           }
           }
       });
    }



    public boolean validacion(){
        //valida que las claves sean iguales
        if(!((mClave.getText().toString()).equals((mClave2.getText().toString())) && !(mClave.getText().toString().equals("")))){
            Toast.makeText(this,"Las claves no coinciden",Toast.LENGTH_LONG).show();
            mClave2.setError("Las claves no coinciden");
            return false;
        }

        //llama a la funcion validarEmail para ver si tiene el arroba y los 3 strings detra
        if(!validarEmail()){
            mEmail.setError("El correo es invalido. Verifique su formato");
            Toast.makeText(this,"El correo es invalido",Toast.LENGTH_LONG).show();
            return false;
        }

        if(!(rCredito.isChecked()) && !(rDebito.isChecked())){
            rCredito.setError("Ingresar un tipo de tarjeta");
            Toast.makeText(MainActivity.this, "Ingresar un tipo de tarjeta", Toast.LENGTH_LONG).show();
            return false;
        }

        //la tarjeta vacia
        if(mTarjeta.getText().toString().isEmpty()){
            mTarjeta.setError("El numero de tarjeta es obligatorio ");
            return false;
        }

        //ccv no vacio
        if(mCcv.getText().toString().isEmpty()){
            mCcv.setError("El codigo es obligatorio ");
            return false;
        }

        //Verificar que si ingresó una tarjeta de crédito la fecha de vencimiento por lo menos sea superior a los próximos 3 meses
        if(rCredito.isChecked()){
            //guardo el mes actual del dispositivo
            final Calendar c = Calendar.getInstance();
            int mMonthActual = c.get(Calendar.MONTH) + 1;
            int mAnioActual = c.get(Calendar.YEAR);
            boolean mCumpleCondicion = false;

            if (mAnioActual == mAnioElegido){
                if (mMonthActual < mMesElegido) {
                    mCumpleCondicion =  ((mMesElegido - mMonthActual) > 3) ? true : false;
                }
                else if(mMonthActual >= mMesElegido) {
                    mTarjeta.setError("Tarjeta vencida");
                    Toast.makeText(this,"Tarjeta vencida" ,Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            else if (mAnioActual > mAnioElegido) {
                mTarjeta.setError("Tarjeta vencida");
                Toast.makeText(this,"Tarjeta vencida",Toast.LENGTH_LONG).show();
                return false;
            }
            else if (mAnioActual < mAnioElegido) {
                if ( (mAnioElegido - mAnioActual) <= 1 ) {
                    if (mMonthActual == mMesElegido) {
                        mCumpleCondicion = true;
                    }
                    else {
                        switch (mMonthActual){
                            case 12:
                                mCumpleCondicion = (mMesElegido <= 3) ? false : true;
                                break;
                            case 11:
                                mCumpleCondicion = (mMesElegido <= 2) ? false : true;
                                break;
                            case 10:
                                mCumpleCondicion = (mMesElegido == 1) ? false : true;
                                break;
                            default:
                                mCumpleCondicion = true;
                                break;
                        }
                    }
                }
                else {
                    mCumpleCondicion = true;
                }
            }

            if(mCumpleCondicion == false){
                mTarjeta.setError("El mes de vencimiento debe ser superior a los proximos 3 meses");
                Toast.makeText(this,"El mes de vencimiento debe ser superior a los proximos 3 meses",Toast.LENGTH_LONG).show();
                return false;
            }
        }

        //Si se activo 'Realizar una carga inicial' el monto del slider debe ser mayor a 0 pesos !!! no anda !!!
         if(mSwitch1.isChecked() && (mSeekBarCarga.getProgress() == 0)){
             Toast.makeText(this,"Debe ingresar un monto",Toast.LENGTH_LONG).show();
             return false;
         }

         return true;
    }

    private boolean validarEmail() {
        if(mEmail.getText().toString().contains("@")){
            int indice = mEmail.getText().toString().indexOf("@");
            int j = 0;
            for(int i = (indice + 1); i < mEmail.getText().length(); i++){
                j++;
            }
            return (j >= 3);
        }
        return false;
    }



}