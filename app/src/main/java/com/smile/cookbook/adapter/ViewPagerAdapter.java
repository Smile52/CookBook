package com.smile.cookbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smile.cookbook.view.PagerItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**fragment适配器
 * Created by Smile on 2016/9/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment mContentFragment ;
    private List<PagerItem> mTab = new ArrayList<>();
    private Map<Integer, Fragment> mFragmentMap = new HashMap<>() ;

    public ViewPagerAdapter(FragmentManager fm, List<PagerItem> tab) {
        super(fm);
        mTab = tab;
    }

    @Override
    public Fragment getItem(int position) {
        mContentFragment = mFragmentMap.get(position);{
            if (mContentFragment == null){
                mContentFragment =  mTab.get(position).createFragment(position);
                mFragmentMap.put(position,mContentFragment) ;
            }
        }
        return mContentFragment ;


    }

    @Override
    public int getCount() {
        return mTab.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTab.get(position).getTitle();
    }
}
