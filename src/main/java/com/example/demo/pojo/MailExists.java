package com.example.demo.pojo;

public class MailExists {
    private boolean smtp_check;

    public boolean isSmtp_check() {
        return smtp_check;
    }

    public void setSmtp_check(boolean smtp_check) {
        this.smtp_check = smtp_check;
    }

    @Override
    public String toString() {
        return "MailExists{" +
                "smtp_check=" + smtp_check +
                '}';
    }
}
