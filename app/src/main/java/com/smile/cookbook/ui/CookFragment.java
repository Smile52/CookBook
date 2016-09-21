package com.smile.cookbook.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smile.cookbook.R;
import com.smile.cookbook.adapter.FoodAdapter;
import com.smile.cookbook.config.Config;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.entity.FoodForTag;
import com.smile.cookbook.imp.ImpRequest;
import com.smile.cookbook.utils.GridSpacingItemDecoration;
import com.smile.cookbook.utils.RetrofitUtil;
import com.smile.cookbook.utils.XLog;
import com.smile.cookbook.view.PopMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**类别fragment
 * Created by Smile on 2016/9/9.
 */
public class CookFragment extends Fragment {
    private TextView mTextView;
    private RecyclerView mFoodViews;
    private SwipeRefreshLayout mRefreshLayout;
    private FoodAdapter mAdapter;
    private FloatingActionButton mTop;
    private List<FoodForTag.ResultBean.ListBean> mfoods=new ArrayList<>();
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
        Map<String,String> params=addParams(msg.get(0).getCtgId(),"1","20");
        RetrofitUtil.getInstance().setBaseUrl().setTimeout(10000).addFactory().build().getFoodForTag(params, new ImpRequest() {
            @Override
            public void onSuccess(Object o) {
                FoodForTag forTag= (FoodForTag) o;
                mfoods=forTag.getResult().getList();
                XLog.e("dandy","size "+mfoods.size());
                mAdapter=new FoodAdapter(getContext(),mfoods);
                int spanCount = 2;//跟布局里面的spanCount属性是一致的
                int spacing = 5;//每一个矩形的间距
                boolean includeEdge = true;//如果设置成false那边缘地带就没有间距
                mFoodViews.setLayoutManager(new GridLayoutManager(getContext(),2));
                mFoodViews.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing,includeEdge));
                mFoodViews.setAdapter(mAdapter);
                ItemOnClickListener();
            }

            @Override
            public void onFailure() {

            }
        });


    }

    private void  ItemOnClickListener(){
        mAdapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

               startNewAcitivity(view,mfoods.get(position).getRecipe().getImg());
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void startNewAcitivity(View view,String id) {
        Intent intent = new Intent(getActivity(),FoodDetailActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString(Config.PARAMS_ID,id);
        intent.putExtras(bundle);
        ActivityOptionsCompat  options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),view.findViewById(R.id.food_img),"photos");
        getContext().startActivity( intent, options.toBundle());

    }

    /**
     * 设置请求参数
     * @param cid
     * @param page 页码
     * @param size 返回的数量
     */
    public Map addParams(String cid,String page,String size){
        Map<String,String> map=new HashMap<>();
        map.put(Config.PARAMS_KEY,Config.KEY);
        map.put(Config.PARAMS_CID,cid);
        map.put(Config.PARAMS_PAGE,page);
        map.put(Config.PARAMS_SIZE,size);
        return  map;

    }

}
