package com.smile.cookbook;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.smile.cookbook.adapter.ViewPagerAdapter;
import com.smile.cookbook.control.TabController;
import com.smile.cookbook.databinding.MainActivityBinding;
import com.smile.cookbook.view.PagerItem;
import com.smile.cookbook.view.SlidingTabLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mPager;
    private SlidingTabLayout mTabLayout;
    /*每个 tab 的 item*/
    private List<PagerItem> mTab;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private MainActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mPager= (ViewPager) findViewById(R.id.cook_vp);
        mTabLayout= (SlidingTabLayout) findViewById(R.id.cook_tab);
        TabController control=new TabController(getApplicationContext());
        mTab=control.setTabData();
        mPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),mTab));
        mTabLayout.setViewPager(mPager);
        mToolbar=binding.toolbar;
        mToolbar.setTitle("CookBook");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout=binding.drawerLayout;
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }


    public void getStyle(){

    }

}
