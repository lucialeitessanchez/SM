package ar.com.lls.sendmeal.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity
        (       tableName = "pedido_plato",
                primaryKeys = {"pedido_id","plato_id"},
                foreignKeys ={
                    @ForeignKey(entity = Pedido.class,
                                parentColumns = "id",
                                childColumns = "pedido_id"), //Este nombre indica el nombre de la columna  de la BD
                    @ForeignKey(entity = Plato.class,
                        parentColumns = "id",
                        childColumns = "plato_id")
        })
public class PedidoPlato {
    @ColumnInfo(name="pedido_id") //tiene que coincidir con el childcolumns
    @NonNull
    Long idPedido;

    @ColumnInfo(name="plato_id")
    @NonNull
    Long idPlato;

    public PedidoPlato(Long idPedido, Long idPlato) {
        this.idPedido = idPedido;
        this.idPlato = idPlato;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }





}
