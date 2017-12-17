package com.gersion.prettygirl.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gersion.library.http.HttpHandler;
import com.gersion.library.utils.GsonHelper;
import com.gersion.library.utils.LogUtils;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.CommonPictureAdapter;
import com.gersion.prettygirl.base.BaseFragment;
import com.gersion.prettygirl.bean.GirlCategory;
import com.gersion.prettygirl.bean.RandomResultBean;
import com.gersion.prettygirl.constants.AppConstants;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by aa326 on 2017/12/11.
 */

public class RandomFragment extends BaseFragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, HttpHandler.ResultCallBack<RandomResultBean> {

    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter<RecyclerView.ViewHolder> infiniteAdapter;
    private HttpHandler mHttpHandler;
    private CommonPictureAdapter mAdapter;
    private FloatingActionButton mFabRandom;
    private ShineButton mSbLike;
    private ShineButton mSbStar;
    private ImageView mAnimationIV;
    private FrameLayout mFlCover;
    private int maxId;
    private int minId;
    private List<Integer> showedItems = new ArrayList<>();
    private Random mRandom;
    private TextView mTvTitle;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_random;
    }

    @Override
    protected void initView() {
        itemPicker = (DiscreteScrollView) findView(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        mAdapter = new CommonPictureAdapter();
        mAdapter.registerMultiBean(GirlCategory.class, R.layout.item_picture_list);
        infiniteAdapter = InfiniteScrollAdapter.wrap(mAdapter);
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(100);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.7f)
                .build());
        mAnimationIV = findView(R.id.iv_random);
        mFabRandom = findView(R.id.fab_random);
        mSbLike = findView(R.id.sb_like);
        mSbStar = findView(R.id.sb_star);
        mFlCover = findView(R.id.fl_cover);
        mTvTitle = findView(R.id.tv_title);

    }

    private void startRandomAnim() {
        mFlCover.setVisibility(View.VISIBLE);
        mAnimationIV.setImageResource(R.drawable.random_ainm);
        AnimationDrawable animationDrawable = (AnimationDrawable) mAnimationIV.getDrawable();
        animationDrawable.start();
    }

    private void stopRandomAnim() {
        AnimationDrawable animationDrawable = (AnimationDrawable) mAnimationIV.getDrawable();
        animationDrawable.stop();
        mFlCover.setVisibility(View.GONE);
    }

    @Override
    protected void initData(Bundle bundle) {
        mRandom = new Random();
        Map<String,Object> map = new HashMap<>();
        map.put("categoryIds","");
        mHttpHandler = new HttpHandler.Builder(getActivity(), this, RandomResultBean.class)
                .setUrl(AppConstants.URLS.GET_CATEGORY_BY_IDS )
                .setHttpMethod(HttpHandler.POST)
                .setParamMap(map)
                .setResultCallBack(this)
                .build();
        HttpHandler.ResultCallBack resultCallBack = new HttpHandler.ResultCallBack<String>() {
            @Override
            public void handleSucess(String result) {
                int data = GsonHelper.getInt(result, "data");
                if (maxId < data) {
                    minId = maxId;
                    maxId = data;
                }else {
                    minId = data;
                }
                String randomIds = getRandomIds();
                LogUtils.e(randomIds);

                mHttpHandler.setParamMap("categoryIds",randomIds);
                mHttpHandler.getBeanData();
            }

            @Override
            public void handleError(Throwable throwable) {
                LogUtils.e(throwable.getMessage());
            }
        };
        HttpHandler.Builder resultBuilder = new HttpHandler.Builder(getActivity(), this, RandomResultBean.class)
                .setHttpMethod(HttpHandler.GET)
                .setResultCallBack(this);
        resultBuilder.setUrl(AppConstants.URLS.GET_MAX_CATEGORY_ID)
                .setResultCallBack(resultCallBack).build().getString();
        resultBuilder.setUrl(AppConstants.URLS.GET_MIN_CATEGORY_ID)
                .setResultCallBack(resultCallBack).build().getString();

    }

    private String getRandomIds() {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<10;i++) {
            int num = mRandom.nextInt(maxId - minId) + minId;
            if (!showedItems.contains(num)){
                showedItems.add(num);
                sb.append(num).append(",");
            }else {
                i--;
            }
        }
        return sb.toString();
    }

    @Override
    protected void initListener() {
        mFabRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRandomAnim();
                String randomIds = getRandomIds();
                mHttpHandler.setParamMap("categoryIds",randomIds);
                mHttpHandler.getBeanData();
            }
        });
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        List<GirlCategory> data = mAdapter.getData();
        if (data.size()>0) {
            onItemChanged(data.get(positionInDataSet));
        }
    }

    private void onItemChanged(GirlCategory item) {
        mTvTitle.setText(item.getTitle());
//        changeRateButtonState(item);
    }

    @Override
    public void handleSucess(RandomResultBean categoryResultBean) {
        List<GirlCategory> list = categoryResultBean.getData();
        mAdapter.setNewData(list);
        stopRandomAnim();
    }

    @Override
    public void handleError(Throwable throwable) {
        LogUtils.e(throwable.getMessage());
        stopRandomAnim();
    }
}
