package com.paweljarosz.main.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Transactional
@Component
public class GenericDaoImpl<T,ID> implements GenericDao<T,ID>{

	@PersistenceContext
	protected EntityManager em;
	
	private Session session;
	
	private Class<T> cls;
	
	
	public GenericDaoImpl(){
//		Class<?> consideredClass = getClass();
//		while (!GenericDaoImpl.class.equals(consideredClass.getSuperclass())) {
//			consideredClass = consideredClass.getSuperclass();
//		}
//		ParameterizedType genericSuperclass = (ParameterizedType) consideredClass.getGenericSuperclass();
//		this.cls = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public T findById(ID id) {
		return em.find(cls, id);
	}

	@Override
	public T update(T entity) {
		return em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Session getSession() {
		if(session==null){
			return em.unwrap(Session.class);
		}
		return session;
	}

}
