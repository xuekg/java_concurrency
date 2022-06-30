package itcast.n7.refactor.r0623.before;

/**
 * @author xuekg
 * @description 租赁
 * @date 2022/6/23 17:37
 **/
public class Rental {

    private Movie movie;
    private int dayRented;

    public double getCharge() {
        return movie.getCharge(dayRented);
    }

    public int getFrequentRenterPoints() {
        return movie.getFrequentRenterPoints(dayRented);
    }

    public Rental(Movie movie, int dayRented) {
        this.movie = movie;
        this.dayRented = dayRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDayRented() {
        return dayRented;
    }

    public void setDayRented(int dayRented) {
        this.dayRented = dayRented;
    }
}
