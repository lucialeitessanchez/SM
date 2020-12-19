package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PedidoDao;
import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Pedido;
import ar.com.lls.sendmeal.model.Plato;

public class BuscarPedidos extends AsyncTask<String, Void, List<Pedido>> {
    private PedidoDao pedidoDao;
    private OnPedidoResultCallback callback;

    public BuscarPedidos(PedidoDao dao, OnPedidoResultCallback context) {
        this.pedidoDao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        List<Pedido> pedidos = pedidoDao.buscarTodos();
        return pedidos;
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        callback.onResult(pedidos);
    }
}
