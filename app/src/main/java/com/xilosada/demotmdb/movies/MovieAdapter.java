package com.xilosada.demotmdb.movies;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xilosada.demotmdb.ImageLoader;
import com.xilosada.demotmdb.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xilosada on 27/11/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ImageLoader imageLoader;
    private List<Movie> movieList = new ArrayList<>();

    public MovieAdapter(Context context, ImageLoader imageLoader) {
        this.context = context;
        this.imageLoader = imageLoader;
    }

    public void addItems(List<Movie> movies) {
        this.movieList.addAll(movies);
        this.notifyDataSetChanged();
    }

    public void reset() {
        this.movieList.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.renderMovie(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView poster;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            poster.setBackgroundColor(context.getResources().getColor(getRandomColor()));
        }

        public void renderMovie(Movie movie) {
            textView.setText(movie.title);
            imageLoader.loadImage(movie.posterPath, poster);
        }

        @ColorRes
        private int getRandomColor() {
            switch (new Random().nextInt(4)) {
                case 0:
                    return R.color.colorAccent;
                case 1:
                    return R.color.colorAccent2;
                case 2:
                    return R.color.colorAccent3;
                case 3:
                    return R.color.colorAccent4;
            }
            throw new UnsupportedOperationException();
        }
    }
}
