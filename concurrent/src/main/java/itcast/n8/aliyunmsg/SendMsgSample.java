package itcast.n8.aliyunmsg;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.*;

/**
 * @author xuekg
 * @description
 * @date 2022/7/5 10:23
 **/
public class SendMsgSample {

    public static void main(String[] args_) throws Exception {
        Client client = SmsClient.createClient();
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers("xxx")
                .setTemplateParam("{\"code\":\"1234\"}");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            System.out.println(sendSmsResponse.getBody().getMessage());
        } catch (TeaException error) {
            // 如有需要，请打印 error
            System.out.println(Common.assertAsString(error.message));
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            System.out.println(Common.assertAsString(error.message));
        }
    }
}
