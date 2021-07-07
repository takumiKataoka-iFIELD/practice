package com.tuyano.web;

import java.io.Serializable;
import java.util.List;

public interface MsgDataDao  extends Serializable{

	public List <MsgData> getAll();
	public MsgData findById(long id);

}
