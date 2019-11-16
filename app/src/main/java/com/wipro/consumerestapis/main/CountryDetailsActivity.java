package com.wipro.consumerestapis.main;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wipro.consumerestapis.R;
import com.wipro.consumerestapis.adapter.CountryDetailsListAdapter;
import com.wipro.consumerestapis.platform.CountryDetailAppPresenter;
import com.wipro.consumerestapis.platform.CountryInfoContract;
import com.wipro.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.ArrayList;
import java.util.List;

import static com.wipro.consumerestapis.platform.constants.LocalConstants.KEY_RECYCLER_STATE;

public class CountryDetailsActivity extends AppCompatActivity implements CountryInfoContract.view {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Bundle mBundleRecyclerViewState = new Bundle();
    Parcelable listState;
    Snackbar snackbar;
    CoordinatorLayout coordinatorLayout;
    List<CountryRow> countryRows;
    CountryDetailsListAdapter countryDetailsListAdapter;
    CountryDetailAppPresenter countryDetailAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isNetworkAvailable(CountryDetailsActivity.this)) {
            init();

            countryDetailAppPresenter = new CountryDetailAppPresenter(this);
            countryDetailAppPresenter.requestDataFromServer();
        } else {
            openSnackbar();
        }

    }

    private void openSnackbar() {
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        snackbar = Snackbar
                .make(coordinatorLayout, getString(R.string.NETWORK_ERROR), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.RETRY), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNetworkAvailable(CountryDetailsActivity.this)) {
                            init();

                            countryDetailAppPresenter = new CountryDetailAppPresenter(CountryDetailsActivity.this);
                            countryDetailAppPresenter.requestDataFromServer();
                        } else {
                            openSnackbar();
                        }
                    }
                });

        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // restore RecyclerView state
        if (listState != null) {
            (recyclerView.getLayoutManager()).onRestoreInstanceState(listState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            listState = outState.getParcelable(KEY_RECYCLER_STATE);
        }
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        countryRows = new ArrayList<>();

        countryDetailsListAdapter = new CountryDetailsListAdapter(CountryDetailsActivity.this, countryRows);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(countryDetailsListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                countryRows.clear();
                countryDetailAppPresenter.requestDataFromServer();
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setListViewData(List<CountryRow> countryRows, String title) {
        swipeRefreshLayout.setRefreshing(false);
        this.countryRows.addAll(countryRows);
        ((CountryDetailsActivity) this).getSupportActionBar().setTitle(title);
        countryDetailsListAdapter.notifyDataSetChanged();
    }


}
