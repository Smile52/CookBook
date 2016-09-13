package com.smile.cookbook.ui;

import com.smile.cookbook.view.PagerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smile on 2016/9/10.
 */
public class DefultData {
    public static List<PagerItem> getDefultTab(){
         List<PagerItem> mTab = new ArrayList<>() ;
        mTab.add(new PagerItem("周四06-12","今天"));
        mTab.add(new PagerItem("周五06-13","周五06-13"));
        mTab.add(new PagerItem("周六06-14","周六06-14"));
        mTab.add(new PagerItem("周日06-15","周日06-15"));
        mTab.add(new PagerItem("周一06-16","今天"));
        mTab.add(new PagerItem("周二06-17","今天"));
        mTab.add(new PagerItem("周三06-18","今天"));
        mTab.add(new PagerItem("周四06-19","今天"));
        mTab.add(new PagerItem("周五06-20","今天"));
        mTab.add(new PagerItem("周六06-21","今天"));
        mTab.add(new PagerItem("周日06-22","今天"));
        mTab.add(new PagerItem("周一06-23","今天"));
        mTab.add(new PagerItem("周二06-24","今天"));
        return mTab;
    }
}
