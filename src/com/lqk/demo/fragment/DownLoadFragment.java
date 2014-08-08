package com.lqk.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lqk.demo.R;
import com.lqk.demo.activity.MainTabActivity;
import com.lqk.demo.contants.Contants;
import com.lqk.framework.download.FileLoaderManager;
import com.lqk.framework.download.NotfiEntity;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.view.listener.OnClick;


/**
 * @ClassName: HttpFragment
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-4 上午10:58:55
 * 
 */

public class DownLoadFragment extends Fragment {
	protected LayoutInflater inflater;
	@InjectView(binders={@InjectBinder(method="click",listeners={OnClick.class})})
	Button download;
	@InjectView
	ProgressBar pb;
	String url;
	@InjectInit
	public void init(){
		url=Contants.url+"baiduliulanqi.apk";
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.download, container, false);
		Handler_Inject.injectFragment(this, view);
		return view;
	}
	public void click(View v){
		FileLoaderManager.download(url);
		NotfiEntity notfi=new NotfiEntity();
		notfi.setClazz(MainTabActivity.class);
		notfi.setIcon_id(R.drawable.ic_launcher);
		notfi.setLayout_id(R.layout.notification);
		notfi.setProgress_id(R.id.pb);
		notfi.setProgress_txt_id(R.string.app_name);
		FileLoaderManager.showNotif(url, notfi);
	}
}
