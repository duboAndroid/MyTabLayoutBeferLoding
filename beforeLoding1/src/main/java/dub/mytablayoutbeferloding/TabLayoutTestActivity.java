package dub.mytablayoutbeferloding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

//http://blog.csdn.net/chenzheng8975/article/details/54645704
public class TabLayoutTestActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private SimpleFragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private String tabTitles[] = new String[]{"tab1", "tab2", "tab3", "tab4", "tab5", "tab6"};

    private int curTab = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTabLayout = (TabLayout) this.findViewById(R.id.layout_tab);
        mViewPager = (ViewPager) this.findViewById(R.id.view_pager);

        for (int i = 0; i < tabTitles.length; i++) {
            PageFragment fragment = new PageFragment(curTab);
            fragment.setTabPos(i);
            mFragments.add(fragment);
        }
        mAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSmoothScrollingEnabled(true);
        //mTabLayout.setTabMode(TabLayout.MODE_FIXED);//全部放下，不滑动
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//超过长度可滑动
        //设置当前显示哪个标签页
        mViewPager.setCurrentItem(curTab);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //滑动监听加载数据，一次只加载一个标签页
                ((PageFragment) mAdapter.getItem(position)).sendMessage();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public SimpleFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        //防止fragment自动销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }
}