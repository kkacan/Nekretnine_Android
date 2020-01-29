package kacan.nekretnine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kacan.nekretnine.R;
import kacan.nekretnine.adapters.NekretninaAdapter;
import kacan.nekretnine.adapters.NekretninaClickListener;
import kacan.nekretnine.models.Nekretnina;
import kacan.nekretnine.presenter.ReadNekretninaInterface;
import kacan.nekretnine.presenter.ReadNekretninaPresenter;

public class ReadFragment extends Fragment implements ReadNekretninaInterface.View {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lista)
    ListView listView;

    private ReadNekretninaInterface.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container, false);
        ButterKnife.bind(this, view);

        definirajListu();
        definirajSwipe();

        presenter = new ReadNekretninaPresenter(this, getContext());
        presenter.getNekretnine();

        return view;
    }


    private void definirajSwipe() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getNekretnine();
            }
        });

    }

    private void definirajListu() {

        NekretninaAdapter nekretninaAdapter = new NekretninaAdapter
                (getActivity(), R.layout.card_view, new NekretninaClickListener() {
            @Override
            public void onItemClick(Nekretnina nekretnina) {
                ((MainActivity) getActivity()).setNekretnina(nekretnina);
                ((MainActivity) getActivity()).cud();
            }
        });
        listView.setAdapter(nekretninaAdapter);
    }

    @OnClick(R.id.fab)
    public void addNekretnina() {
        ((MainActivity) getActivity()).setNekretnina(new Nekretnina());
        ((MainActivity) getActivity()).cud();
    }


    @Override
    public void dataReceived(List<Nekretnina> nekretnine) {
        ((NekretninaAdapter) listView.getAdapter()).setData(nekretnine);
        ((NekretninaAdapter) listView.getAdapter()).updateAdapter();
        swipeRefreshLayout.setRefreshing(false);
    }
}