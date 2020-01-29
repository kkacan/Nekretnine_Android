package kacan.nekretnine.dao;

import android.content.Context;
import kacan.nekretnine.models.Nekretnina;
import kacan.nekretnine.presenter.CUDNekretninaInterface;
import kacan.nekretnine.presenter.ReadNekretninaInterface;


public class DAONekretnina implements CUDNekretninaInterface.Model, ReadNekretninaInterface.Model {

    private NekretninaDao nekretninaDao;

    public DAONekretnina(Context context) {

        nekretninaDao = NekretninaDatabase.getDatabase(context).nekretninaDao();
    }

    @Override
    public void addNekretnina(CUDNekretninaInterface.Model.OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina) {
        nekretninaDao.insertNekretnina(nekretnina);
        onFinishDataReceived.dataReceived(nekretninaDao.getNekretnine());
    }

    @Override
    public void editNekretnina(CUDNekretninaInterface.Model.OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina) {
        nekretninaDao.updateNekretnina(nekretnina);
        onFinishDataReceived.dataReceived(nekretninaDao.getNekretnine());
    }

    @Override
    public void delNekretnina(CUDNekretninaInterface.Model.OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina) {
        nekretninaDao.deleteNekretnina(nekretnina);
        onFinishDataReceived.dataReceived(nekretninaDao.getNekretnine());
    }

    @Override
    public void getNekretnine(ReadNekretninaInterface.Model.OnFinishDataReceived onFinishDataReceived) {

        onFinishDataReceived.dataReceived(nekretninaDao.getNekretnine());
    }
}
