package com.lqk.demo.activity;

import com.lqk.demo.R;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectLayer;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.lib.ui.header.HeaderActivity;
import com.lqk.lib.ui.header.HeaderConfig;
import com.lqk.lib.ui.header.HeaderConfig.BtnClick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


/**
 * @ClassName: Tab1Act
 * @Description: TODO
 * @author longqiankun
 * @date 2014-7-31 下午6:11:25
 * 
 */
@InjectLayer(value=R.layout.tab4,parent=com.lqk.lib.R.id.page_content)
public class Tab4Act extends  BaseHeaderActivity {
	@InjectInit
	public void init(){}
	@Override
	protected HeaderConfig getHeaderConfig() {
		HeaderConfig config=new HeaderConfig();
		config.middleTile="娱乐";
		config.top_bg=R.color.tabbar_2;
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