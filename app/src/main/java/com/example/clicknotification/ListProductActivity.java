package com.example.clicknotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.clicknotification.databinding.ActivityListProductBinding;

public class ListProductActivity extends AppCompatActivity {
    ActivityListProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_product);

        getSupportActionBar().setTitle("ListProductActivity");


        binding.btnGoToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProductActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}