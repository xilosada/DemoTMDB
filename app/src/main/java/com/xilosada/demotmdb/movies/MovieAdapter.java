package com.xilosada.demotmdb.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xilosada.demotmdb.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilosada on 27/11/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public static final String TMDB_IMAGE_BUCKET = "https://image.tmdb.org/t/p/w500";
    private List<Movie> movieList = new ArrayList<>();
    private Picasso picasso;

    public MovieAdapter(Picasso picasso) {
        this.picasso = picasso;
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
            this.textView = (TextView) itemView.findViewById(R.id.title);
            this.poster = (ImageView) itemView.findViewById(R.id.poster);
        }

        public void renderMovie(Movie movie) {
            textView.setText(movie.title);
            picasso.load(TMDB_IMAGE_BUCKET +movie.posterPath)
                    .resize(500, 400) // resizes the image to these dimensions (in pixel)
                    .centerCrop()
                    .into(poster);
        }
    }
}
