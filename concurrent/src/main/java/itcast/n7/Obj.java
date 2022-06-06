package itcast.n7;

/**
 * @author xuekg
 * @description
 * @date 2022/6/6 8:40
 **/
public class Obj {
    public static int objCount = 0;

    public Obj(){
        objCount++;
        System.out.println("调用构造函数："+objCount);
    }
}
