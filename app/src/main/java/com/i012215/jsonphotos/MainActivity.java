package com.i012215.jsonphotos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i012215.jsonphotos.Adapters.AdapterPhoto;
import com.i012215.jsonphotos.Connection.HttpManager;
import com.i012215.jsonphotos.Models.Photo;
import com.i012215.jsonphotos.Parser.JsonPhoto;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
    RecyclerView recyclerView;
    List<Photo> photoList;
    AdapterPhoto adapterPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.id_pb_item);
        button = (Button) findViewById(R.id.id_btn_listfotos);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public Boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    public void loadData(View view) {
        if (isOnline()) {
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/photos");
        } else {
            Toast.makeText(this, "Sin Conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData() {
        adapterPhoto = new AdapterPhoto(photoList, getApplicationContext());
        recyclerView.setAdapter(adapterPhoto);
    }

    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
//             setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                //countryList = JsonCountry.getData(s);
                photoList = JsonPhoto.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);

        }
    }
}