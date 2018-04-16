package com.example.dj.retrofitandroidhive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dj.retrofitandroidhive.R;
import com.example.dj.retrofitandroidhive.model.Result;

import java.util.List;

/**
 * Created by DJ on 01/04/2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private List<Result> results;
    private Context context;


    public MoviesAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;

    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.movieTitle.setText(results.get(position).getTitle());
        holder.movieDescription.setText(results.get(position).getOverview());
        holder.rating.setText(results.get(position).getVoteAverage().toString());
        holder.data.setText(results.get(position).getReleaseDate());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }
}
