package ar.com.lls.sendmeal.REPOSITORY;

import android.widget.Toast;

import java.util.List;

import ar.com.lls.sendmeal.PlatoActivity;
import ar.com.lls.sendmeal.model.Plato;

public interface OnInsertarPlatoResult {
    //void onResult(List<Plato> plato);
    void onResult(Long idPlato);
}
