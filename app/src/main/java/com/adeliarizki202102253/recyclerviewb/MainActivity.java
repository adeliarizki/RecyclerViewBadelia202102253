package com.adeliarizki202102253.recyclerviewb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.*;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView _recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView1);

        AsyncHttpClient ahc = new AsyncHttpClient();
        String url = "https://stmikpontianak.net/202102251/tampilMahasiswa.php";

        ahc.get(url, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson g = new Gson();
                List<MahasiswaModel> mahasiswaModelList = g.fromJson(new String(responseBody), new TypeToken<List<MahasiswaModel>>(){}.getType());

                RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
                _recyclerView1.setLayoutManager(lm);

                MahasiswaAdapter ma = new MahasiswaAdapter(mahasiswaModelList);
                _recyclerView1.setAdapter(ma);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}