package com.smile.cookbook.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smile.cookbook.R;
import com.smile.cookbook.config.Config;
import com.smile.cookbook.entity.FoodForTag;

public class FoodDetailActivity extends AppCompatActivity {
    private String mID;
    private ImageView mPhoto;
    private FoodForTag.ResultBean.ListBean mFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        mID=bundle.getString(Config.PARAMS_ID);
        mFood= (FoodForTag.ResultBean.ListBean) bundle.getSerializable(Config.KEY_FOOD);
        setContentView(R.layout.activity_food_detail);
        //给页面设置工具栏
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置工具栏标题
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mFood.getName());


        mPhoto= (ImageView) findViewById(R.id.food_photo);
        Glide.with(FoodDetailActivity.this)
                .load(mFood.getRecipe().getImg())
                .override(600, 600) // resizes the image to these dimensions (in pixel)
                .centerCrop()
                .into(mPhoto);

    }

}
