package kacan.nekretnine.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(tableName = "nekretnina")
public class Nekretnina implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @TypeConverters(DateConverter.class)
    private Date datumIzmjene;
    private int tip;
    private String grad;
    private String adresa;
    private String opis;
    private String slika;

}
