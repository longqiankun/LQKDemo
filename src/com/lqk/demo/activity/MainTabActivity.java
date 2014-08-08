package com.lqk.demo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.lqk.demo.R;
import com.lqk.demo.contants.Contants;
import com.lqk.lib.ui.tabbar.TabBarActivity;
import com.lqk.lib.ui.tabbar.TabConfig;



/**
 * @ClassName: MainTabActivity
 * @Description: TODO
 * @author longqiankun
 * @date 2014-7-31 下午6:06:02
 */
public class MainTabActivity extends TabBarActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setBottomBg(R.drawable.bottom_bar);
			
			TabConfig c1=	new TabConfig(Contants.Frame,"框架", R.drawable.menu_plan_icon_s,
					R.drawable.menu_plan_icon_n, new Intent(this,Tab1Act.class), Tab1Act.class);
			c1.isSelected=true;
			c1.isPrompt=true;
			c1.promptRes=R.drawable.starting;
			addTabButton(c1);
			
			TabConfig newTabConfig=new TabConfig(Contants.New,"政治", R.drawable.menu_setting_icon_s,
					R.drawable.menu_setting_icon_n, new Intent(this,Tab2Act.class), Tab2Act.class);
			addTabButton(newTabConfig);
			
			TabConfig spTabConfig=new TabConfig(Contants.sport,"体育", R.drawable.photo_s,
					R.drawable.photo_n, new Intent(this,Tab3Act.class), Tab3Act.class);
			addTabButton(spTabConfig);
			
			addTabButton(new TabConfig(Contants.happy,"娱乐", R.drawable.zonghe_s,
			R.drawable.zonghe_icon_n, new Intent(this,Tab4Act.class), Tab4Act.class));
			
			commit();
			
			updateBodyView(Tab1Act.class, new Intent(this,Tab1Act.class));
		}

}
