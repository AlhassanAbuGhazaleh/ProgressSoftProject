package com.progresssoft.dao;

import com.progresssoft.model.Currency;

public interface CurrencyDao {
	public Currency findByCurrencyName(String currencyName);
}
