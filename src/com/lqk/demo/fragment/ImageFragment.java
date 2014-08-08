package com.lqk.demo.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lqk.demo.R;
import com.lqk.demo.contants.Contants;
import com.lqk.framework.image.ImageDownloader;
import com.lqk.framework.image.Utils;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectPullRefresh;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.view.PullToRefreshManager;


/**
 * @ClassName: ImageFragment
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-4 上午11:02:50
 * 
 */
public class ImageFragment extends Fragment {
	protected LayoutInflater inflater;
//	@InjectView(down=true,pull=true)
	@InjectView
	ListView lv;
	ArrayList<String> image = new ArrayList<String>();
	public static ImageDownloader mImageFetcher = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.image, container, false);
		Handler_Inject.injectFragment(this, view);
		return view;
	}
	
	@InjectInit
	private void init() {

		// The ImageFetcher takes care of loading images into our ImageView children asynchronously
		mImageFetcher = new ImageDownloader(getActivity(),300);
		mImageFetcher.setLoadingImage(R.drawable.ic_launcher);
		for (int i = 0; i < 1000; i++) {
			image.add(Contants.url+"baiduliulanqi.apk");
		}
		lv.setAdapter(new ImageListAdapter(getActivity(), image));
		lv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView absListView, int scrollState) {
				// Pause fetcher to ensure smoother scrolling when flinging
				if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
					// Before Honeycomb pause image loading on scroll to help with performance
					if (!Utils.hasHoneycomb()) {
						mImageFetcher.setPauseWork(true);
					}
				} else {
					mImageFetcher.setPauseWork(false);
				}
			}

			@Override
			public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			}
		});
	}
	
/*	@InjectPullRefresh
	private void call(int type) {
		PullToRefreshManager.getInstance().onFooterRefreshComplete();
		//完成 刷新
		PullToRefreshManager.getInstance().onHeaderRefreshComplete();
	}*/
}
