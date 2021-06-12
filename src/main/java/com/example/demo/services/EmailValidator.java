package com.example.demo.services;

import com.example.demo.pojo.MailExists;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        String apikey = "7a18afe42da879a7aa47929cff78898f";
        final String url = "https://apilayer.net/api/check?access_key=" + apikey + "&email=" + email + "&smtp=1&format=1";

        RestTemplate restTemplate = new RestTemplate();
        MailExists mailExists = restTemplate
                .getForObject(url, MailExists.class);

        assert mailExists != null;
        return mailExists.isSmtp_check();
    }

}
