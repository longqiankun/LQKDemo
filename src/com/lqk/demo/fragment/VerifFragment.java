package com.lqk.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lqk.demo.R;
import com.lqk.framework.constant.RegExpConstants;
import com.lqk.framework.inject.InjectBinder;
import com.lqk.framework.inject.InjectView;
import com.lqk.framework.util.Handler_Inject;
import com.lqk.framework.verification.Rule;
import com.lqk.framework.verification.Validator;
import com.lqk.framework.verification.Validator.ValidationListener;
import com.lqk.framework.verification.annotation.Regex;
import com.lqk.framework.verification.annotation.TextRule;
import com.lqk.framework.view.listener.OnClick;


/**
 * @ClassName: VerifFragment
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-4 上午11:01:23
 * 
 */

public class VerifFragment extends Fragment implements ValidationListener {
	protected LayoutInflater inflater;
	String phoneNo=RegExpConstants.MobileNO;
	@InjectView
	@Regex(order=1,message="必须是数字",trim=true,pattern="[a-zA-Z0-9_]{6,15}")
	EditText et_phone;
	@InjectView
	@TextRule(maxLength = 16, minLength = 4, trim = true, message = "密码长度4到16位", order = 2)
	EditText et_pwd;
	@InjectView(binders={@InjectBinder(method="click",listeners={OnClick.class})})
	Button et_ok;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		View view = inflater.inflate(R.layout.verification, container, false);
		Handler_Inject.injectFragment(this, view);
		return view;
	}
	
	private void click(View v){
		Validator validator = new Validator(this);
		validator.setValidationListener(this);
		validator.validate();
	}
		
	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();
		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
		}
	
	}
	
		/* (non-Javadoc)
		 * @see com.lqk.framework.verification.Validator.ValidationListener#onValidationSucceeded()
		 */
		
	@Override
	public void onValidationSucceeded() {
		// TODO Auto-generated method stub
		
	}
	
	
}
