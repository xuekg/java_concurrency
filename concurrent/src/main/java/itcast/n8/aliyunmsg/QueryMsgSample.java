package itcast.n8.aliyunmsg;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsRequest;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponse;
import com.aliyun.dysmsapi20170525.models.QuerySendDetailsResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import java.util.List;
import java.util.Objects;

/**
 * @author xuekg
 * @description
 * @date 2022/7/5 14:04
 **/
public class QueryMsgSample {

    public static void main(String[] args_) throws Exception {
        Client client = SmsClient.createClient();
        //查询短信发送详情
        QuerySendDetailsRequest querySendDetailsRequest = new QuerySendDetailsRequest()
                .setPageSize(10L)
                .setCurrentPage(1L)
                .setPhoneNumber("xxx")
                .setSendDate("20220705");
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            QuerySendDetailsResponse querySendDetailsResponse = client.querySendDetailsWithOptions(querySendDetailsRequest, runtime);
            QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOs smsSendDetailDTOs = querySendDetailsResponse.getBody().getSmsSendDetailDTOs();
            if(Objects.nonNull(smsSendDetailDTOs)){
                List<QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO> smsSendDetailDTO = smsSendDetailDTOs.getSmsSendDetailDTO();
                for(QuerySendDetailsResponseBody.QuerySendDetailsResponseBodySmsSendDetailDTOsSmsSendDetailDTO smsSendDetailDto : smsSendDetailDTO){
                    System.out.println(smsSendDetailDto.getPhoneNum()+"--接收到短信为："+smsSendDetailDto.getContent());
                }
            }
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
