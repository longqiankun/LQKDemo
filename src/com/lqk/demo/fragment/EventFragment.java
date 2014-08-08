package com.lqk.demo.fragment;

import com.lqk.demo.R;
import com.lqk.framework.event.EventBus;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectStop;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.util.ToastUtils;
import com.lqk.framework.view.listener.OnClick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * @ClassName: EventFragment
 * @Description: 
 * @author longqiankun
 * @date 2014-8-4 上午11:03:44
 */
public class EventFragment extends Fragment {
	protected LayoutInflater inflater;
	
	@InjectView(binders={@InjectBinder(method="click",listeners={OnClick.class})})
	Button send;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.event, container, false);
		Handler_Inject.injectOrther(this, view);
		return view;
	}
	@InjectInit
	private void init(){
		EventBus.getDefault().register(this);
	}
	@InjectStop
	private void stop(){
		EventBus.getDefault().unregister(this);
	}
	private void click(View v){
		EventBus.getDefault().post("发送的是内容");
	}
	public void onEventMainThread(String s){
		ToastUtils.showToast(this.getActivity(), s);
	}
}
