package ar.com.lls.sendmeal.REPOSITORY;

import java.util.List;

import ar.com.lls.sendmeal.model.Plato;

public interface OnInsertarPlatoResult {
    void onResult(List<Plato> plato);
}
