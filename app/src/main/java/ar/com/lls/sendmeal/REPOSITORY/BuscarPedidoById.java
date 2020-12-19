package ar.com.lls.sendmeal.REPOSITORY;

import ar.com.lls.sendmeal.DAO.PedidoDao;

public class BuscarPedidoById {
    private PedidoDao pedidoDao;
    private AppRepository appRepository;


    public BuscarPedidoById(PedidoDao pedidoDao, AppRepository appRepository) {
        this.pedidoDao = pedidoDao;
        this.appRepository = appRepository;
    }

    public void execute(String id) {
        this.pedidoDao.buscar(id);
    }
}
