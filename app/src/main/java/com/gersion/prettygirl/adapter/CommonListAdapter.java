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
        ImageUtil.loadImage(activity,bean.getIcon(),ivIcon);
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
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<GirlImage> list) {
        items.removeAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void remove(GirlImage GirlImage) {
        items.remove(GirlImage);
        notifyDataSetChanged();
    }

    @Override
    public List<GirlImage> getData() {
        return items;
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.item_common_list, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        GirlImage apkModel = mDatas.get(position);
//        holder.bind(apkModel);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private GirlImage mBean;
//        private final TextView mTvTitle;
//        private final ImageView mIvIcon;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            mTvTitle = itemView.findViewById(R.id.tv_title);
//            mIvIcon = itemView.findViewById(R.id.iv_icon);
//        }
//
//        public void bind(GirlImage bean) {
//            mBean = bean;
//            itemView.setOnClickListener(this);
//            mTvTitle.setText(bean.getTitle());
//            Glide.with(mContext).load(bean.getImageUrl()).into(mIvIcon);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    }
}