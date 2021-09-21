package algo_3;

/**
 * @author xuekg
 * @description 异或 相同为0，不同为1
 * 无进位相加
 * 0^N = N
 * N^N = 0
 * @date 2021/9/5 19:28
 **/
public class EvenTimesOddTimes {

    /**
     * 一个数组中，只有一个数出现了奇数次
     * 其他数都出现了偶数次
     * 求出此数是多少
     *
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int cur : arr) {
            eor ^= cur;
        }
        System.out.println(eor);
    }

    /**
     * 一个数组中
     * 有两个数出现了奇数次
     * 其他都出现了偶数次
     * 求出这两个数是多少
     *
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0, onlyOne = 0;
        for (int curNum : arr) {
            eor ^= curNum;
        }

        //eor = a^b
        //eor !=0
        //eor 必然有一个位置上是1
        //提取出最右的1
        //eor = 1010111100
        //取反 = 0101000011
        //+1  = 0101000100
        //&   = 0000000100
        int rightOne = eor & (~eor + 1);
        for (int cur : arr) {
            // == 0或 == 1都可以，只要取一个值
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        //onlyone是a或者b
        //eor与其^就得到另外一个了

        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }
}
