package com.tuyano.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mdgdata")
public class MsgData {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String title;

	@Column(nullable = false)
	@NotEmpty
	private String message;

	//以下の記述により、アソシエーションを組んでいる
	//-----------------------------------------------------
	@ManyToOne
	private MyData mydata;

	public MsgData() {
		mydata = new MyData();
	}

	public void setMyData(MyData mydata) {
		this.mydata = mydata;
	}
	//-----------------------------------------------------
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyData getMyData() {
		return mydata;
	}





}
