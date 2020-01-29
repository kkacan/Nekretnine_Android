package kacan.nekretnine.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import kacan.nekretnine.R;
import kacan.nekretnine.models.Nekretnina;
import kacan.nekretnine.presenter.CUDNekretninaInterface;
import kacan.nekretnine.presenter.CUDNekretninaPresenter;


public class CUDFragment extends Fragment implements CUDNekretninaInterface.View {

    private static final int TAKE_PHOTO = 1;
    private String path;

    @BindView(R.id.type)
    Spinner type;
    @BindView(R.id.town)
    EditText town;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.desc)
    EditText desc;
    @BindView(R.id.photo)
    ImageView photo;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.take_photo)
    Button takePhoto;
    @BindView(R.id.edit)
    Button edit;
    @BindView(R.id.del)
    Button del;

    private CUDNekretninaInterface.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud, container, false);
        ButterKnife.bind(this, view);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        back();
                        return true;
                    }
                }
                return false;
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePic();
            }
        });


        presenter = new CUDNekretninaPresenter(this, ((MainActivity) getActivity()).getNekretnina(), getContext());
        if (presenter.getNekretnina().getId() == 0) {
            del.setVisibility(View.GONE);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addNekretnina();
                }
            });
            return view;
        }

        setEditDelete();

        return view;
    }


    private void setEditDelete() {
        type.setSelection(presenter.getNekretnina().getTip());
        town.setText(presenter.getNekretnina().getGrad());
        address.setText(presenter.getNekretnina().getAdresa());
        desc.setText(presenter.getNekretnina().getOpis());
        date.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(presenter.getNekretnina().getDatumIzmjene()));
        Picasso.get()
                .load(presenter.getNekretnina().getSlika())
                .error(R.drawable.noimage)
                .into(photo);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editNekretnina();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delNekretnina();
            }
        });


    }

    private void editNekretnina() {
        presenter.getNekretnina().setGrad(town.getText().toString());
        presenter.getNekretnina().setSlika(presenter.getNekretnina().getSlika());
        presenter.getNekretnina().setAdresa(address.getText().toString());
        presenter.getNekretnina().setTip(type.getSelectedItemPosition());
        presenter.getNekretnina().setOpis(desc.getText().toString());
        presenter.getNekretnina().setDatumIzmjene(new Date());
        presenter.editNekretnina();
        back();
    }

    private void addNekretnina() {
        presenter.getNekretnina().setGrad(town.getText().toString());
        presenter.getNekretnina().setSlika("file://" + path);
        presenter.getNekretnina().setAdresa(address.getText().toString());
        presenter.getNekretnina().setTip(type.getSelectedItemPosition());
        presenter.getNekretnina().setOpis(desc.getText().toString());
        presenter.getNekretnina().setDatumIzmjene(new Date());
        presenter.addNekretnina();
        back();
    }

    private void delNekretnina() {
        presenter.getNekretnina().setGrad(town.getText().toString());
        presenter.getNekretnina().setSlika("file://" + path);
        presenter.getNekretnina().setAdresa(address.getText().toString());
        presenter.getNekretnina().setTip(type.getSelectedItemPosition());
        presenter.getNekretnina().setOpis(desc.getText().toString());
        presenter.delNekretnina();
        back();
    }

    private void back() {
        ((MainActivity) getActivity()).read();
    }

    private void takePic() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;

        }
        File photo;
        try {
            photo = createPhoto();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        if (photo == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        Uri photoURI = FileProvider.getUriForFile(getActivity(), "kacan.nekretnine.provider", photo);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivityForResult(takePictureIntent, TAKE_PHOTO);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PHOTO && resultCode == Activity.RESULT_OK) {

            presenter.getNekretnina().setSlika("file://" + path);
            presenter.editNekretnina();
            Picasso.get()
                    .load(presenter.getNekretnina().getSlika())
                    .error(R.drawable.noimage)
                    .into(photo);

        }
    }

    private File createPhoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = "NEKRETNINA_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(name, ".jpg", storageDir);
        path = image.getAbsolutePath();
        return image;
    }

    @Override
    public void dataReceived(List<Nekretnina> nekretnine) {

    }


}