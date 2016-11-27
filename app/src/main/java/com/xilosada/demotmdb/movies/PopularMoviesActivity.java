package com.xilosada.demotmdb.movies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.xilosada.demotmdb.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import views.EndlessRecyclerViewScrollListener;
import views.RxEditText;

public class PopularMoviesActivity extends AppCompatActivity implements MoviesContract.MovieListView {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MovieAdapter mAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private MoviesContract.MovieListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RxEditText editText = (RxEditText) findViewById(R.id.myEditText);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            editText.setVisibility(View.VISIBLE);
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Picasso picasso = Picasso.with(this);
        // specify an adapter (see also next example)
        mAdapter = new MovieAdapter(picasso);
        mRecyclerView.setAdapter(mAdapter);
        presenter = new MovieListPresenterImpl(this, new MoviesProviderImpl());

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.e("QUERY PAGE", "number "+ page);
                Snackbar.make(view, "QUERY PAGE number "+ page, Snackbar.LENGTH_LONG).show();
                presenter.requestPage(page);
            }
        };

        editText.getTextChanges()
                .toObservable()
                .throttleLast(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> presenter.setQuery(s))
                .subscribe();

        mRecyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    public void renderMovies(List<Movie> movies) {
        mAdapter.addItems(movies);
    }

    @Override
    public void reset() {
        mAdapter.reset();
    }
}
