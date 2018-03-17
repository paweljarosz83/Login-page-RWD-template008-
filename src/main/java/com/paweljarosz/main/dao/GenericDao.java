package com.paweljarosz.main.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public interface GenericDao<T,ID> {
	
	public void save(T entity);
	public T findById(ID id);
	public T update(T entity);
	public void delete(T entity);
	
	public EntityManager getEntityManager();
	public Session getSession();
	
}
