package com.smile.cookbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smile.cookbook.R;
import com.smile.cookbook.entity.FoodForTag;

import java.util.List;

/**
 * Created by Smile on 2016/9/18.
 */
    public class FoodAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<FoodForTag.ResultBean.ListBean> mFoods;
    private LayoutInflater mInflater;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    public FoodAdapter(Context context, List<FoodForTag.ResultBean.ListBean> foods) {
        mContext = context;
        mFoods = foods;
        mInflater=LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==TYPE_ITEM){
            view=  mInflater.inflate(R.layout.food_item, parent, false);
            return new FoodViewHold(view);
        }else if(viewType==TYPE_FOOTER){
            view=mInflater.inflate(R.layout.item_foot,parent,false);
            return  new FootViewHold(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FoodViewHold){

            FoodForTag.ResultBean.ListBean bean=mFoods.get(position);
            ((FoodViewHold) holder).mName.setText(bean.getName());

           // XLog.e("dandy ","name "+bean.getName()+"  img "+bean.getRecipe().getImg());
            Glide.with(mContext)
                    .load(bean.getRecipe().getImg())
                    .override(600, 600) // resizes the image to these dimensions (in pixel)
                    .centerCrop()
                    .error(R.mipmap.ic_launcher)
                    .into(((FoodViewHold) holder).mImg);
            if (onItemClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });

            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return mFoods.size() == 0 ? 0 : mFoods.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1  == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 判断当前postion是不是底部view
     * @param postion
     * @return
     */
    public boolean isFooter(int postion){
        return postion==(mFoods.size());
    }

    class FoodViewHold extends RecyclerView.ViewHolder{
        private ImageView mImg;
        private TextView mName;
        public FoodViewHold(View itemView) {
            super(itemView);
                mImg= (ImageView) itemView.findViewById(R.id.food_img);
                mName= (TextView) itemView.findViewById(R.id.food_name);
        }
    }
    class FootViewHold extends RecyclerView.ViewHolder{

        public FootViewHold(View itemView) {
            super(itemView);
        }
    }
}
