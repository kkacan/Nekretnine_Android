package kacan.nekretnine.presenter;

import java.util.List;

import kacan.nekretnine.models.Nekretnina;


public interface ReadNekretninaInterface {

    interface Presenter{
        void getNekretnine();
    }

    interface View{
        void dataReceived(List<Nekretnina> nekretnine);
    }

    interface Model{
        void getNekretnine(OnFinishDataReceived onFinishDataReceived);

        interface OnFinishDataReceived{
            void dataReceived(List<Nekretnina> nekretnine);
        }
    }
}
