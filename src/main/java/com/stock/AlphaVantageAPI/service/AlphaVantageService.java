package com.stock.AlphaVantageAPI.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.stock.AlphaVantageAPI.config.AlphaVantageConfig;
import com.stock.AlphaVantageAPI.entity.APIData;
import com.stock.AlphaVantageAPI.repository.Stockrepo;


@Service
public class AlphaVantageService {
	private final AlphaVantageConfig config;
	private final RestTemplate restTemplate;

	@Autowired
	private Stockrepo stockrepo;
	
	public AlphaVantageService(AlphaVantageConfig config) {
		this.config = config;
		this.restTemplate = new RestTemplate();
	}
	
	public String getStockData(String symbol, String function) throws IOException, InterruptedException {
		String apiKey = config.getApiKey();
		String apiUrl = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + "&apikey="
				+ apiKey;
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
		return response.getBody();
	}
	
	public String getStockPrice(String symbol, String function) throws IOException, InterruptedException {
		String jsonData = getStockData(symbol, function);
		APIData stockData = new APIData();	
		stockData.setData(jsonData);
		stockrepo.save(stockData);
		return "Work Done";
	}
}
	
	
	/*
	 * HttpClient httpClient = HttpClient.newHttpClient(); HttpRequest httpRequest =
	 * HttpRequest.newBuilder().GET().uri(URI.create(apiUrl)).build();
	 * 
	 * HttpResponse<String> response = httpClient.send(httpRequest,
	 * HttpResponse.BodyHandlers.ofString()); String json = response.body();
	 * System.out.println(json); //string to stringObject Gson gson = new Gson();
	 * StockPrice stockPrice = gson.fromJson(json, StockPrice.class); MetaData
	 * metaData = stockPrice.getMetaData(); StockData stockData = null;
	 * stockdatarepo.deleteAll();
	 * 
	 * switch (function) { case "TIME_SERIES_DAILY_ADJUSTED": for (Map.Entry<String,
	 * StockData> entry : stockPrice.getStockdaily().entrySet()) { String date =
	 * entry.getKey(); stockData = entry.getValue(); System.out.println(stockData);
	 * stockdatarepo.save(stockData); } break; case "TIME_SERIES_INTRADAY": for
	 * (Map.Entry<String, StockData> entry : stockPrice.getStockdaily().entrySet())
	 * { String date = entry.getKey(); stockData = entry.getValue();
	 * System.out.println(stockData); stockdatarepo.save(stockData); } break;
	 * 
	 * 
	 * case "TIME_SERIES_WEEKLY": for (Map.Entry<String, StockData> entry :
	 * stockPrice.getStockweekly().entrySet()) { String date = entry.getKey();
	 * stockData = entry.getValue(); stockdatarepo.save(stockData); } break; case
	 * "TIME_SERIES_MONTHLY": for (Map.Entry<String, StockData> entry :
	 * stockPrice.getStockMonthly().entrySet()) { String date = entry.getKey();
	 * stockData = entry.getValue(); stockdatarepo.save(stockData);
	 * System.out.println(stockData); } break; }
	 * 
	 * metaData.setSymbol(stockPrice.getMetaData().getSymbol());
	 * metadatarepo.save(metaData); System.out.println(metaData); return
	 * "data has been sent"; }
	 */
//}