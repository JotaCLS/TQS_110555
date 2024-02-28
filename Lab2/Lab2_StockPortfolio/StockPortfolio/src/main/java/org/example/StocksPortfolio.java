package org.example;

import java.util.ArrayList;
import java.util.List;


public class StocksPortfolio {

    private iStockmarketService stockmarket;
    private List<Stock> stocks = new ArrayList<Stock>();
    public StocksPortfolio(iStockmarketService stockmarket) {
        this.stockmarket = stockmarket;
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public double totalValue() {
        double value = 0;
        for (Stock s : stocks) {
            value += stockmarket.lookUpPrice(s.getLabel()) * s.getQuantity();
        }
        return value;
    }
}
