package com.interestin.service;

import java.util.Locale;

/**
 * Created by dbatuik on 31.01.14.
 */
public interface MailService {
    void sendUserActivationMail(String mailTo, String activationUrl, Locale locale);
}
