package com.progresssoft.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.progresssoft.model.Currency;

@Repository("CurrencyDao")
public class CurrencyDaoImpl implements CurrencyDao {

	@PersistenceContext(unitName = "Tagtac-JPA")
	@Qualifier("tagtacEntityManagerFactoryBean")
	private EntityManager teleEM;

	@Override
	public Currency findByCurrencyName(String currencyName) {

		StringBuilder dbCurrency = new StringBuilder().append("SELECT c FROM Currency c ")
				.append("WHERE c.currencyName = :currencyName");

		Query query = teleEM.createQuery(dbCurrency.toString());
		query.setParameter("currencyName", currencyName);
		Currency result = (Currency) query.getSingleResult();
		return result;
	}

}
