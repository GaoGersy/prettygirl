package com.gersion.prettygirl.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.activity.DetailActivity;
import com.gersion.prettygirl.activity.MainActivity;
import com.gersion.prettygirl.bean.GirlCategory;
import com.gersion.prettygirl.utils.ImageUtil;
import com.gersion.smartrecycleviewlibrary.ptr2.IRVAdapter;

import java.util.List;

public class CommonPictureAdapter extends MultiTypeAdapter implements IRVAdapter<GirlCategory> {
    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        final GirlCategory bean = (GirlCategory) item;
        ImageView ivIcon = (ImageView) helper.getView(R.id.iv_picture);
        final MainActivity activity = (MainActivity) helper.getConvertView().getContext();
        ImageUtil.loadCropImage(activity,bean.getIcon(),ivIcon);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title",bean.getTitle());
                bundle.putInt("categoryId",bean.getCategoryId());
                activity.toActivity(DetailActivity.class,bundle);
            }
        });
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return this;
    }

    @Override
    public void setNewData(List<GirlCategory> list) {
        items = list;
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<GirlCategory> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<GirlCategory> list) {
        items.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(GirlCategory GirlCategory) {
        items.remove(GirlCategory);
        notifyDataSetChanged();
    }

    @Override
    public List<GirlCategory> getData() {
        return items;
    }
}