package itcast.test;

/**
 * @author xuekg
 * @description 二进制转换为十进制
 * 将每一位与其对应的权值相乘，得到的结果相加
 * 1-------0-------1-------1
 * 2^3   2^2      2^1      2^0
 * 1*2^3+0*2^2+1*2^1+1*2^0
 * @date 2022/5/26 17:59
 **/
public class Test12_4 {

    /**
     * @param binaryArr {1,0,1,1}
     * @param k         表示二进制位个数，数组长度
     * @return
     */
    public int convert(int[] binaryArr, int k) {
        int a = 0;
        int weight = 1;
        for (int i = k - 1; i >= 0; i--) {
            a += binaryArr[i] * weight;
            weight *= 2;
        }
        return a;
    }
}
