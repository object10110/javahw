package org.samarskii.formatter;

import org.samarskii.model.Gender;
import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Locale;

public class GenderFormatter implements Formatter<Gender> {
    @Resource
    private MessageSource messageSource;

    @Override
    public Gender parse(String s, Locale locale) throws ParseException {
        return Gender.parse(s);
    }

    @Override
    public String print(Gender gender, Locale locale) {
        return messageSource.getMessage("gender." + gender.name().toLowerCase(), null, "Male", locale);
    }
}
