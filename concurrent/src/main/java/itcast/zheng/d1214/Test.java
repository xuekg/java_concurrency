package itcast.zheng.d1214;

/**
 * @author xuekg
 * @description
 * @date 2022/12/14 16:41
 **/
public class Test {


}
class solution{
    private void sort(int[] a){
        int i = 0;
        int j = a.length;
        while (i < j){
            if(a[i] % 2 == 1){
                i++;
                continue;
            }
            if(a[j] % 2 == 0){
                j--;
                continue;
            }
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }
}
