package bawei.com.toutiaoxlistviewtablayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.alibaba.fastjson.JSON;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bawei.com.toutiaoxlistviewtablayout.adapter.MyAdapter;
import bawei.com.toutiaoxlistviewtablayout.bean.NewsCategory;

public class MainActivity extends FragmentActivity {
    private List<Fragment> list = new ArrayList<Fragment>();
    private IApplication application;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private MyAdapter adapter;
    private List<NewsCategory.DataBeanX.DataBean> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpage);

        showTabLayout();

        application = (IApplication) getApplication();

        initData();
    }

    private void showTabLayout() {
        //TabLayout 和 ViewPager 关联
        tablayout.setupWithViewPager(viewPager);

        // 设置 选中 未选中 字的颜色
        tablayout.setTabTextColors(Color.GRAY, Color.BLACK);

        // 设置 指示器的颜色
        tablayout.setSelectedTabIndicatorColor(Color.RED);

        //设置滚动模式
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    private void initData() {

        RequestParams params = new RequestParams(MyUrl.NEWS_CATEGORY);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {


                NewsCategory newsCategory = JSON.parseObject(result, NewsCategory.class);

                categoryList = newsCategory.getData().getData();

                adapter = new MyAdapter(getSupportFragmentManager(), categoryList);
                viewPager.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}