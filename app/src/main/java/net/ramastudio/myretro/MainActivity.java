package net.ramastudio.myretro;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.ramastudio.myretro.api.Retroserver;
import net.ramastudio.myretro.model.ApiRequestBiodata;
import net.ramastudio.myretro.model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    inisialisasi btn edt
    EditText nama, usia, domisili;
    Button btnSimpan;
//    biar prosesnya terlihat
    ProgressDialog pg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        membuat object
        nama = (EditText) findViewById(R.id.edtNama);
        usia = (EditText) findViewById(R.id.edtUsia);
        domisili = (EditText) findViewById(R.id.edtDomisili);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        pg = new ProgressDialog(this);

//        1. kita buat object api
        final ApiRequestBiodata api = Retroserver.getClient().create(ApiRequestBiodata.class);
//        selanjutnya bisa melakukan call api pada btnOnClickListener

//    btn onclick
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pg.setMessage("Kirim Data ...");
                pg.setCancelable(false);
                pg.show();

                String string_nama = nama.getText().toString();
                String string_usia = usia.getText().toString();
                String string_domisili = domisili.getText().toString();

//                disini apinya yang kita kirim adalah stingnya
               Call<ResponseModel> sendbio = api.sendBiodata(string_nama, string_usia, string_domisili);
               sendbio.enqueue(new Callback<ResponseModel>() {
                   @Override
                   public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                       sampai sini pgnya di hide
                       pg.hide();

                       Log.d("Retro", "response: " + response.body().toString());
                       String kode = response.body().getKode();
                       if (kode.equals("1")){
                           Toast.makeText(MainActivity.this, "Data Berhasil Disimpan",
                                   Toast.LENGTH_LONG).show();
                       }else {
                           Toast.makeText(MainActivity.this, "Gagal",
                                   Toast.LENGTH_LONG).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<ResponseModel> call, Throwable t) {
//                       disini pg juga di hide
                       pg.hide();
                        Log.d("Retro", "Failure" + "Gagal Mengirim request");
                   }
               });

            }
        });

    }
}
