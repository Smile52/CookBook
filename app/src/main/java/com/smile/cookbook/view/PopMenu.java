package com.smile.cookbook.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

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
        //设置popwindow的宽和高
        mPopupWindow = new PopupWindow(
                view, context.getResources().getDimensionPixelSize(R.dimen.pop_width),
                context.getResources().getDimensionPixelSize(R.dimen.pop_height));
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
        //二级菜类别的点击事件
        mAdapter.setmOnItemClickListener(new FoodTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int postion) {
                Toast.makeText(mContext,"点击了"+postion,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
