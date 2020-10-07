package ar.com.lls.sendmeal.model;

import android.app.Activity;
import android.content.Intent;
import android.nfc.cardemulation.CardEmulation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.com.lls.sendmeal.ListaPlatos;
import ar.com.lls.sendmeal.R;

import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {
    private final boolean fromPedidoActivity;
    private List<Plato> items;
    Activity miActivity;


    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un plato
        public ImageView imagen;
        public TextView titulo;
        public TextView precio;
        public TextView descripcion;
        public CardView cardView;
        public CheckedTextView cbSeleccionar;
        private Activity listaPlato;
        public ImageView imgSeleccionar;



        public PlatoViewHolder(View v) {
            super(v);
            this.listaPlato = listaPlato;
            imagen = (ImageView) v.findViewById(R.id.imagenPlato);
            titulo = (TextView) v.findViewById(R.id.tituloPlato);
            precio = (TextView) v.findViewById(R.id.precioPlato);
            descripcion = (TextView) v.findViewById(R.id.descripcionPlato);
            cardView = (CardView) v.findViewById(R.id.card_view);
            cbSeleccionar = (CheckedTextView) v.findViewById(R.id.cbPedir);
            imgSeleccionar = (ImageView) v.findViewById(R.id.imagenPlato);

            cbSeleccionar.setVisibility(GONE); //los pongo invisible hasta no saber de donde viene
            imgSeleccionar.setVisibility(GONE);
        }
    }

    public PlatoAdapter(List<Plato> items, boolean desdePedidoActivityOno, Activity laActivity ){
        this.items = items;
        this.fromPedidoActivity = desdePedidoActivityOno;
        this.miActivity = laActivity;
    }

    public boolean fueInvocadaDesdePedidoAct(){
        if(fromPedidoActivity){
            return true;
        }
        else{
            return false;
        }
    }


    @NonNull
    @Override
    public PlatoAdapter.PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        return new PlatoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlatoAdapter.PlatoViewHolder holder, int position) {
        //holder.imagen.setImageResource(items.get(position).getImagen()); por ahora no porque va la imagen para todos igual
        holder.titulo.setText(items.get(position).getTitulo());
        holder.precio.setText("$"+items.get(position).getPrecio().toString()); //ojo hay que hacerlo string porque devuelve un entero
        holder.descripcion.setText(items.get(position).getDescripcion());

        final Double precioPlatoSeleccionado = items.get(position).getPrecio();

        if(fueInvocadaDesdePedidoAct()){  //se hacen visibles si vienen desde la clase pedido
            holder.cbSeleccionar.setVisibility(VISIBLE);
            holder.imgSeleccionar.setVisibility(VISIBLE);
        }

        holder.cbSeleccionar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent devolucion = new Intent();
                String nombrePlato = holder.titulo.getText().toString();


                devolucion.putExtra("nombrePlato",nombrePlato);
                devolucion.putExtra("precioPlato",precioPlatoSeleccionado);
                miActivity.setResult(RESULT_OK ,devolucion);

                miActivity.finish();


            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
