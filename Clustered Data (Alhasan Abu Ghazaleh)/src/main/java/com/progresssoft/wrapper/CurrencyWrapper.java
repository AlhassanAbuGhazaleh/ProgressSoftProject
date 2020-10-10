package com.progresssoft.wrapper;

import com.progresssoft.model.Currency;

public class CurrencyWrapper {

	private Currency fromCurrency;

	private Currency toCurrency;

	public Currency getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(Currency fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public Currency getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(Currency toCurrency) {
		this.toCurrency = toCurrency;
	}

	public CurrencyWrapper(Currency fromCurrency, Currency toCurrency) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
	}

	public CurrencyWrapper() {
		super();
	}

}
