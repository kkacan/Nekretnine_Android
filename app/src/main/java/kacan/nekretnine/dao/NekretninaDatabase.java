package kacan.nekretnine.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kacan.nekretnine.models.Nekretnina;

@Database(entities = {Nekretnina.class}, version = 1, exportSchema = false)
public abstract class NekretninaDatabase extends RoomDatabase {

    public abstract NekretninaDao nekretninaDao();

    private static NekretninaDatabase INSTANCE;

    public static NekretninaDatabase getDatabase(Context context) {
        if (INSTANCE == null) {

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NekretninaDatabase.class, "nekretnine")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
