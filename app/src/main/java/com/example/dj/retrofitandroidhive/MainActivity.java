package com.example.dj.retrofitandroidhive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.dj.retrofitandroidhive.adapter.MoviesAdapter;
import com.example.dj.retrofitandroidhive.model.MovieResponse;
import com.example.dj.retrofitandroidhive.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;


    private final static String API_KEY = "acd70cbc721e1b864b57977a1158a145";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()){
            Toast.makeText(this, "Please obtain API key", Toast.LENGTH_SHORT).show();
            return;
        }

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Result> results = response.body().getResults();
                Log.d(TAG, "onResponse: "+results.size());
                recyclerView.setAdapter(new MoviesAdapter(results,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.toString());

            }
        });
    }
}
