package ar.com.lls.sendmeal.REPOSITORY;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Plato;

public class BuscarPlatoById {

    private PlatoDao platoDao;
    private AppRepository appRepository;


    public BuscarPlatoById(PlatoDao platoDao, AppRepository appRepository) {
        this.platoDao = platoDao;
        this.appRepository = appRepository;
    }



    public void execute(String id) {
        this.platoDao.buscar(id);
    }
}
