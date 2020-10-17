package ar.com.lls.sendmeal.REPOSITORY;

import java.util.List;

import ar.com.lls.sendmeal.model.Plato;

interface OnPlatoResultCallback {
    void onResult(List<Plato> plato);
}
