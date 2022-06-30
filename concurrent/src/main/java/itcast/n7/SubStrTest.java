package itcast.n7;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuekg
 * @description
 * @date 2022/6/7 11:25
 **/
public class SubStrTest {

    public static void main(String[] args) {
        List<Integer> integers = null;
        integers.stream().forEach(item -> System.out.println(item));
        
        List<String> strings = Arrays.asList("fdsa");
        System.out.println(strings.get(0));

        String rolling = "AP111";
        System.out.println(rolling.substring(4));

        String str1 = "MA-M04";
        String str2 = "MA-M10";
        System.out.println(str1.compareToIgnoreCase(str2));
        System.out.println("=======================");

        List<ObjTest1> list = new ArrayList<>();
        list.add(new ObjTest1(2, "MA-05"));
        list.add(new ObjTest1(1, "MA-06"));
        list.add(new ObjTest1(null, ""));

        Map<Integer, List<ObjTest1>> stringListMap = list.stream().collect(Collectors.groupingBy(ObjTest1::getPadding));

        List<ObjTest1> collect = list.stream().sorted(Comparator.comparing(ObjTest1::getPadding)
                .thenComparing(ObjTest1::getRolling)).collect(Collectors.toList());

        for (ObjTest1 obj : collect) {
            System.out.println(obj.toString());
        }
    }
}

class ObjTest1 {
    private Integer padding;
    private String rolling;

    public Integer getPadding() {
        return padding;
    }

    public void setPadding(Integer padding) {
        this.padding = padding;
    }

    public String getRolling() {
        return rolling;
    }

    public void setRolling(String rolling) {
        this.rolling = rolling;
    }

    @Override
    public String toString() {
        return "Obj{" +
                "padding=" + padding +
                ", rolling='" + rolling + '\'' +
                '}';
    }

    public ObjTest1(Integer padding, String rolling) {
        this.padding = padding;
        this.rolling = rolling;
    }
}

