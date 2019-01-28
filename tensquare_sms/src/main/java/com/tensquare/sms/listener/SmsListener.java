package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Value("${aliyun.sms.template_code}")
    private String templateCode;
    @Value("${aliyun.sms.sign_name}")
    private String singName;

    /**
     * 调用工具类发送短信
     * @param massage
     */
    @RabbitHandler
    public void sendSms(Map<String, String> massage){
        SmsUtil smsUtil = new SmsUtil();
        try {
            smsUtil.sendSms(massage.get("mobileNum"), templateCode, singName, massage.get("smsCode"));
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
