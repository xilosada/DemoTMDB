package com.xilosada.demotmdb.movies;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.xilosada.demotmdb.DeviceInfo;
import com.xilosada.demotmdb.ImageLoader;
import com.xilosada.demotmdb.R;

import java.util.List;
import views.EndlessRecyclerViewScrollListener;

public class PopularMoviesActivity extends AppCompatActivity implements MoviesContract.MovieListView {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MovieAdapter mAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private MoviesContract.MovieListPresenter presenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);

        //TODO DI Dagger
        DeviceInfo deviceInfo = new DeviceInfo();
        mAdapter = new MovieAdapter(this, new ImageLoader(this, deviceInfo));
        presenter = new MovieListPresenterImpl(this, new MoviesProviderImpl(deviceInfo));

        configViews();
    }

    private void configViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.setQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                presenter.setQuery(query);
                return true;
            }
        });

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.i("QUERY", "page number "+ page);
                Snackbar.make(view, "Loading "+ page, Snackbar.LENGTH_LONG).show();
                presenter.requestPage(page);
            }
        };
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
