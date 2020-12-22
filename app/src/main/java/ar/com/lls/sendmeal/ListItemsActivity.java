package ar.com.lls.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ar.com.lls.sendmeal.REPOSITORY.AppRepository;
import ar.com.lls.sendmeal.REPOSITORY.OnPedidoResultCallback;
import ar.com.lls.sendmeal.REPOSITORY.OnPlatoResultCallback;
import ar.com.lls.sendmeal.model.Plato;

public class ListItemsActivity extends AppCompatActivity implements OnPedidoResultCallback {

    private AppRepository repository;
    List<Plato> pedidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        repository = new AppRepository(this.getApplication());
        repository.buscarTodosPedidos(this);
    }

    @Override
    public List onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalize
        Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
         pedidos = result;
        return pedidos;
    }
}