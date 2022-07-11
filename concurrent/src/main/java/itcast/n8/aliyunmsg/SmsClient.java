package itcast.n8.aliyunmsg;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;

/**
 * @author xuekg
 * @description
 * @date 2022/7/5 14:02
 **/
public class SmsClient {

    private static final String KEY = "xxx";

    private static final String SECRET = "yyy";

    /**
     * 使用AK&SK初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public static Client createClient() throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(KEY)
                // 您的 AccessKey Secret
                .setAccessKeySecret(SECRET);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
}
