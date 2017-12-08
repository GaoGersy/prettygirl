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
import com.gersion.prettygirl.bean.GirlImage;
import com.gersion.prettygirl.utils.ImageUtil;
import com.gersion.smartrecycleviewlibrary.ptr2.IRVAdapter;

import java.util.List;
import java.util.ListIterator;

public class CommonListAdapter extends MultiTypeAdapter implements IRVAdapter<GirlImage> {
    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
       final GirlCategory bean = (GirlCategory) item;
        helper.setText(R.id.tv_title, bean.getTitle());
//        helper.setText(R.id.tv_content, bean.getSummary());
        View llContainer = helper.getView(R.id.ll_container);
        ImageView ivIcon = (ImageView) helper.getView(R.id.iv_icon);
        final MainActivity activity = (MainActivity) helper.getConvertView().getContext();
//        Glide.with(activity)
//                .load(AppConstants.BASE_IMAGE_URL+bean.getImageUrl())
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
//                .into(ivIcon);
//        GlideApp.with(activity)
//                .load(AppConstants.BASE_IMAGE_URL+bean.getIcon())
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
//                .into(ivIcon);
        ImageUtil.loadCropImage(activity,bean.getIcon(),ivIcon);
        llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                bundle.putSerializable("data",bean);
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
    public void setNewData(List<GirlImage> list) {
        items = list;
        notifyDataSetChanged();
    }

    @Override
    public void addData(List<GirlImage> list) {
        int start = items.size();
        items.addAll(list);
        this.notifyItemRangeChanged(start, items.size());
    }

    @Override
    public void removeAll(List<GirlImage> list) {
        items.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(GirlImage girlImage) {
        int position = 0;
        for(ListIterator iterator = this.items.listIterator(); iterator.hasNext(); ++position) {
            GirlImage next = (GirlImage) iterator.next();
            if(next == girlImage) {
                iterator.remove();
                this.notifyItemRemoved(position);
                break;
            }
        }
    }

    @Override
    public List<GirlImage> getData() {
        return items;
    }

}