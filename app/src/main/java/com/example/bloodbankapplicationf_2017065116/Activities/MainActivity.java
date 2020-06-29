package com.example.bloodbankapplicationf_2017065116.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbankapplicationf_2017065116.Adapter.RequestAdapter;
import com.example.bloodbankapplicationf_2017065116.DataModels.RequestDataModel;
import com.example.bloodbankapplicationf_2017065116.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RequestDataModel> requestDataModels;
    private RequestAdapter requestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        TextView make_request_button = findViewById(R.id.make_request_button);
        make_request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MakeRequestActivity.class));
            }
        });
        requestDataModels = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
                }
                    return false;
                }


        });


        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdapter = new RequestAdapter(requestDataModels, this);
        recyclerView.setAdapter(requestAdapter);
        populateHomePage();


    }

    private void populateHomePage(){

        RequestDataModel requestDataModel = new RequestDataModel("Ye data MainActivity ka populateHomePage ka method RequestDataModel ma sa araha ha","https://i.pinimg.com/originals/e2/ed/64/e2ed6474391685c3874b32d30ae1e09f.jpg");
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);

        requestAdapter.notifyDataSetChanged();

    }
}

