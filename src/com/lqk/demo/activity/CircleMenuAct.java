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
import com.lqk.lib.menu.circlemenu.RoundSpinView;
import com.lqk.lib.menu.circlemenu.RoundSpinView.MenuItem;
import com.lqk.lib.ui.header.HeaderActivity;
import com.lqk.lib.ui.header.HeaderConfig;
import com.lqk.lib.ui.header.HeaderConfig.BtnClick;
import com.lqk.lib.ui.tabbar.TabConfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @ClassName: Tab1Act
 * @Description: 
 * @author longqiankun
 * @date 2014-7-31 下午6:11:25
 */
@InjectLayer(value=R.layout.circle_menu,parent=com.lqk.lib.R.id.page_content)
public class CircleMenuAct extends  BaseHeaderActivity {
	@InjectView(binders={@InjectBinder(method="click",listeners={OnClick.class})})
	TextView tv;
	
	@InjectView
	LinearLayout ll_circle_menu_containt;
	MainTabActivity mParent;
	@InjectInit
	public void init(){
		mParent=(MainTabActivity) getParent();
		MenuItem[] menuItems=new MenuItem[6];
		menuItems[0]=new MenuItem(R.drawable.zonghe_s, "综合1");
		menuItems[1]=new MenuItem(R.drawable.zonghe_s, "综合2");
		menuItems[2]=new MenuItem(R.drawable.zonghe_s, "综合3");
		menuItems[3]=new MenuItem(R.drawable.zonghe_s, "综合4");
		menuItems[4]=new MenuItem(R.drawable.zonghe_s, "综合5");
		menuItems[5]=new MenuItem(R.drawable.zonghe_s, "综合6");
		RoundSpinView child=new RoundSpinView(this, 300, 250, 100, R.drawable.app_avatar_shadow, menuItems);
//		ll_circle_menu_containt.addView(child);
		ll_circle_menu_containt.setVisibility(View.GONE);
	}
	private void click(View v){
		Intent intent=new Intent(this,Tab2Act.class);
		intent.putExtra(TabConfig.TABSIGNKEY, Contants.New);
		mParent.updateBodyView(Tab2Act.class, intent);
	}
	
	@Override
	protected HeaderConfig getHeaderConfig() {
		HeaderConfig config=new HeaderConfig();
		config.middleTile="体育2";
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