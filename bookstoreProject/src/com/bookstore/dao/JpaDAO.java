package com.bookstore.dao;

import java.lang.ref.Reference;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bookstore.entity.Users;

public class JpaDAO<E> {
	protected EntityManager entityManager;

	public JpaDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public E create(E entity){
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	public E update(E entity){
		entityManager.getTransaction().begin();
		entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}
	
	public void delete(Class<E> type,Object id){
		entityManager.getTransaction().begin();
		Object reference=entityManager.getReference(type, id);
		entityManager.remove(reference);
		entityManager.getTransaction().commit();
	}
	
	public E find(Class<E> type,Object id){
		E entity=entityManager.find(type, id);
		if(entity!=null){
			entityManager.refresh(entity);
		}
		return entity;
	}
	
	public List<E> findWithNamedQuery(String queryName){
		Query query=entityManager.createNamedQuery(queryName);
		return query.getResultList();
	}

	
	public long countWithNamedQuery(String queryName){
		Query query=entityManager.createNamedQuery(queryName);
		return (long)query.getSingleResult();
	}

	public List<E> findWithNamedQuery(String queryName, String paramName, Object paramValue) {
		Query query=entityManager.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		return query.getResultList();
	}
	
	public List<E> findWithNamedQuery(String queryName, Map<String,Object> parameters) {
		Query query=entityManager.createNamedQuery(queryName);
		Set<Entry<String,Object>> setParameters=parameters.entrySet();
		for(Entry<String,Object> entry:setParameters){
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}
}