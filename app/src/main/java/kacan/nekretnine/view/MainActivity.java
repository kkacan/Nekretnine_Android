package kacan.nekretnine.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import kacan.nekretnine.R;
import kacan.nekretnine.models.Nekretnina;


public class MainActivity extends AppCompatActivity {

    private Nekretnina nekretnina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        read();
    }

    public Nekretnina getNekretnina() {
        return nekretnina;
    }

    public void setNekretnina(Nekretnina nekretnina) {
        this.nekretnina = nekretnina;
    }

    public void read() {
        setFragment(new ReadFragment());
    }

    public void cud() {
        setFragment(new CUDFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


}
