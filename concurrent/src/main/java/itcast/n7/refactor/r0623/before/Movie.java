package itcast.n7.refactor.r0623.before;

/**
 * @author xuekg
 * @description 影片类
 * @date 2022/6/23 17:36
 **/
public class Movie {

    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private String title;
    private Price price;

    public double getCharge(int dayRented) {
        return price.getCharge(dayRented);
    }

    public int getFrequentRenterPoints(int dayRented) {
        return price.getFrequentRenterPoints(dayRented);
    }

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrenPrice();
                break;
            case NEW_RELEASE:
                price = new RegularPrice();
                break;
            default:
                throw new IllegalArgumentException("incorrect price code");
        }
    }
}
