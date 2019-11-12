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
import com.example.consumerestapis.platform.CanadaDetailPresenter;
import com.example.consumerestapis.platform.CanadaInfoContract;
import com.example.consumerestapis.platform.models.responseDTO.CanadaRows;

import java.util.ArrayList;
import java.util.List;

public class CanadaListView extends AppCompatActivity implements CanadaInfoContract.view {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView title, description;
    private ImageView image_to_show;

    List<CanadaRows> canadaRows;
    ListAdapter listAdapter;
    CanadaDetailPresenter canadaDetailPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        canadaDetailPresenter = new CanadaDetailPresenter(this);
        canadaDetailPresenter.requestDataFromServer();
       // setListViewData();
//        Log.e("Mainnnn", "Number of detail received: " + canadaRows.size());
    }

    @Override
    public void init() {
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        canadaRows = new ArrayList<>();

        listAdapter = new ListAdapter(CanadaListView.this, canadaRows);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        image_to_show = (ImageView) findViewById(R.id.show_image);

    }

    @Override
    public void setListener() {

    }

    @Override
    public void setListViewData(List<CanadaRows> canadaRows) {
        canadaRows.addAll(canadaRows);
        Log.e("Hellllo", "Number of detail received: " + canadaRows.size());
        listAdapter.notifyDataSetChanged();
    }


}
