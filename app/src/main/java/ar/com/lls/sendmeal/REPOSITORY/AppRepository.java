package ar.com.lls.sendmeal.REPOSITORY;

import android.app.Application;
import android.util.Log;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Plato;


public class AppRepository{
    private PlatoDao platoDao;
    private OnResultCallback callback;


    public AppRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
    }

    public void insertar(final Plato platoACrear, OnInsertarPlatoResult callback) {
        new CrearPlato(platoDao,callback).execute(platoACrear);
    }

    public void borrar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.borrar(plato);
            }
        });
    }

    public void actualizar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.actualizar(plato);
            }
        });
    }

    public void buscar(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }

    public void buscarTodos(OnPlatoResultCallback callback){
        new BuscarPlatos(platoDao, callback).execute();
    }

    //@Override
    public void onResult(List<Plato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onResult(platos);
    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
