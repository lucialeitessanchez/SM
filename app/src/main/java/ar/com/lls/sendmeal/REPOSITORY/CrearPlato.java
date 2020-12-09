package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Plato;

public class CrearPlato extends AsyncTask<Plato, Void, Void>{
    private PlatoDao platoDao;
    private OnInsertarPlatoResult callback;

    public CrearPlato(PlatoDao platoDao, OnInsertarPlatoResult callback) {
        this.platoDao = platoDao;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Plato... nuevoPlato) {
       platoDao.insertar(nuevoPlato[0]);//Agrega un plato a la BD
       return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //callback.onResult(aVoid);
    }
}
