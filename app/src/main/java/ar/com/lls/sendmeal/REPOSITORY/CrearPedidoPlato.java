package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;

import ar.com.lls.sendmeal.DAO.PedidoPlatoDao;
import ar.com.lls.sendmeal.model.PedidoPlato;

public class CrearPedidoPlato extends AsyncTask<PedidoPlato, Void, Long> {
    private PedidoPlatoDao pedidoPlatoDao;
    private OnInsertarPedidoPlatoCallback callback;

    public CrearPedidoPlato(PedidoPlatoDao pedidoPlatoDao, OnInsertarPedidoPlatoCallback callback) {
        this.pedidoPlatoDao = pedidoPlatoDao;
        this.callback = callback;
    }

    @Override
    protected Long doInBackground(PedidoPlato... pedidoPlatos) {
        pedidoPlatoDao.insertar(pedidoPlatos[0]); //Agrega un plato a la BD
        return null;
    }

    @Override
    protected void onPostExecute(Long idPlatoGuardado) {
        callback.onResultPedidoPlatoCallback();
    }
}
