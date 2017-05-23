package bawei.com.toutiaoxlistviewtablayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bawei.com.mylibrary.XListView;
import bawei.com.toutiaoxlistviewtablayout.IApplication;
import bawei.com.toutiaoxlistviewtablayout.MyUrl;
import bawei.com.toutiaoxlistviewtablayout.NetUtil;
import bawei.com.toutiaoxlistviewtablayout.R;
import bawei.com.toutiaoxlistviewtablayout.SteamTools;
import bawei.com.toutiaoxlistviewtablayout.adapter.NewsListAdapter;
import bawei.com.toutiaoxlistviewtablayout.bean.TuijianBean;


/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class NewsMainFragment extends Fragment implements XListView.IXListViewListener {
    private ListView listView;


    private String newsType;
    private List<TuijianBean.DataBean> list;

    private List<TuijianBean.DataBean> listAll = new ArrayList<>();

    private NewsListAdapter adapter;
    private IApplication application;
    private XListView xListView;
    private LinearLayout linearLayout;
    private String userCity = "北京";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_news, null);
        View headerView = inflater.inflate(R.layout.listview_header_item, null);

//        linearLayout = (LinearLayout) headerView.findViewById(R.id.linearlayout_city);

        xListView = (XListView) view.findViewById(R.id.xlistView);

//        listView = (ListView) view.findViewById(R.id.news_listview);
//        xListView.addHeaderView(headerView);

        newsType = getArguments().getString("newstype");

        application = (IApplication) getActivity().getApplication();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*springView.setType(SpringView.Type.FOLLOW);//不重叠


        springView.setHeader(new AcFunHeader(getActivity(),R.drawable.acfun_header));

        springView.setFooter(new AcFunFooter(getActivity(),R.drawable.acfun_footer));

        springView.setListener(this);*/

        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);


        initData(true);
/*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(),WebViewActivity.class);
                String url = listAll.get(i-1).getUrl();
                intent.putExtra("url",url);

                getActivity().startActivity(intent);

                getActivity().overridePendingTransition(R.anim.in1,R.anim.out1);
            }
        });*/

        adapter = new NewsListAdapter(getActivity(), listAll);

    }


    public void initData(boolean flag) {


        //判断是否WIFI网络
        if (NetUtil.GetNetype(getActivity()).equals("WIFI")) {
            findDatasFromIntentle(flag);
        } else {
            findDatasFromDB();
        }

        setData(flag);
    }




    private void findDatasFromIntentle(final boolean flag) {

        RequestParams requestParams = new RequestParams(MyUrl.getUrl(newsType, userCity));

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            private DbManager manager;

            @Override
            public void onSuccess(String result) {
                TuijianBean tuijianBean = JSON.parseObject(result, TuijianBean.class);
                list = tuijianBean.getData();

                listAll.addAll(list);

                if (flag) {
                    adapter = new NewsListAdapter(getActivity(), listAll);
                    xListView.setAdapter(adapter);

                } else {
                    adapter.notifyDataSetChanged();
                }


//                SteamTools.WriteToFile(result,"data.txt");
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

    private void findDatasFromDB() {

        String result = SteamTools.readSdcardFile("data.txt");
        TuijianBean tuijianBean = JSON.parseObject(result, TuijianBean.class);
        list = tuijianBean.getData();
        adapter = new NewsListAdapter(getActivity(), list);
        xListView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
    public void setData(boolean flag) {

        if (flag) {

            xListView.setAdapter(adapter);

        } else {

            adapter.notifyDataSetChanged();

        }

    }
    @Override
    public void onRefresh() {

        listAll.clear();
        adapter.notifyDataSetChanged();
        initData(true);
        xListView.stopRefresh();
        xListView.setRefreshTime("松开即可推荐");
    }


    @Override
    public void onLoadMore() {
        initData(false);
        xListView.stopLoadMore();
    }
}
