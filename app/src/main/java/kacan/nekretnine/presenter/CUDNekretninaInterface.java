package kacan.nekretnine.presenter;

import java.util.List;
import kacan.nekretnine.models.Nekretnina;

public interface CUDNekretninaInterface {

    interface Presenter{
        Nekretnina getNekretnina();

        void addNekretnina();
        void editNekretnina();
        void delNekretnina();
    }

    interface View{
        void dataReceived(List<Nekretnina> nekretnine);
    }

    interface Model{

        void addNekretnina(OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina);
        void editNekretnina(OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina);
        void delNekretnina(OnFinishDataReceived onFinishDataReceived, Nekretnina nekretnina);

        interface OnFinishDataReceived{
            void dataReceived(List<Nekretnina> nekretnine);
        }
    }
}
