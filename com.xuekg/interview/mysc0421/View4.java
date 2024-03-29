package interview.mysc0421;

/**
 * 总共100级台阶(任意级都行)，小明每次可选择走1步、2步或者3步，问走完这100级台阶总共有多少种走法？
 * 这个问题本质上是斐波那契数列，假设只有一个台阶，那么只有一种跳法，那就是一次跳一级，f(1)=1；
 * 如果有两个台阶，那么有两种跳法，第一种跳法是一次跳一级，第二种跳法是一次跳两级,f(2)=2。
 * 如果有大于2级的n级台阶，那么假如第一次跳一级台阶，剩下还有n-1级台阶，有f(n-1)种跳法，
 * 假如第一次条2级台阶，剩下n-2级台阶，有f(n-2)种跳法。这就表示f(n)=f(n-1)+f(n-2)。
 */
public class View4 {
  static final int s = 990; // 自定义的台阶数

  static int compute(int stair) {
    if (stair <= 0) {
      return 0;
    }

    if (stair == 1) {
      return 1;
    }

    if (stair == 2) {
      return 2;
    }

    return compute(stair - 1) + compute(stair - 2);
  }

  public static void main(String args[]) {
    System.out.println("共有" + compute(s) + "种走法");
  }
}