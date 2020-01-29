package kacan.nekretnine.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import kacan.nekretnine.models.Nekretnina;

@Dao
public interface NekretninaDao {

    @Query("select * from nekretnina order by datumIzmjene DESC")
    List<Nekretnina> getNekretnine();

    @Insert
    void insertNekretnina(Nekretnina nekretnina);

    @Update
    void updateNekretnina(Nekretnina nekretnina);

    @Delete
    void deleteNekretnina(Nekretnina nekretnina);
}
