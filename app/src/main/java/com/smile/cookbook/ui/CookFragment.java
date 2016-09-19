package com.smile.cookbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smile.cookbook.R;
import com.smile.cookbook.adapter.FoodAdapter;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.utils.XLog;
import com.smile.cookbook.view.PopMenu;

import java.io.Serializable;
import java.util.List;

/**类别fragment
 * Created by Smile on 2016/9/9.
 */
public class CookFragment extends Fragment {
    private TextView mTextView;
    private RecyclerView mFoodViews;
    private SwipeRefreshLayout mRefreshLayout;
    private FoodAdapter mAdapter;
    private FloatingActionButton mTop;
    public static Fragment instance(List<Food.ChildBean> msg){
        CookFragment fragment=new CookFragment();
        Bundle bundle = new Bundle() ;
        bundle.putSerializable("msg", (Serializable) msg);
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_content,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // mTextView= (TextView) view.findViewById(R.id.cook_tv);
        mFoodViews= (RecyclerView) view.findViewById(R.id.foods_rcv);
        mRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mTop= (FloatingActionButton) view.findViewById(R.id.top_acb);
        Bundle bundle = getArguments() ;
        final List<Food.ChildBean.CategoryInfoBean> msg = (List<Food.ChildBean.CategoryInfoBean>) bundle.getSerializable("msg");
        XLog.e("dandy"," msg  "+msg.size());

        mTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               PopMenu menu=new PopMenu(getContext(),msg);
                menu.showAsDropDown(view);
            }
        });


    }

    public void  addParams(String id){

    }

}
