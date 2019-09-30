package com.diviso.graeshoppe.client.SMS;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SMSResource", url= "${smsgateway.url}")
public interface SMSResourceApiClient extends SMSResourceApi{

}
