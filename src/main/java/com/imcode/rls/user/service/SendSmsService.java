package com.imcode.rls.user.service;

import java.util.Map;

public interface SendSmsService {
    boolean sendSms(String phome, String template, Map<String,Object> code);
}
