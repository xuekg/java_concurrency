package itcast.zheng.d0120;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xuekg
 * @description
 * @date 2023/2/1 15:42
 **/
public class DateTest {
    public static void main(String[] args) {

        Integer time=1620734188;
        //1、使用hutool
        System.out.println(DateTime.of(1620734188000L));

        //2、使用SimpleDateFormat
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(new BigDecimal(time).multiply(new BigDecimal(1000)).longValue()));
        System.out.println(date);

        DateTime begin = DateUtil.beginOfDay(new Date());
        DateTime end = DateUtil.endOfDay(new Date());

        System.out.println(begin.getTime());
        System.out.println(Timestamp.valueOf("2023-02-06 12:21:21.433"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss.SSS");
        String format = sdf.format(new Date());
        System.out.println("--------------");
        System.out.println(Timestamp.valueOf(format));
    }
}
