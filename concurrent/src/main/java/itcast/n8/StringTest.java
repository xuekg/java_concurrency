package itcast.n8;

import java.nio.charset.Charset;

/**
 * @author xuekg
 * @description
 * @date 2022/6/4 22:26
 **/
public class StringTest {

    public static void main(String[] args) {
        String s1 = "abc";
        System.out.println(s1.getBytes().length);

        String s2 = "王abc争";
        System.out.println(s2.getBytes().length);

        //但是如果你jvm平台使用的是GBK编码方式，那么你通过string里面的getBytes()方式获取的字符的字节是2。
        // 如果使用的是UTF-8编码的方式，那么一个字符getBytes()方式获取的字符的字节长度应该是3.
        System.out.println(Charset.defaultCharset());
    }
}
