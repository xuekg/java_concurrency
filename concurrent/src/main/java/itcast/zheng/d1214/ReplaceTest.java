package itcast.zheng.d1214;

/**
 * @author xuekg
 * @description
 * @date 2022/12/15 15:19
 **/
public class ReplaceTest {

    public static void main(String[] args) {
        String s = "a_b-c_dd";
        s = s.replace("-",",").replace("_",",");
        String[] split = s.split(",");
        for (String s1: split){
            System.out.println(s1);
        }
    }
}
