package itcast.zheng.d1213;

import lombok.val;


/**
 * @author xuekg
 * @description
 * @date 2022/12/13 16:33
 **/
public class Test {


    public static void main(String[] args) {
        int[] arr = {1,4,7,8,3,6,9};
        val solution = new mergeSolution();
        solution.sort(arr,arr.length);
        for (int i = 0; i <arr.length; i++) {
            System.out.println(arr[i]);
        }

    }
}

class bubbleSolution{
    public void sort(int[]a,int n){
        if(n <=1){
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            //提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - 1-i; j++) {
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }
}

class insertSolution{
    public void sort(int[]a,int n){
        if(n <=1){
            return;
        }
        //[0,i)已排序
        for (int i = 1; i < n ; i++) {
            int value = a[i];
            int j = i-1;
            //查找插入的位置
            for(;j>=0;j--){
                if(a[j] >value){
                    //数据移动
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            //插入数据
            a[j+1] = value;
        }
    }
}

class selectSolution{
    public void sort(int[]a,int n){
        if(n <=1){
            return;
        }
        for (int i = 0; i < n-1 ; i++) {
           int minPos = i;
           //查找最小值
            for (int j = i; j < n ; j++) {
                if(a[j] < a[minPos]){
                    minPos = j;
                }
            }
            //交互元素
            int tmp = a[i];
            a[i] = a[minPos];
            a[minPos] = tmp;
        }
    }
}

class mergeSolution{
    public void sort(int[]a,int n){
       this.merge_sort(a,0,n-1);
    }
    public void merge_sort(int[]a,int p,int r){
//        System.out.println("p= "+p+" r= "+r);
        if(p>=r){
            return;
        }
        int q = p+(r-p)/2;
        merge_sort(a,p,q);
        merge_sort(a,q+1,r);

        this.merge(a,p,q,r);
    }

    private void merge(int[] a, int p, int q, int r) {
        System.out.println("p= "+p+" q= "+q+" r= "+r);
        int i = p;
        int j = q+1;
        int k = 0;
        //申请一个大小跟a一样的临时数组
        int[] tmp = new int[r-p+1];
        while (i <= q && j <= r){
            if(a[i] <= a[j]){
                tmp[k++] = a[i++];
            }else{
                tmp[k++] = a[j++];
            }
        }
        while (i<=q){
            tmp[k++] = a[i++];
        }
        while (j <= r){
            tmp[k++] = a[j++];
        }
        //将tmp中的数组拷贝回a数组
        for (int l = 0; l <= r-p; l++) {

            a[p+l] = tmp[l];
        }
    }
}
