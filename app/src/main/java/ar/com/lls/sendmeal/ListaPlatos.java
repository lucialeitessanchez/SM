package ar.com.lls.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;

import ar.com.lls.sendmeal.model.Plato;
import ar.com.lls.sendmeal.model.PlatoAdapter;

public class ListaPlatos extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        //toolbar en crear plato
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); //boton atras
        // le pongo el titulo de la actividad
        actionBar.setTitle(R.string.ListaPlatos);

        //Inicializar Platos
        //List items = new ArrayList();



        //agrego los platos a pata
        /*items.add(new Plato("Pollo", "Pollo a la parrilla con papas", 230.0, 200));
        items.add(new Plato("Sushi", "8 rolls de sushi clàsico", 300.50, 480));
        items.add(new Plato("Canelones", "Canelones de verdura", 500.0, 393));
        items.add(new Plato("Asado", "tira de asado a la parrilla", 900.0, 471));*/

        //Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.listaPlatosRecyclerView);
        recycler.setHasFixedSize(true);

        //Uso un administrador para LinearLayout
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        //Crea un nuevo adaptador
        Plato varPlato = new Plato();
        List<Plato> listaItems = varPlato.getListaPlatos();
        adapter = new PlatoAdapter(listaItems);
        recycler.setAdapter(adapter);
        varPlato.inicializarPlatos(); // fijate esto si no como programarlo mejor

        Bundle b = getIntent().getExtras();

        if(b!=null){
            //Pregunto si la clave que fue seteada en PedidoActivity
            if(b.containsKey("desde pedidoActivity a ListaPlatos")){
                //Mostrar botón "pedir" porque significa que ListaPlatos se llamó desde PedidoActivity

                //Log.i("MI ACTIVITY", "El valor del check fue " + check);
            }
        }
    }
}