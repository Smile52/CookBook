package com.smile.cookbook.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.smile.cookbook.R;
import com.smile.cookbook.adapter.FoodTypeAdapter;
import com.smile.cookbook.entity.Food;

import java.util.List;

/**
 * Created by Smile on 2016/9/19.
 */
public class PopMenu {
    private Context mContext;
    private RecyclerView mType;//类别
    private List<Food.ChildBean.CategoryInfoBean> mCategoryLists;
    private PopupWindow mPopupWindow;
    private FoodTypeAdapter mAdapter;

    public PopMenu(Context context,List<Food.ChildBean.CategoryInfoBean> been) {
        mContext = context;
        this.mCategoryLists=been;
        View view= LayoutInflater.from(mContext).inflate(R.layout.pop_menu,null);
        mType= (RecyclerView) view.findViewById(R.id.type_rcv);
        mAdapter=new FoodTypeAdapter(mContext,mCategoryLists);
        mType.setLayoutManager(new LinearLayoutManager(mContext));

        mType.setAdapter(mAdapter);
        mType.addItemDecoration(new MyDecoration(mContext, MyDecoration.VERTICAL_LIST));
        mPopupWindow = new PopupWindow(view, 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow = new PopupWindow(view,
                context.getResources().getDimensionPixelSize(R.dimen.pop_width),
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景（很神奇的）
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    //下拉式 弹出 pop菜单 parent 右下角
    public void showAsDropDown(View parent) {
        mPopupWindow.showAsDropDown(parent, 10,
                //保证尺寸是根据屏幕像素密度来的
                mContext.getResources().getDimensionPixelSize(R.dimen.pop_width));

        // 使其聚集
        mPopupWindow.setFocusable(true);
        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        //刷新状态
        mPopupWindow.update();
    }

}
