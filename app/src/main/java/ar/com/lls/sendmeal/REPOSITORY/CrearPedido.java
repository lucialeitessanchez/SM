package ar.com.lls.sendmeal.REPOSITORY;

import android.os.AsyncTask;

import ar.com.lls.sendmeal.DAO.PedidoDao;
import ar.com.lls.sendmeal.model.Pedido;

public class CrearPedido extends AsyncTask<Pedido, Void, Long> {
        private PedidoDao pedidoDao;
        private OnInsertarPedidoResult callback;

        public CrearPedido(PedidoDao pedidoDao, OnInsertarPedidoResult callback) {
                this.pedidoDao = pedidoDao;
                this.callback = callback;
                }

        @Override
        protected Long doInBackground(Pedido... nuevoPedido) {
                Long idPedidoCreado = pedidoDao.insertar(nuevoPedido[0]);//Agrega un plato a la BD
                return idPedidoCreado;
                }

        @Override
        protected void onPostExecute(Long idPedidoGuardado) {
                callback.onResult(idPedidoGuardado);
                }
}
