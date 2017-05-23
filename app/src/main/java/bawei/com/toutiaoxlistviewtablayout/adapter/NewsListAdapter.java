package bawei.com.toutiaoxlistviewtablayout.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bawei.com.toutiaoxlistviewtablayout.R;
import bawei.com.toutiaoxlistviewtablayout.bean.TuijianBean;


public class NewsListAdapter extends BaseAdapter{

	List<TuijianBean.DataBean> newsList;
	ImageLoader imageLoader = ImageLoader.getInstance();
	Context mcontext;
	private PopupWindow popupWindow;
	public NewsListAdapter(Context mcontext, List<TuijianBean.DataBean> newsList) {
		this.newsList = newsList;
		this.mcontext = mcontext;

	}

	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public TuijianBean.DataBean getItem(int position) {
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
		}
		return null;
	}




	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = View.inflate(mcontext, R.layout.list_news_item, null);
			mHolder = new ViewHolder();

			mHolder.item_title = (TextView) view.findViewById(R.id.item_title);
			mHolder.item_lable = (TextView) view.findViewById(R.id.item_lable);
			mHolder.item_media_name = (TextView) view.findViewById(R.id.item_media_name);
			mHolder.item_comment_count = (TextView) view.findViewById(R.id.item_comment_count);
			mHolder.right_image = (ImageView) view.findViewById(R.id.item_middle_image);
			mHolder.item_image_0 = (ImageView) view.findViewById(R.id.item_image01);
			mHolder.item_image_1 = (ImageView) view.findViewById(R.id.item_image02);
			mHolder.item_image_2 = (ImageView) view.findViewById(R.id.item_image03);
			mHolder.item_image_layout =(LinearLayout) view.findViewById(R.id.item_image_layout);
			mHolder.textViewDel = (TextView) view.findViewById(R.id.del_id);

			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
		TuijianBean.DataBean news = getItem(position);



		mHolder.item_title.setText(news.getTitle()+"");
		mHolder.item_lable.setText(news.getSource());
		mHolder.item_comment_count.setText("评论" + news.getComment_count());
		List<TuijianBean.DataBean.ImageBean> imgUrlList = news.getImage_list();
		mHolder.item_comment_count.setVisibility(View.VISIBLE);

		if(imgUrlList !=null && imgUrlList.size() !=0){
			if(imgUrlList.size() == 3){
				mHolder.right_image.setVisibility(View.GONE);
				mHolder.item_image_layout.setVisibility(View.VISIBLE);
				Glide.with(mcontext).load(imgUrlList.get(0).getUrl()).override(250, 180).centerCrop().into(mHolder.item_image_0);
				Glide.with(mcontext).load(imgUrlList.get(1).getUrl()).override(250, 180).centerCrop().into(mHolder.item_image_1);
				Glide.with(mcontext).load(imgUrlList.get(2).getUrl()).override(250, 180).centerCrop().into(mHolder.item_image_2);

				//imageLoader.displayImage(imgUrlList.get(0).getUrl(), mHolder.item_image_0);
				//imageLoader.displayImage(imgUrlList.get(1).getUrl(), mHolder.item_image_1);
				//imageLoader.displayImage(imgUrlList.get(2).getUrl(), mHolder.item_image_2);
			}
		}else if (newsList.get(position).getMiddle_image()!=null){
			mHolder.right_image.setVisibility(View.VISIBLE);
			mHolder.item_image_layout.setVisibility(View.GONE);
			Glide.with(mcontext).load(newsList.get(position).getMiddle_image().getUrl()).centerCrop().into(mHolder.right_image);

			//imageLoader.displayImage(newsList.get(position).getMiddle_image().getUrl(), mHolder.right_image);

		}
		mHolder.textViewDel.setVisibility(View.VISIBLE);
/*
		mHolder.textViewDel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View inflate = LayoutInflater.from(mcontext).inflate(R.layout.pop, null);
				TextView textView = (TextView) inflate.findViewById(R.id.buganxingqu);

				popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);
				ColorDrawable dw = new ColorDrawable(0x10ab82ff);
				popupWindow.setBackgroundDrawable(dw);
				popupWindow.setOutsideTouchable(true);
				// 获取窗体显示的布局的长宽高,然后设置偏移量就能显示在指定控件的上方了   测量出布局的宽高
				inflate.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
				popupWindow.showAsDropDown(mHolder.textViewDel, 0, (int) -(inflate.getMeasuredHeight() + mHolder.textViewDel.getHeight()));
				textView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						newsList.remove(position);
						notifyDataSetChanged();
						popupWindow.dismiss();
					}
				});
//
//				final int[] locationl=new int[2];
//				v.getLocationOnScreen(locationl);
//				Rect rect=new Rect();
//				Paint paint=new Paint();
//
//				View view1 = LayoutInflater.from(mcontext).inflate(R.layout.pop,null,false);
//
//				final PopupWindow popupWindow = new PopupWindow(view1, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//				popupWindow.setFocusable(true);
//				popupWindow.showAsDropDown(mHolder.textViewDel);
//				popupWindow.setOutsideTouchable(true);
//				popupWindow.setBackgroundDrawable(new BitmapDrawable());
// 				float width= 60;
//				popupWindow.showAtLocation(v,Gravity.NO_GRAVITY,(int)(locationl[0]-width),locationl[1]);
//
//				popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//					@Override
//					public void onDismiss() {
//						popupWindow.update();
//					}
//				});

			}
		});*/
		return view;
	}

	static class ViewHolder {
		//title
		TextView item_title;
		//新闻源
		TextView item_media_name;
		//类似推广之类的标签
		TextView item_lable;
		//评论数量
		TextView item_comment_count;
		//右边图片
		ImageView right_image;
		//3张图片布局
		LinearLayout item_image_layout; //3张图片时候的布局
		ImageView item_image_0;
		ImageView item_image_1;
		ImageView item_image_2;
		TextView textViewDel ;
		//大图的图片的话布局
		ImageView large_image;
		//评论布局
		RelativeLayout comment_layout;
		TextView comment_content;
	}
}
