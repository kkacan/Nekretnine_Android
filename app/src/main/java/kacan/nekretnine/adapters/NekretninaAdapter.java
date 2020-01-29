package kacan.nekretnine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.List;
import kacan.nekretnine.R;
import kacan.nekretnine.models.Nekretnina;


public class NekretninaAdapter extends ArrayAdapter<Nekretnina> {

    private List<Nekretnina> nekretnine;
    private NekretninaClickListener nekretninaClickListener;
    private int resource;
    private Context context;


    public NekretninaAdapter(@NonNull Context context, int resource, NekretninaClickListener nekretninaClickListener) {
        super(context, resource);

        this.nekretninaClickListener = nekretninaClickListener;
        this.resource = resource;
        this.context = context;
    }

    private static class ViewHolder {

        private TextView id;
        private TextView tip;
        private TextView grad;
        private TextView adresa;
        private TextView datum;
        private ImageView slika;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Nekretnina nekretnina;

        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.resource, null);
            viewHolder.id = view.findViewById(R.id.id);
            viewHolder.tip = view.findViewById(R.id.tip);
            viewHolder.grad = view.findViewById(R.id.grad);
            viewHolder.adresa = view.findViewById(R.id.adresa);
            viewHolder.datum = view.findViewById(R.id.datum);
            viewHolder.slika = view.findViewById(R.id.slika);
        } else {
            viewHolder = (ViewHolder) view.getTag();

        }

        nekretnina = getItem(position);

        if (nekretnina != null) {
            viewHolder.id.setText(String.valueOf(nekretnina.getId()));
            viewHolder.tip.setText(context.getResources().getStringArray(R.array.type_array)[nekretnina.getTip()]);
            viewHolder.grad.setText(nekretnina.getGrad());
            viewHolder.adresa.setText(nekretnina.getAdresa());
            viewHolder.datum.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                    .format(nekretnina.getDatumIzmjene()));

            Picasso.get()
                    .load(nekretnina.getSlika())
                    .fit()
                    .centerCrop()
                    .error(R.drawable.noimage)
                    .into(viewHolder.slika);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nekretninaClickListener.onItemClick(nekretnina);
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return nekretnine == null ? 0 : nekretnine.size();
    }

    @Nullable
    @Override
    public Nekretnina getItem(int position) {
        return nekretnine.get(position);
    }

    public void updateAdapter() {
        notifyDataSetChanged();
    }

    public void setData(List<Nekretnina> itemList) {
        this.nekretnine = itemList;
    }
}
