package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.PlatoActivity;
import ar.com.lls.sendmeal.model.Plato;

public class CrearPlato extends AsyncTask<Plato, Void, Long> {
    private PlatoDao platoDao;
    private OnInsertarPlatoResult callback;

    public CrearPlato(PlatoDao platoDao, OnInsertarPlatoResult callback) {
        this.platoDao = platoDao;
        this.callback = callback;
    }

    /*@Override
    protected Void doInBackground(Plato... nuevoPlato) {
       platoDao.insertar(nuevoPlato[0]);//Agrega un plato a la BD
       return null;
    }*/
    @Override
    protected Long doInBackground(Plato... nuevoPlato) {
        Long idPlatoCreado = platoDao.insertar(nuevoPlato[0]);//Agrega un plato a la BD
        return idPlatoCreado;
    }

    /*@Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //callback.onResult(aVoid);
    }*/
    @Override
    protected void onPostExecute(Long idPlatoGuardado) {
        //super.onPostExecute(idPlatoGuardado);
        callback.onResult(idPlatoGuardado);
    }
}
