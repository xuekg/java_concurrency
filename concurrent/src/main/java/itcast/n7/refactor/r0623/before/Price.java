package itcast.n7.refactor.r0623.before;

/**
 * @author xuekg
 * @description 修改影片分类结构、改变计费规则，改变常客积分计算规则，都基于此类进行扩展
 * @date 2022/6/30 8:29
 **/
public abstract class Price {

    abstract int getPriceCode();

    abstract double getCharge(int dayRented);

    public int getFrequentRenterPoints(int dayRented) {
        return 1;
    }
}
