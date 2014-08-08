package com.lqk.demo.activity;

import com.lqk.demo.R;
import com.lqk.demo.contants.Contants;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectLayer;
import com.lqk.framework.inject.InjectResume;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.util.ToastUtils;
import com.lqk.framework.view.listener.OnClick;
import com.lqk.lib.ui.header.HeaderActivity;
import com.lqk.lib.ui.header.HeaderConfig;
import com.lqk.lib.ui.header.HeaderConfig.BtnClick;
import com.lqk.lib.ui.tabbar.TabConfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * @ClassName: Tab1Act
 * @Description: 
 * @author longqiankun
 * @date 2014-7-31 下午6:11:25
 */
@InjectLayer(value=R.layout.circle_menu,parent=com.lqk.lib.R.id.page_content)
public class Tab3Act extends  BaseHeaderActivity {
	@InjectView(binders={@InjectBinder(method="click",listeners={OnClick.class})})
	TextView tv;
	MainTabActivity mParent;
	@InjectInit
	public void init(){
		mParent=(MainTabActivity) getParent();
	}
	
	private void click(View v){
		Intent intent=new Intent(this,Tab3D1Act.class);
		intent.putExtra(TabConfig.TABSIGNKEY, Contants.sport);
		mParent.updateBodyView(Tab3D1Act.class, intent);
	}
	
	@Override
	protected HeaderConfig getHeaderConfig() {
		HeaderConfig config=new HeaderConfig();
		config.middleTile="体育";
		config.top_bg=R.color.tabbar_2;
		config.ib_top_leftsrc=R.drawable.icon_back;
		config.isShowIbLeft=true;
		config.isShowbtnLeft=true;
		return config;
	}
	@Override
	protected void btnAction(BtnClick btnClick) {

	}
	@Override
	public void setListener() {}
	@Override
	public void initData() {
	}
	}