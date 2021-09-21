package md5;

/**
 * @author xuekg
 * @description
 * @date 2021/9/18 11:26
 **/
public class Md5Test {
    public static void main(String[] args) {
        String body = "123456app_keyk3_testcustomerIdceshi555formatjsonmethodCS-CreateDOsign_methodMD5timestamp2021-09-18 11:27:20v1.0{\r\n" +
                "    \"orderCode\": \"CK202101\",\r\n" +
                "    \"transFlag\": \"1\",\r\n" +
                "    \"serviceLinkman\": \"马宇驰\",\r\n" +
                "    \"servicePhone\": \"（15641805552）\",\r\n" +
                "    \"serviceProvince\": \"北京市\",\r\n" +
                "    \"serviceCity\": \"北京市\",\r\n" +
                "    \"serviceDistrict\": \"丰台区\",\r\n" +
                "    \"serviceAddress\": \"北京市丰台区丰台区晓月苑五里5号楼10层1单元1003\",\r\n" +
                "    \"remark\": \"\",\r\n" +
                "    \"extend1\": \"\",\r\n" +
                "    \"extractionDate\": \"2021-03-23 00:00:00\",\r\n" +
                "    \"items\": [\r\n" +
                "        {\r\n" +
                "            \"materialId\": \"01.01.02\",\r\n" +
                "            \"qty\": \"1.00\",\r\n" +
                "            \"item\": 1,\r\n" +
                "            \"stockId\": \"zf123\"\r\n" +
                "        }\r\n" +
                "    ],\r\n" +
                "    \"partnerId\": \"panasonic\",\r\n" +
                "    \"custID\": \"zhufaner\",\r\n" +
                "    \"orderType\": \"020203\"\r\n" +
                "}123456";
        String md5Code = Md5Utils.getMd5Code(body).toUpperCase();
//        System.out.println(md5Code);
//        System.out.println(md5Code.equals("C83A557C3D40F716761F7A390B2BCE65"));

        String a = "123456app_keyk3_testcustomerIdceshi555formatjsonmethodCS-CreateDOsign_methodMD5timestamp2021-09-18 11:43:41v1.0\"{    \\\"orderCode\\\": \\\"CK202101\\\",    \\\"transFlag\\\": \\\"1\\\",    \\\"serviceLinkman\\\": \\\"马宇驰\\\",    \\\"servicePhone\\\": \\\"（15641805552）\\\",    \\\"serviceProvince\\\": \\\"北京市\\\",    \\\"serviceCity\\\": \\\"北京市\\\",    \\\"serviceDistrict\\\": \\\"丰台区\\\",    \\\"serviceAddress\\\": \\\"北京市丰台区丰台区晓月苑五里5号楼10层1单元1003\\\",    \\\"remark\\\": \\\"\\\",    \\\"extend1\\\": \\\"\\\",    \\\"extractionDate\\\": \\\"2021-03-23 00:00:00\\\",    \\\"items\\\": [        {            \\\"materialId\\\": \\\"01.01.02\\\",            \\\"qty\\\": \\\"1.00\\\",            \\\"item\\\": 1,            \\\"stockId\\\": \\\"zf123\\\"        }    ],    \\\"partnerId\\\": \\\"panasonic\\\",    \\\"custID\\\": \\\"zhufaner\\\",    \\\"orderType\\\": \\\"020203\\\"}\"123456";
        String a1 = Md5Utils.getMd5Code(a).toUpperCase();
        System.out.println(a1);
        //90556B70A9B71ABA0794A7F177DEA4D8
    }
}
