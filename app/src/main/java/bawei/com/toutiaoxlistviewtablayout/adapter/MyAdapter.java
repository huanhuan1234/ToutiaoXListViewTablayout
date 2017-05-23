package bawei.com.toutiaoxlistviewtablayout.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import bawei.com.toutiaoxlistviewtablayout.bean.NewsCategory;
import bawei.com.toutiaoxlistviewtablayout.fragment.NewsMainFragment;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class MyAdapter extends FragmentPagerAdapter {
   private List<NewsCategory.DataBeanX.DataBean> list;

    public MyAdapter(FragmentManager fm, List<NewsCategory.DataBeanX.DataBean> list) {
        super(fm);
       this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        NewsMainFragment f = new  NewsMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("newstype",list.get(position).getCategory());
        f.setArguments(bundle);
        return  f;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }
}
