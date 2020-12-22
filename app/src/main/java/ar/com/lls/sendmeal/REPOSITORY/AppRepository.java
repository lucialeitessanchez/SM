package ar.com.lls.sendmeal.REPOSITORY;

import android.app.Application;
import android.util.Log;

import java.util.List;

import ar.com.lls.sendmeal.DAO.PedidoDao;
import ar.com.lls.sendmeal.DAO.PedidoPlatoDao;
import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Pedido;
import ar.com.lls.sendmeal.model.PedidoPlato;
import ar.com.lls.sendmeal.model.Plato;


public class AppRepository{
    private PlatoDao platoDao;
    private PedidoDao pedidoDao;
    private PedidoPlatoDao pedidoplatoDao;

    private OnResultCallback callback;


    public AppRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
        pedidoDao = db.pedidoDao();
        pedidoplatoDao = db.pedidoPlatoDao();
    }

    public void insertarPlato(final Plato platoACrear, OnInsertarPlatoResult callback) {
        new CrearPlato(platoDao,callback).execute(platoACrear);
    }

    public void insertarPedido(final Pedido pedidoACrear, OnInsertarPedidoResult callback) {
        new CrearPedido(pedidoDao,callback).execute(pedidoACrear);
    }

    public void insertarPlatoPedido(final List<Long> idPlatos, final Long Idpedido, OnInsertarPedidoPlatoCallback callback) {
        final int size = idPlatos.size();
        for (int i = 0; i < size; i++) {
            Long idPlato = idPlatos.get(i);
            PedidoPlato pedidoPlato = new PedidoPlato(Idpedido, idPlato);

            new CrearPedidoPlato(pedidoplatoDao, callback).execute(pedidoPlato);
        }
    }

    public void borrarPlato(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.borrar(plato);
            }
        });
    }

    public void borrarPedido(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.borrar(pedido);
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

    public void buscarPlato(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }

    public void buscarPedido(String id) {
        new BuscarPedidoById(pedidoDao, this).execute(id);
    }

    public void buscarTodosPlatos(OnPlatoResultCallback callback){
        new BuscarPlatos(platoDao, callback).execute();
    }

    public void buscarTodosPedidos(OnPedidoResultCallback callback){
        new BuscarPedidos(pedidoDao, callback).execute();
    }


    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
