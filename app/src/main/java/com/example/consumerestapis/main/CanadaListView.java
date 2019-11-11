package com.example.consumerestapis.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consumerestapis.R;

public class CanadaListView extends AppCompatActivity implements ListView{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView title,description;
    private ImageView image_to_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void setListViewData() {

    }
}
