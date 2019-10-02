package com.tuyano.web;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mydata")
public class MyData {

	@OneToMany(cascade = CascadeType.ALL)
	@Column(nullable = true)
	private List<MsgData> msgdatas;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(length = 50, nullable =false)
	@NotEmpty
	private String name;

	@Column(length = 200, nullable = true)
	@Email
	private String mail;

	@Column(nullable = true)
	@Min(0)
	@Max(200)
	private int age;

	@Column(nullable = true)
	private String memo;

	public MyData(String name,String mail,String memo,int age) {
		this.name = name;
		this.mail = mail;
		this.memo = memo;
		this.age = age;
	}
	public MyData() {

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public int getAge() {
		return age;
	}

	public String getMemo() {
		return memo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}
}
