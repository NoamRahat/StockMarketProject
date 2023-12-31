package StockMarketProject;

import java.util.Calendar;
import java.util.Random;

// class Stock inherits from Asset

public class Stock extends Asset implements IUpdatable {

    private String companyName;

    public Stock(String symbol, double price, double mean, double std, int availableAmount, String companyName) {
        super(symbol, price, mean, std, availableAmount);
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Stock{" + "companyName=" + companyName
                + super.toString() + '}';

    }

    @Override
    public void update() {
        // randomize the change in the price of the stock by the mean and std in normal distribution
        double change = new Random().nextGaussian() * getStd() + getMean();
        double newPrice = getPrice() + (getPrice() * (change / 100));
//        insert the last price to the history of prices
        getHistoryPrices().add(getPrice());
        setPrice(newPrice);
        if (this.getOwners().size() > 0) {
            for (Trader owner : getOwners()) {
                owner.update();
            }
        }
    }


}
