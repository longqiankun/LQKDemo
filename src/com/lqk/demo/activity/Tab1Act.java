package com.lqk.demo.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lqk.demo.R;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectLayer;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.util.ToastUtils;
import com.lqk.framework.view.listener.OnClick;
import com.lqk.lib.ui.header.HeaderActivity;
import com.lqk.lib.ui.header.HeaderConfig;
import com.lqk.lib.ui.header.HeaderConfig.BtnClick;

/**
 * @ClassName: Tab1Act
 * @Description: TODO
 * @author longqiankun
 * @date 2014-7-31 下午6:11:25
 */
@InjectLayer(value=R.layout.tab1,parent=com.lqk.lib.R.id.page_content)
public class Tab1Act extends BaseHeaderActivity {
	@InjectView(binders = {@InjectBinder(method = "click",listeners={OnClick.class})})
	Button btn_frame;
	
	@InjectInit
	public void init(){}
	@Override
	protected HeaderConfig getHeaderConfig() {
		HeaderConfig config=new HeaderConfig();
		config.middleTile="框架";
		config.top_bg=R.color.tabbar_2;
//		config.ib_top_leftbg=R.drawable.bar_operate_red;
		config.ib_top_leftsrc=R.drawable.icon_back;
//		config.btn_top_leftbg=R.drawable.bar_return_brown;
		
		config.btn_top_leftTextId=R.string.app_name;
		config.isShowbtnLeft=true;
		config.isShowIbLeft=true;
		config.isImpLeft=true;
		config.isImpbtnLeft=true;
		config.isImpIbLeft=true;
		return config;
	}
	public void click(View view){
		switch (view.getId()) {
		case R.id.btn_frame:
			startActivity(new Intent(this,MenuAct.class));
			break;
		}
	}
	@Override
	protected void btnAction(BtnClick btnClick) {
		if(btnClick==BtnClick.LEFT){
			ToastUtils.showToast(this, "框架");
		}
		ToastUtils.showToast(this, "框架2");
	}
	@Override
	public void setListener() {}
	@Override
	public void initData() {
	}

}
