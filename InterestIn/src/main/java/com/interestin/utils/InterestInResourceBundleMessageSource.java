package com.interestin.utils;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.*;

/**
 * Created by Danylo on 15.02.14.
 */
public class InterestInResourceBundleMessageSource extends ResourceBundleMessageSource {
    public Map<String, String> getAllMessages(Locale locale) {
        setDefaultEncoding("UTF-8");
        Map<String, String> messages = new HashMap<>();
        ResourceBundle resourceBundle = getResourceBundle("messages", locale);
        Set<String> keys = resourceBundle.keySet();
        for (String key : keys) {
            if (key.indexOf("###") < 0) {
                messages.put(key, resourceBundle.getString(key));
            }
        }
        return messages;
    }
}
