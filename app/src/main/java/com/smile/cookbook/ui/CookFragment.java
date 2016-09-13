package com.smile.cookbook.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smile.cookbook.R;

/**
 * Created by Smile on 2016/9/9.
 */
public class CookFragment extends Fragment {
    private TextView mTextView;
    public static Fragment instance(String msg){
        CookFragment fragment=new CookFragment();
        Bundle bundle = new Bundle() ;
        bundle.putString("msg",msg);
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
        mTextView= (TextView) view.findViewById(R.id.cook_tv);
        Bundle bundle = getArguments() ;
        String msg = bundle.getString("msg") ;
        mTextView.setText(msg);
    }
}
