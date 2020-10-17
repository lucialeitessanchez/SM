package ar.com.lls.sendmeal.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ar.com.lls.sendmeal.model.Pedido;

@Dao
public interface PedidoDao {
    @Insert
    void insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedidos WHERE id = :id LIMIT 1")
    Pedido buscar(String id);

    @Query("SELECT * FROM plato")
    List<Pedido> buscarTodos();
}