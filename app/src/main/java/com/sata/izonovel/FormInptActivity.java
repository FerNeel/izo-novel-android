package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.sata.izonovel.Model.InsertNovelMOdel;
import com.sata.izonovel.Retrofit.APIService;

import java.sql.SQLInput;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInptActivity extends AppCompatActivity {
    String[] genre = {"Action", "Romance", "Fantasi", "Horor", "Comedy", "Sci-fi"};

    TextInputEditText Judul,Pengarang,Penerbit,TahunTerbit,Genre,Sinopsis;
    Button Cancel, Submit;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inpt);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, genre);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.filled_exposed_dropdown);
        actv.setThreshold(1);
        actv.setAdapter(adapter);


        Judul = findViewById(R.id.in_judul);
        Pengarang = findViewById(R.id.in_pengarang);
        Penerbit = findViewById(R.id.in_penerbit);
        TahunTerbit = findViewById(R.id.in_tahun_terbit);
        Genre = findViewById(R.id.in_genre);
        Sinopsis = findViewById(R.id.in_sinopsis);
        Submit = findViewById(R.id.btn_submit);
        Cancel = findViewById(R.id.btn_cancel);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onsavenovel();
            }
        });
    }
        private void onsavenovel() {
            InsertNovelMOdel insertNovelMOdel = new InsertNovelMOdel();
            insertNovelMOdel.setCollection("novel");
            insertNovelMOdel.setDataSource("Clister0");
            insertNovelMOdel.setDatabase("izonovel");
            InsertNovelMOdel.Document document = new InsertNovelMOdel.Document();
            document.setJudul(Judul.getText().toString());
            document.setPengarang(Pengarang.getText().toString());
            document.setPenerbit(Penerbit.getText().toString());
            document.setTahunTerbit(TahunTerbit.getText().toString());
            document.setGenre(Genre.getText().toString());
            document.setSinopsis(Sinopsis.getText().toString());
            insertNovelMOdel.setDocument(document);

            progressDialog.setTitle("Info");
            progressDialog.setMessage("Sedang Mengirim Data...");
            progressDialog.show();

            APIService.endpoint().insertNovel(insertNovelMOdel).enqueue(new Callback<InsertNovelMOdel>() {
                @Override
                public void onResponse(Call<InsertNovelMOdel> call, Response<InsertNovelMOdel> response) {
                    progressDialog.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormInptActivity.this);
                    builder.setTitle("Info");
                    builder.setMessage("Data Berhasil Disimpan");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Judul.setText("");
                            Pengarang.setText("");
                            Penerbit.setText("");
                            TahunTerbit.setText("");
                            Genre.setText("");
                            Sinopsis.setText("");
                        }
                    });
                    builder.show();
                }

                @Override
                public void onFailure(Call<InsertNovelMOdel> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });


    }
}