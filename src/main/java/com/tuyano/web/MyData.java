package com.tuyano.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "mydata")
public class MyData {

	@Id
	@GeneratedValue
	@Column
	@NotNull
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
