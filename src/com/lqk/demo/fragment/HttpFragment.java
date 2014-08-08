package com.lqk.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lqk.demo.R;
import com.lqk.demo.contants.Contants;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectHttp;
import com.lqk.framework.inject.InjectHttpErr;
import com.lqk.framework.inject.InjectHttpOk;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectObject;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.internet.FastHttpHander;
import com.lqk.framework.internet.InternetConfig;
import com.lqk.framework.internet.ResponseEntity;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.view.listener.OnClick;


/**
 * @ClassName: HttpFragment
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-4 上午10:58:55
 */

public class HttpFragment extends Fragment {
	final static String url=Contants.url+"AnnotationServlet";
	protected LayoutInflater inflater;
	@InjectView(binders ={@InjectBinder(method ="click",listeners={OnClick.class})})
	Button btn_post,btn_get;
	@InjectView TextView tv_json;
	@InjectObject Obj obj=new Obj();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.http, container, false);
//		Handler_Inject.injectOrther(this, view);
		Handler_Inject.injectFragment(this, view);
		return view;
	}
	@InjectInit
	public void init(){
		
	}
	@InjectHttpOk
	public void success(ResponseEntity entity){
		switch (entity.getKey()) {
		case 333:
			obj.s=entity.getContentAsString();
			tv_json.setText(obj.s);
			break;
		case 332:
			obj.s=entity.getContentAsString();
			tv_json.setText(obj.s);
			break;
		default:
			break;
		}
		
	}
	@InjectHttpErr
	private void fail(ResponseEntity entity){
		
	}
	@InjectHttp
	private void result(ResponseEntity entity){
		
	}
	public void click(View v){
		switch (v.getId()) {
		case R.id.btn_post:
			InternetConfig config=new InternetConfig();
			config.setSave(true);
			config.setKey(333);
			FastHttpHander.ajaxForm(url, config, this);
			break;
		case R.id.btn_get:
			InternetConfig configw=new InternetConfig();
			configw.setKey(332);
			FastHttpHander.ajaxGet(url, configw, this);
			break;

		default:
			break;
		}
	}
	
	class Obj{
		public String s;
	}
}
