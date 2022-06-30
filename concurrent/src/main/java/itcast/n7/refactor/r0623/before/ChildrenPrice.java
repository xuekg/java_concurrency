package itcast.n7.refactor.r0623.before;


/**
 * @author xuekg
 * @description
 * @date 2022/6/30 8:29
 **/
public class ChildrenPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 1.5;
        if (dayRented > 3) {
            result += (dayRented - 3) * 1.5;
        }
        return result;
    }
}
