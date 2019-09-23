package com.diviso.graeshoppe.client.SMS;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url="https://api.textlocal.in")
public interface SMSResourceApiClient {

}
