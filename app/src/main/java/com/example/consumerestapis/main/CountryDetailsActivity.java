package com.example.consumerestapis.main;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.consumerestapis.R;
import com.example.consumerestapis.adapter.CountryDetailsListAdapter;
import com.example.consumerestapis.platform.CountryDetailAppPresenter;
import com.example.consumerestapis.platform.CountryInfoContract;
import com.example.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.ArrayList;
import java.util.List;

import static com.example.consumerestapis.platform.constants.LocalConstants.KEY_RECYCLER_STATE;

public class CountryDetailsActivity extends AppCompatActivity implements CountryInfoContract.view {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Bundle mBundleRecyclerViewState = new Bundle();
    Parcelable listState;

    List<CountryRow> countryRows;
    CountryDetailsListAdapter countryDetailsListAdapter;
    CountryDetailAppPresenter countryDetailAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        countryDetailAppPresenter = new CountryDetailAppPresenter(this);
        countryDetailAppPresenter.requestDataFromServer();

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
       if(outState != null){
           listState = outState.getParcelable(KEY_RECYCLER_STATE);
       }
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
        Log.e("Hellllo", "Number of detail received: " + countryRows.size());
        countryDetailsListAdapter.notifyDataSetChanged();
    }


}
