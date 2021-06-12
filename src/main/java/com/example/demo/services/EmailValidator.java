package com.example.demo.services;

import com.example.demo.dto.MailExists;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    //TODO: REFACTOR
    //TODO:TASK3: Generar un servicio que utilice la API de Mailboxlayer y permita para verificar el correo de un usuario, tanto si tiene el formato de correo cómo si tiene un servidor existente asignado al dominio utilizado.

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
