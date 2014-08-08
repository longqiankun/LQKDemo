package com.lqk.demo.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lqk.demo.R;
import com.lqk.demo.fragment.SQLFragment.MyAdapter.ViewHolder;
import com.lqk.demo.model.User;
import com.lqk.framework.adapter.LazyAdapter;
import com.lqk.framework.db.sqlite.DbModelSelector;
import com.lqk.framework.db.sqlite.DbUtils;
import com.lqk.framework.db.sqlite.WhereBuilder;
import com.lqk.framework.db.table.DbModel;
import com.lqk.framework.inject.InjectAll;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectInit;
import com.lqk.framework.inject.InjectStop;
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

public class SQLFragment extends Fragment {
	protected LayoutInflater inflater;
	DbUtils mDbUtils;
	@InjectAll
	Views v;
	class Views{
		@InjectBinder(method="click",listeners={OnClick.class})
		Button add,update,delete,select;
		EditText et_username,et_password,et_username_update,et_password_update,et_no,et_no_delete;
	   ListView lv_list;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.sql, container, false);
		Handler_Inject.injectFragment(this, view);
		return view;
	}
	@InjectInit
	public void init(){
		 mDbUtils=	DbUtils.create(getActivity(), "lqk.db");
		 updatedata();
	}
	@InjectStop
	public void stop(){
		
	}
	public void click(View v){
		switch (v.getId()) {
		case R.id.add:
			add();
			updatedata();
			break;
		case R.id.update:
			update();
			updatedata();
			break;
		case R.id.delete:
			delete();
			updatedata();
			break;
		case R.id.select:
			updatedata();
			break;

		default:
			break;
		}
	}
	private void updatedata(){
		select();
		setAdapter2();
	}
	private ArrayList<Map<String, String>> dataList(){
		List<DbModel> DBS=select();
		ArrayList<Map<String, String>> list=new ArrayList<Map<String,String>>();
		for (int i = 0; i <DBS.size(); i++) {
			DbModel db=	DBS.get(i);
			list.add(db.getDataMap());
		}
		return list;
	}
	private ArrayList<HashMap<String, String>> dataList2(){
		List<User> DBS=select2();
		ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for (int i = 0; i <DBS.size(); i++) {
			User db=	DBS.get(i);
			HashMap<String, String> hs=new HashMap<String, String>();
			hs.put("tv_id", db.id+"");
			hs.put("tv_username", db.username+"");
			hs.put("tv_password", db.password+"");
			list.add(hs);
		}
		return list;
	}
	private void setAdapter(boolean a) {
//		MyAdapter adapter = new MyAdapter(v.lv_list,  a?dataList():dataList2(), R.layout.user_item);
//		v.lv_list.setAdapter(adapter);
	}
	private void setAdapter2() {
		String[] from={"id","username","password"};
		int[] to={R.id.id,R.id.username,R.id.password};
		SimpleAdapter adapter=new SimpleAdapter(this.getActivity(), dataList(), R.layout.user_item, from, to);
//		MyAdapter adapter = new MyAdapter(v.lv_list,  dataList2(), R.layout.user_item);
		v.lv_list.setAdapter(adapter);
	}
		/**
	* @Title: add
	* @Description: TODO
	* @param 
	* @return void
	* @throws
	*/
		
	private void add() {
		User user=new User();
		user.username=v.et_username.getText().toString();
		user.password=v.et_password.getText().toString();
		mDbUtils.save(user);
	}
	private void update() {
		User user=new User();
		user.username=v.et_username_update.getText().toString();
		user.password=v.et_password_update.getText().toString();
		WhereBuilder wb=WhereBuilder.b("id", "=", v.et_no.getText().toString());
		mDbUtils.update(user, wb);
	}
	private void delete() {
		WhereBuilder wb=WhereBuilder.b("id", "=", v.et_no_delete.getText().toString());
		mDbUtils.delete(User.class, wb);
	}
	private List<DbModel> select(){
		return mDbUtils.findDbModelAll(DbModelSelector.from(User.class));
	}
	private List<User> select2(){
return mDbUtils.findAll(new User());
	}
	
	public class MyAdapter extends LazyAdapter<HashMap<String, String>, ViewHolder> {
		public MyAdapter(ListView view, ArrayList<HashMap<String, String>> dataList, int layout_id) {
		    super(view, dataList, layout_id);
	    }
		@Override
		public void deal(HashMap<String, String> data, ViewHolder viewHold, int position) {
			super.deal(data, viewHold, position);
			System.out.println("getview的实现逻辑");
		}
		@Override
		public void download(ImageView view, String url) {
			super.download(view, url);
			System.out.println("图片下载的实现逻辑");
		}
		public class ViewHolder {
			@InjectView
			public TextView id,username,password;
		}
	}
}
