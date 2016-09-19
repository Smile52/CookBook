package com.smile.cookbook.view;

import android.support.v4.app.Fragment;

import com.smile.cookbook.entity.Food;
import com.smile.cookbook.ui.CookFragment;

import java.util.List;

/**
 *
 */
public class PagerItem {
    /*item 的信息*/
    private String mMsg ;
    /*item的 title*/
    private String mTitle ;
    private List<Food.ChildBean> mBeen;

    public PagerItem(String mTitle, List<Food.ChildBean> been) {
        this.mBeen = been;
        this.mTitle = mTitle;
    }

    public Fragment createFragment(int position){
        return CookFragment.instance(mBeen) ;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<Food.ChildBean> getBeen() {
        return mBeen;
    }


}
