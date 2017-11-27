package com.gersion.prettygirl.adapter;

import android.support.v7.widget.RecyclerView;

import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;
import com.gersion.prettygirl.bean.HomeListBean;
import com.gersion.smartrecycleviewlibrary.ptr2.IRVAdapter;

import java.util.List;

public class CommonPictureAdapter extends MultiTypeAdapter implements IRVAdapter<HomeListBean> {
    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
//       final HomeListBean.DataBean.ListBean bean = (HomeListBean.DataBean.ListBean) item;
//        helper.setText(R.id.tv_title, bean.getTitle());
//        helper.setText(R.id.tv_content, bean.getSummary());
//        View llContainer = helper.getView(R.id.ll_container);
//        final MainActivity activity = (MainActivity) helper.getConvertView().getContext();
//        llContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",bean);
//                activity.toActivity(DetailActivity.class,bundle);
//            }
//        });
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public void setNewData(List<HomeListBean> list) {

    }

    @Override
    public void addData(List<HomeListBean> list) {

    }

    @Override
    public void removeAll(List<HomeListBean> list) {

    }

    @Override
    public void remove(HomeListBean homeListBean) {

    }

    @Override
    public List<HomeListBean> getData() {
        return items;
    }
}