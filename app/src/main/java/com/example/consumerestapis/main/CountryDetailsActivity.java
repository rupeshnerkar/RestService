package com.example.consumerestapis.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.consumerestapis.R;
import com.example.consumerestapis.adapter.CountryDetailsListAdapter;
import com.example.consumerestapis.platform.CountryDetailAppPresenter;
import com.example.consumerestapis.platform.CountryInfoContract;
import com.example.consumerestapis.platform.models.responseDTO.CountryRow;

import java.util.ArrayList;
import java.util.List;

public class CountryDetailsActivity extends AppCompatActivity implements CountryInfoContract.view {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView title, description;
    private ImageView image_to_show;

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
       // setListViewData();
//        Log.e("Mainnnn", "Number of detail received: " + countryRows.size());
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        countryRows = new ArrayList<>();

        countryDetailsListAdapter = new CountryDetailsListAdapter(CountryDetailsActivity.this, countryRows);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryDetailsListAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        image_to_show = (ImageView) findViewById(R.id.show_image);

    }

    @Override
    public void setListener() {

    }

    @Override
    public void setListViewData(List<CountryRow> countryRows) {
        this.countryRows.addAll(countryRows);
        Log.e("Hellllo", "Number of detail received: " + countryRows.size());
        countryDetailsListAdapter.notifyDataSetChanged();
    }


}
