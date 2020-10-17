package ar.com.lls.sendmeal.REPOSITORY;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ar.com.lls.sendmeal.DAO.PlatoDao;
import ar.com.lls.sendmeal.model.Plato;

@Database(entities = {Plato.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlatoDao platoDao();
    /* .... */
    static AppDatabase getInstance(final Context context) {
        /* .... */
        return INSTANCE;
    }
}
