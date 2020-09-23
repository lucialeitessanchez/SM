package ar.com.lls.sendmeal.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ar.com.lls.sendmeal.R;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {
    private List<Plato> items;

    public static class PlatoViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un plato
        public ImageView imagen;
        public TextView titulo;
        public TextView precio;
        public TextView descripcion;

        public PlatoViewHolder(View v) {
            super(v);
            imagen = (ImageView) v.findViewById(R.id.imagenPlato);
            titulo = (TextView) v.findViewById(R.id.tituloPlato);
            precio = (TextView) v.findViewById(R.id.precioPlato);
            descripcion = (TextView) v.findViewById(R.id.descripcionPlato);


        }
    }

    public PlatoAdapter(List<Plato> items){
        this.items = items;
    }

    @NonNull
    @Override
    public PlatoAdapter.PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_plato,parent,false);
        return new PlatoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoAdapter.PlatoViewHolder holder, int position) {
        //holder.imagen.setImageResource(items.get(position).getImagen()); por ahora no porque va la imagen para todos igual
        holder.titulo.setText(items.get(position).getTitulo());
        holder.precio.setText(items.get(position).getPrecio().toString()); //ojo hay que hacerlo string porque devuelve un entero
        holder.descripcion.setText(items.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
