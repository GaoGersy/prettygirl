package com.gersion.prettygirl.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.gersion.library.http.HttpHandler;
import com.gersion.prettygirl.R;
import com.gersion.prettygirl.adapter.HomePagerAdapter;
import com.gersion.prettygirl.base.BaseActivity;
import com.gersion.prettygirl.fragment.ForumFragment;
import com.gersion.prettygirl.fragment.MineFragment;
import com.gersion.prettygirl.fragment.PictureFragment;
import com.gersion.prettygirl.view.NoTouchViewPager;
import com.gersion.prettygirl.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TitleView mTitleView;
//    private TabLayout mTablayout;
    private HttpHandler mHttpHandler;
    private BottomNavigationView mBottomNavigationView;
    private List<Fragment> mFragments;
    private ArrayList<String> mTitles;
    private NoTouchViewPager mNoTouchViewPager;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mTitleView = findView(R.id.titleView);

        mTitleView.setTitleText("美图show");
        mTitleView.setBackVisiable(false);

//        BadgeItem badgeItem=new BadgeItem().setBorderWidth(1).setBackgroundColorResource(R.color.colorAccent).setText("2").setHideOnSelect(true);

        mBottomNavigationView = findView(R.id.bottomNavigationView);
//        bottomNavigationView.set
//        BottomNavigationBar bottomNavigationBar= findViewById(R.id.bottomNavigationBar);
//        TextBadgeItem badgeItem=new TextBadgeItem().setBorderWidth(1).setBackgroundColor(Color.RED).setText("2").setHideOnSelect(false);
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
//        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
//        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_picture,R.string.picture).setActiveColor(Color.RED).setBadgeItem(badgeItem))
//                .addItem(new BottomNavigationItem(R.drawable.ic_forum,R.string.forum).setActiveColor(Color.BLUE))
//                .addItem(new BottomNavigationItem(R.drawable.ic_mine, R.string.mine).setActiveColor(Color.YELLOW))
//                .setFirstSelectedPosition(0)
//                .initialise();
//
//        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener(){
//            @Override
//            public void onTabSelected(int position) {
//                LogUtils.d(position);
//            }
//        });
//        mTablayout.setupWithViewPager(mViewPager);
//        mTablayout.setTabsFromPagerAdapter(mHomePagerAdapter);

        PictureFragment pictureFragment = new PictureFragment();
        MineFragment mineFragment = new MineFragment();
        ForumFragment forumFragment = new ForumFragment();
        mFragments = new ArrayList<>();
        mFragments.add(pictureFragment);
        mFragments.add(forumFragment);
        mFragments.add(mineFragment);
        mTitles = new ArrayList<>();
        String picture = "美图show";
        String forum = getResources().getString(R.string.forum);
        String mine = getResources().getString(R.string.mine);
        mTitles.add(picture);
        mTitles.add(forum);
        mTitles.add(mine);
        mNoTouchViewPager = findView(R.id.noTouchViewPager);
        HomePagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager(), mFragments);
        mNoTouchViewPager.setAdapter(adapter);
        mNoTouchViewPager.setOffscreenPageLimit(3);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.menu_picture:
                        mNoTouchViewPager.setCurrentItem(0,false);
                        mTitleView.setTitleText(mTitles.get(0));
                        break;
                        case R.id.menu_forum:
                            mNoTouchViewPager.setCurrentItem(1,false);
                            mTitleView.setTitleText(mTitles.get(1));
                        break;
                        case R.id.menu_mine:
                            mNoTouchViewPager.setCurrentItem(2,false);
                            mTitleView.setTitleText(mTitles.get(2));
                        break;
                }
                return true;
            }
        });
    }


}
