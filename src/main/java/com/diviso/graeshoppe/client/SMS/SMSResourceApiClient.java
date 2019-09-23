package com.diviso.graeshoppe.client.SMS;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SMSResource", url= "${sms.url}")
public interface SMSResourceApiClient extends SMSResourceApi{

}
