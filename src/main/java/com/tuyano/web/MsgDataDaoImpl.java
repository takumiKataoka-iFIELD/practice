package com.tuyano.web;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MsgDataDaoImpl implements MsgDataDao {
	private static final long serialVersionUID = 1L;

	private EntityManager entityManager;

	public MsgDataDaoImpl() {
	}

	public MsgDataDaoImpl(EntityManager manager) {
		entityManager = manager;
	}

	@Override
	public List<MsgData> getAll() {
		Query query = entityManager.createQuery("from MyData");
		List <MsgData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public MsgData findById(long id) {
		return (MsgData)entityManager.createQuery("from MsgData where id = " + id).getSingleResult();
	}

}
