package com.progresssoft.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.progresssoft.generic.service.GenericServiceImpl;
import com.progresssoft.model.Currency;
import com.progresssoft.model.Deal;
import com.progresssoft.utils.Logger;
import com.progresssoft.utils.ResponseStatus;
import com.progresssoft.utils.ResponseStatus.Status;
import com.progresssoft.wrapper.DealDetailWrapper;

@Service("dealService")
@Transactional
public class DealServiceImpl extends GenericServiceImpl<Deal> implements DealService {

	private static final Logger logger = Logger.getLogger(DealServiceImpl.class);

	private CurrencyService currencyService;

	@Override
	public ResponseStatus saveDealData(DealDetailWrapper dealDetailWrapper) {

		ResponseStatus responseStatus = new ResponseStatus();

		if (dealDetailWrapper.getAmount() == null || dealDetailWrapper.getAmount().equals("")) {
			responseStatus.setStatus(Status.error);
			responseStatus.setMessage("Amount is required");
			return responseStatus;
		}

		if (dealDetailWrapper.getFromCurrency() == null || dealDetailWrapper.getToCurrency() == null) {
			responseStatus.setStatus(Status.error);
			responseStatus.setMessage("Currencies required");
			return responseStatus;
		}

		logger.logInfo("The new entered Deals Data is from currency : " + dealDetailWrapper.getFromCurrency()
				+ " to currency :" + dealDetailWrapper.getToCurrency());

		Iterable<Object> dbIterableCurrencies = currencyService.findAll();
		List<Currency> existCurrencies = new ArrayList<Currency>();
		for (Object object : dbIterableCurrencies) {
			if (object instanceof Currency) {
				existCurrencies.add((Currency) object);
			}
		}

		Map<String, Currency> mapCurrencies = new HashMap<>();
		for (Currency currency : existCurrencies) {
			mapCurrencies.put(currency.getCurrencyName(), currency);
		}

		if (!existCurrencies.isEmpty() && !mapCurrencies.containsKey(dealDetailWrapper.getFromCurrency())
				&& !mapCurrencies.containsKey(dealDetailWrapper.getToCurrency())) {

			responseStatus.setStatus(Status.error);
			responseStatus.setMessage("You should add Currencies first");
			return responseStatus;
		}

		Iterable<Object> dbIterableDeals = findAll();
		List<Deal> existDeals = new ArrayList<Deal>();
		for (Object object : dbIterableDeals) {
			if (object instanceof Deal) {
				existDeals.add((Deal) object);
			}
		}

		Map<Long, Deal> mapDeals = new HashMap<>();
		for (Deal deal : existDeals) {
			mapDeals.put(deal.getId(), deal);
		}

		Currency fromCurrency = currencyService.findByCurrencyName(dealDetailWrapper.getFromCurrency());
		Currency toCurrency = currencyService.findByCurrencyName(dealDetailWrapper.getToCurrency());

		mapDeals.forEach((id, deal) -> {

			if (deal.getFromCurrency() == fromCurrency && deal.getToCurrency() == toCurrency) {
				logger.logInfo("This deal is exist before !!!!!!!");
				return;
			} else {
				Deal newDeal = new Deal(fromCurrency, toCurrency, new Date(), dealDetailWrapper.getAmount());

				this.save(newDeal);
			}
		});

		responseStatus.setStatus(Status.success);
		responseStatus.setMessage("Deal Added Successfully");
		return responseStatus;
	}

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	@Autowired
	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}

}
