package ar.com.lls.sendmeal.REPOSITORY;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ar.com.lls.sendmeal.DAO.PedidoDao;
import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Pedido;
import ar.com.lls.sendmeal.model.Plato;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Room;

//En este archivo se crea una instancia de AppDatabase utilizando Room

@Database(entities = {Plato.class, Pedido.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlatoDao platoDao();
    public abstract PedidoDao pedidoDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 1;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getInstance(final Context context){
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class,"db_send_meta")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
