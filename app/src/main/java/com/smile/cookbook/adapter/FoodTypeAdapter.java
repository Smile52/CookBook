package com.smile.cookbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smile.cookbook.R;
import com.smile.cookbook.entity.Food;
import com.smile.cookbook.utils.XLog;

import java.util.List;

/**
 * Created by Smile on 2016/9/19.
 */
public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.TypeHold>{
    private Context mContext;
    private List<Food.ChildBean.CategoryInfoBean> mDatas;

    public FoodTypeAdapter(Context context, List<Food.ChildBean.CategoryInfoBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public TypeHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.food_type_item,parent,false);
        return new TypeHold(view);
    }

    @Override
    public void onBindViewHolder(TypeHold holder, int position) {
        XLog.e("dandy","ee"+mDatas.toString());
        holder.typeName.setText(mDatas.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

     class TypeHold extends RecyclerView.ViewHolder{
        private TextView typeName;
        public TypeHold(View itemView) {
            super(itemView);
            typeName= (TextView) itemView.findViewById(R.id.food_type_name);
        }
    }
}
