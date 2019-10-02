package com.tuyano.web;

import java.io.Serializable;
import java.util.List;

public interface MsgDataDao  extends Serializable{

	List <MsgData> getAll();

}
