package kacan.nekretnine.presenter;

import android.content.Context;
import java.util.List;
import kacan.nekretnine.dao.DAONekretnina;
import kacan.nekretnine.models.Nekretnina;

public class CUDNekretninaPresenter implements CUDNekretninaInterface.Presenter, CUDNekretninaInterface.Model.OnFinishDataReceived {

    private CUDNekretninaInterface.View view;
    private CUDNekretninaInterface.Model model;
    private Nekretnina nekretnina;


    public CUDNekretninaPresenter(CUDNekretninaInterface.View view, Nekretnina nekretnina, Context context){

        this.view = view;
        this.nekretnina = nekretnina;
        model = new DAONekretnina(context);
    }

    @Override
    public Nekretnina getNekretnina() {
        return nekretnina;
    }

    public void setNekretnina(Nekretnina nekretnina) {
        this.nekretnina = nekretnina;
    }

    @Override
    public void addNekretnina() {
        model.addNekretnina(this, nekretnina);
    }

    @Override
    public void editNekretnina() {
        model.editNekretnina(this, nekretnina);
    }

    @Override
    public void delNekretnina() {
        model.delNekretnina(this, nekretnina);
    }

    @Override
    public void dataReceived(List<Nekretnina> nekretnine) {
        view.dataReceived(nekretnine);
    }
}
