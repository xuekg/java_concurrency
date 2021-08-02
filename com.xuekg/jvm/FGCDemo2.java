package jvm;

/**
 * @author xuekg
 * @description 新生代gc15次进入老年代
 * 不要再idea中运行，去maven项目中打成jar包，指定要运行的main方法，然后java jvm参数 -jar xxx 来执行
 * @date 2021/8/2 10:49
 **/
public class FGCDemo2 {
//    jvm配置-XX:NewSize=10485760-XX:MaxNewSize=10485760
//    -XX:InitialHeapSize=20971520-XX:MaxHeapSize=20971520
//    -XX:SurvivorRatio=3-XX:MaxTenuringThreshold=15
//    -XX:PretenureSizeThreshold=10485760-XX:+UseParNewGC
//    -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails
//    -XX:+PrintGCTimeStamps -Xloggc:gcold3.log

    public static void main(String[] args) {
        byte[] array2 = null;
        for (int i = 0; i < 15; i++) {
            byte[] array1 = new byte[2 * 1024 * 1024];
            array1 = null;
            array1 = new byte[2 * 1024 * 1024];
            array1 = null;
            if (i == 0) {
                array2 = new byte[18 * 1024];
            }
            byte[] array3 = new byte[2 * 1024 * 1024];
            array3 = null;
        }
    }
}
