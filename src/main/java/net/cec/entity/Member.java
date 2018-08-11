package net.cec.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Member {
	@Id
	private String  id;
	
	private String avatar;
	@Index
	private long birthday;
	@Index
	private String phone;
	@Index
	private String email;
	@Index
	private String name;
	@Index
	private String realName;

	private int status;
	
	private long balance;
	
	
	public Member(String id){
		this.id=id;
		this.status=0;
		this.balance=0;
		
		
		
	}
	
	


	public long getBalance() {
		return balance;
	}




	public void setBalance(long balance) {
		this.balance = balance;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public long getBirthday() {
		return birthday;
	}


	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}
