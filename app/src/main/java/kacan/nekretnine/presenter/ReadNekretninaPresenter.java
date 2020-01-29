package kacan.nekretnine.presenter;

import android.content.Context;
import java.util.List;
import kacan.nekretnine.dao.DAONekretnina;
import kacan.nekretnine.models.Nekretnina;

public class ReadNekretninaPresenter implements ReadNekretninaInterface.Presenter, ReadNekretninaInterface.Model.OnFinishDataReceived{

    private ReadNekretninaInterface.View view;
    private ReadNekretninaInterface.Model model;

    public ReadNekretninaPresenter(ReadNekretninaInterface.View view, Context context){

        this.view = view;
        model = new DAONekretnina(context);
    }

    @Override
    public void getNekretnine() {
        model.getNekretnine(this);
    }

    @Override
    public void dataReceived(List<Nekretnina> nekretnine) {
        view.dataReceived(nekretnine);
    }
}
