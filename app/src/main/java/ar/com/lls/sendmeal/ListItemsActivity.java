package ar.com.lls.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import ar.com.lls.sendmeal.REPOSITORY.AppRepository;

public class ListItemsActivity extends AppCompatActivity implements AppRepository.OnResultCallback {

    private AppRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        repository = new AppRepository(this.getApplication(), this);
        repository.buscarTodos();
    }

    @Override
    public void onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalize
        Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
    }
}