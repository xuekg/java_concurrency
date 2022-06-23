package itcast.n7.refactor.r0623.after;

import itcast.n7.refactor.r0623.before.Movie;
import itcast.n7.refactor.r0623.before.Rental;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xuekg
 * @description 顾客
 * @date 2022/6/23 17:38
 **/
public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();


    /**
     * 重构后的代码
     *
     * @return
     */
    public String statement() {
        Iterator<Rental> iterator = rentals.iterator();
        String result = "Rental Record for" + getName() + "\n";
        while (iterator.hasNext()) {
            Rental each = (Rental) iterator.next();

            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        return rental.getCharge();
    }

    private double getTotalCharge() {
        double result = 0;
        Iterator<Rental> iterator = rentals.iterator();
        while (iterator.hasNext()) {
            Rental rental = iterator.next();
            result += rental.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Iterator<Rental> iterator = rentals.iterator();
        while (iterator.hasNext()) {
            Rental rental = iterator.next();
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }


    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
