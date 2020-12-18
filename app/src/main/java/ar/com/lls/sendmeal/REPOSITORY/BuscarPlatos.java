package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Plato;


public class BuscarPlatos extends AsyncTask<String, Void, List<Plato>> {
    private PlatoDao platoDao;
    private OnPlatoResultCallback callback;

    public BuscarPlatos(PlatoDao dao, OnPlatoResultCallback context) {
        this.platoDao = dao;
        this.callback = context;
    }

    @Override
    protected List<Plato> doInBackground(String... strings) {
        List<Plato> platos = platoDao.buscarTodos();
        return platos;
    }

    @Override
    protected void onPostExecute(List<Plato> platos) {
        callback.onResult(platos);
    }
}
