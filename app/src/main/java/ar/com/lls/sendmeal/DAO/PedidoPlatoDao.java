package ar.com.lls.sendmeal.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ar.com.lls.sendmeal.model.Pedido;
import ar.com.lls.sendmeal.model.PedidoPlato;
import ar.com.lls.sendmeal.model.Plato;

@Dao
public interface PedidoPlatoDao {

    @Insert
    void insertar(PedidoPlato unPedidoPlato);

    @Query("SELECT * FROM pedido INNER JOIN pedido_plato ON pedido.id=pedido_plato.pedido_id Where pedido_plato.plato_id=:platoId")
    List<Pedido> getPedidoPorPlato(final long platoId);


    @Query("SELECT * FROM plato INNER JOIN pedido_plato ON plato.id=pedido_plato.plato_id Where pedido_plato.pedido_id=:pedidoId")
    List<Plato> getPlatoPorPedido(final long pedidoId);

}
