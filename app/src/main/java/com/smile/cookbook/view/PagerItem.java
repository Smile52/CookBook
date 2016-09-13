package com.smile.cookbook.view;

import android.support.v4.app.Fragment;

import com.smile.cookbook.ui.CookFragment;

/**
 *
 */
public class PagerItem {
    /*item 的信息*/
    private String mMsg ;
    /*item的 title*/
    private String mTitle ;

    public PagerItem(String mTitle,String mMsg) {
        this.mMsg = mMsg;
        this.mTitle = mTitle;
    }

    public Fragment createFragment(int position){
        return CookFragment.instance(mMsg) ;
    }

    public String getTitle() {
        return mTitle;
    }
}
