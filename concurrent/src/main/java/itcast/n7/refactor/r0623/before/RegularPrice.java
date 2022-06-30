package itcast.n7.refactor.r0623.before;

/**
 * @author xuekg
 * @description
 * @date 2022/6/30 8:30
 **/
public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 2;
        if (dayRented > 2) {
            result += (dayRented - 2) * 1.5;
        }
        return result;
    }
}
