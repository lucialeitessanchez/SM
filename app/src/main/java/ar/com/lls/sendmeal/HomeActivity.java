package ar.com.lls.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //inicializacion del drawer y nav para la toolbar
        setToolBar();
        drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);

        //cada vez que toque un item del menu hamburguesa, entra a cada pantalla
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               Intent i;
                switch (item.getItemId()){
                    //si toca el icono de registrar, entra a la clase de registro
                    case (R.id.menu_Registrar):
                        i = new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(i);
                        break;
                    case(R.id.menu_nuevoPlato):
                        i = new Intent(HomeActivity.this,PlatoActivity.class);
                        startActivity(i);
                        break;
                    case(R.id.menu_ListaPlatos):
                        // listar platos
                        i = new Intent(HomeActivity.this,ListaPlatos.class);
                        startActivity(i);
                        break;
                    case(R.id.menu_pedido):
                        // listar platos
                        i = new Intent(HomeActivity.this,PedidoActivity.class);
                        startActivity(i);
                        break;
                    case(R.id.menu_informacion):
                        // tirar informacion de la aplicacion
                        i = new Intent(HomeActivity.this,HomeActivity.class);
                        Toast.makeText(HomeActivity.this,"Laboratorio de Gauchat,Leites,Piedrabuena",Toast.LENGTH_LONG).show();
                        startActivity(i);
                        break;
                    case(R.id.lista_pedidos):
                        // tirar informacion de la aplicacion
                        i = new Intent(HomeActivity.this,ListItemsActivity.class);
                        startActivity(i);
                        break;
                }
                return false;
            }
        });

    }

    //agrega la toolbar y le agrega la imagen que cree en drawable (el menu hamburguesa)
    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //programa la accion del menu dezplegable
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }






}