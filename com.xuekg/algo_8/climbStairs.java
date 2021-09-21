package algo_8;

/**
 * @author xuekg
 * @description
 * @date 2021/9/15 21:14
 **/
public class climbStairs {
    public int climbStairs(int n) {
        if(n <= 1){
            return 1;
        }
        return climbStairs(n -1) + climbStairs(n-2);
    }

}
