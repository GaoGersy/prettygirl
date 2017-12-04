package com.gersion.prettygirl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.bean.GirlImage;
import com.gersion.prettygirl.utils.ImageUtil;
import com.gersion.smartrecycleviewlibrary.ptr2.IRVAdapter;

import java.util.List;

public class CommonPictureAdapter extends MultiTypeAdapter implements IRVAdapter<GirlImage> {
    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
//       final GirlImage.DataBean.ListBean bean = (GirlImage.DataBean.ListBean) item;
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
        final GirlImage bean = (GirlImage) item;
        ImageView ivIcon = (ImageView) helper.getView(R.id.iv_picture);
        Context context = helper.getConvertView().getContext();
//        GlideApp.with(context)
//                .load(AppConstants.BASE_IMAGE_URL+bean.getImageUrl())
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(ivIcon);
        ImageUtil.loadImage(context,bean.getImageUrl(),ivIcon);
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public void setNewData(List<GirlImage> list) {
        items = list;
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<GirlImage> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<GirlImage> list) {
        items.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(GirlImage girlImage) {
        items.remove(girlImage);
        notifyDataSetChanged();
    }

    @Override
    public List<GirlImage> getData() {
        return items;
    }
}