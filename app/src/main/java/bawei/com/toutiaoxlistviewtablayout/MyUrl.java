package bawei.com.toutiaoxlistviewtablayout;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author 作者Trydimire E-mail: 867133009@qq.com
 * @version 1.0
 * @date 创建时间：2015-8-7 上午11:31:52
 * @parameter
 * @return
 */
public class MyUrl {

    /**
     * 热词的接口地址
     **/
    public static final String HOT_WORDS_URL = "http://toutiao.com/hot_words/?_=";

    /**
     * 新闻选项的接口地址
     **/
    public static final String NEWS_CATEGORY = "http://ic.snssdk.com/article/category/get/v2/?iid=2939228904";

    /**
     * 所有城市的接口地址(按字母方式排列)
     **/
    public static final String CITY_ADDRESS = "http://ic.snssdk.com/2/article/city/";

    /**
     * 弹出有几条更新的接口
     **/
    public static final String UPDATE_COUNTS = "http://ic.snssdk.com/2/article/v27";
    /**
     * 拼接的第一个参数
     **/
    private static String urlone = "http://ic.snssdk.com/2/article/v25/stream/?category=";

    /**
     * 传入频道名返回接口地址
     * 该方法拼接可以获取实时新闻
     * newstype 从新闻选项的接口中获得 新闻接口中的 ategory中的数据
     */
    public static String getUrl(String newstype ,String userCity) {
        String url = "";

        url = urlone + newstype;
        if(newstype.equals("news_local")){
            try {
                url = urlone + newstype+"&user_city="+ URLEncoder.encode(userCity,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return url;
    }

}
