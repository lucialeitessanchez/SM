package ar.com.lls.sendmeal.REPOSITORY;

import java.util.List;

import ar.com.lls.sendmeal.model.Pedido;

public interface OnPedidoResultCallback {
    List<Pedido> onResult(List<Pedido> pedidos);
}
