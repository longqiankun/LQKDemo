package com.lqk.demo.model;

import com.lqk.framework.db.annotation.Column;
import com.lqk.framework.db.annotation.NoAutoIncrement;
import com.lqk.framework.db.annotation.Table;


/**
 * @ClassName: User
 * @Description: TODO
 * @author longqiankun
 * @date 2014-8-5 下午2:04:03
 * 
 */
@Table(name="User")
public class User {
@NoAutoIncrement
public int id;
@Column
public String username;
@Column
public String password;
}
