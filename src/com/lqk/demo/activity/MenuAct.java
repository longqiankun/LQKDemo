package com.lqk.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lqk.demo.R;
import com.lqk.demo.fragment.DownLoadFragment;
import com.lqk.demo.fragment.EventFragment;
import com.lqk.demo.fragment.HttpFragment;
import com.lqk.demo.fragment.ImageFragment;
import com.lqk.demo.fragment.SQLFragment;
import com.lqk.demo.fragment.VerifFragment;
import com.lqk.framework.inject.InjectInit;
import com.lqk.lib.menu.menudrawer.MenuDrawer;


/**
 * @ClassName: MenuAct
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-4 上午10:57:08
 */

public class MenuAct extends FragmentActivity {

    private MenuDrawer mMenuDrawer;
    private MenuAdapter mAdapter;
    private ListView mList;

    private int mActivePosition = 0;
    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    List<Object> items = new ArrayList<Object>();
	@InjectInit
	public void init(){
		  mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.MENU_DRAG_CONTENT);
		 View containtor= getLayoutInflater().inflate(R.layout.containtor, null);
	        mMenuDrawer.setContentView(containtor);
		
	        initData();
	        
	        mList = new ListView(this);
	        mAdapter = new MenuAdapter(items);
	        mList.setAdapter(mAdapter);
	        mList.setOnItemClickListener(mItemClickListener);

	        mMenuDrawer.setMenuView(mList);

	        mMenuDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_FULLSCREEN);

	        mViewPager = (ViewPager) findViewById(R.id.view_pager);
	        mViewPager
	                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
	                    @Override
	                    public void onPageSelected(final int position) {
	                        mMenuDrawer.setTouchMode(position == 0
	                                ? MenuDrawer.TOUCH_MODE_FULLSCREEN
	                                : MenuDrawer.TOUCH_MODE_NONE);
	                    }
	                });

	        mPagerAdapter = new PagerAdapter(this);
	        mPagerAdapter.addTab(HttpFragment.class, null);
	        mPagerAdapter.addTab(SQLFragment.class, null);
	        mPagerAdapter.addTab(DownLoadFragment.class, null);
	        mPagerAdapter.addTab(EventFragment.class, null);
	        mPagerAdapter.addTab(ImageFragment.class, null);
	        mPagerAdapter.addTab(VerifFragment.class, null);

	        mViewPager.setAdapter(mPagerAdapter);
	}
	
		/**
	* @Title: initData
	* @Description: TODO
	* @param 
	* @return void
	* @throws
	*/
		
	private void initData() {
		  	items.add(new Item("网络项", R.drawable.ic_action_refresh_dark));
	        items.add(new Item("数据库", R.drawable.ic_action_select_all_dark));
	        items.add(new Category("HTTP"));
	        items.add(new Item("图片", R.drawable.ic_action_refresh_dark));
	        items.add(new Item("下载", R.drawable.ic_action_select_all_dark));
	        items.add(new Category("SQL"));
	        items.add(new Item("事件", R.drawable.ic_action_refresh_dark));
	        items.add(new Item("验证", R.drawable.ic_action_select_all_dark));
	}
	   private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position,
	                long id) {
	        	mViewPager.setCurrentItem(position);
	            mActivePosition = position;
	            mMenuDrawer.setActiveView(view, position);
	            mMenuDrawer.closeMenu();
	        }
	    };
	    

	    private static final class Item {

	        String mTitle;
	        int mIconRes;

	        Item(String title, int iconRes) {
	            mTitle = title;
	            mIconRes = iconRes;
	        }
	    }

	    private static final class Category {

	        String mTitle;

	        Category(String title) {
	            mTitle = title;
	        }
	    }

	    private class MenuAdapter extends BaseAdapter {

	        private List<Object> mItems;

	        MenuAdapter(List<Object> items) {
	            mItems = items;
	        }

	        @Override
	        public int getCount() {
	            return mItems.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return mItems.get(position);
	        }

	        @Override
	        public long getItemId(int position) {
	            return position;
	        }

	        @Override
	        public int getItemViewType(int position) {
	            return getItem(position) instanceof Item ? 0 : 1;
	        }

	        @Override
	        public int getViewTypeCount() {
	            return 2;
	        }

	        @Override
	        public boolean isEnabled(int position) {
	            return getItem(position) instanceof Item;
	        }

	        @Override
	        public boolean areAllItemsEnabled() {
	            return false;
	        }

	        @Override
	        public View getView(int position, View convertView, ViewGroup parent) {
	            View v = convertView;
	            Object item = getItem(position);

	            if (item instanceof Category) {
	                if (v == null) {
	                    v = getLayoutInflater().inflate(R.layout.menu_row_category,
	                            parent, false);
	                }

	                ((TextView) v).setText(((Category) item).mTitle);

	            } else {
	                if (v == null) {
	                    v = getLayoutInflater().inflate(R.layout.menu_row_item,
	                            parent, false);
	                }

	                TextView tv = (TextView) v;
	                tv.setText(((Item) item).mTitle);
	                tv.setCompoundDrawablesWithIntrinsicBounds(
	                        ((Item) item).mIconRes, 0, 0, 0);
	            }

	            v.setTag(R.id.mdActiveViewPosition, position);

	            if (position == mActivePosition) {
	                mMenuDrawer.setActiveView(v, position);
	            }

	            return v;
	        }
	    }

	    /**
	     * This is a helper class that implements the management of tabs and all
	     * details of connecting a ViewPager with associated TabHost. It relies on a
	     * trick. Normally a tab host has a simple API for supplying a View or
	     * Intent that each tab will show. This is not sufficient for switching
	     * between pages. So instead we make the content part of the tab host 0dp
	     * high (it is not shown) and the TabsAdapter supplies its own dummy view to
	     * show as the tab content. It listens to changes in tabs, and takes care of
	     * switch to the correct paged in the ViewPager whenever the selected tab
	     * changes.
	     */
	    public static class PagerAdapter extends FragmentPagerAdapter {

	        private final Context mContext;
	        private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	        static final class TabInfo {

	            private final Class<?> mClss;
	            private final Bundle mArgs;

	            TabInfo(Class<?> aClass, Bundle args) {
	                mClss = aClass;
	                mArgs = args;
	            }
	        }

	        public PagerAdapter(FragmentActivity activity) {
	            super(activity.getSupportFragmentManager());
	            mContext = activity;
	        }

	        @Override
	        public int getCount() {
	            return mTabs.size();
	        }

	        @Override
	        public Fragment getItem(int position) {
	            TabInfo info = mTabs.get(position);
	            return Fragment.instantiate(mContext, info.mClss.getName(),
	                    info.mArgs);
	        }

	        public void addTab(Class<?> clss, Bundle args) {
	            TabInfo info = new TabInfo(clss, args);
	            mTabs.add(info);
	            notifyDataSetChanged();
	        }

	    }
}
