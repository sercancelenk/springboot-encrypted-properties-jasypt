package io.byzas.encryptedproperty;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyService {
    @Value("${itemconfig.password}")
    private String pass;

    @Value("${itemconfig.ipassword}")
    private String ipass;

    public String getPass() {
        return pass;
    }

    public String getIpass() {
        return ipass;
    }

    public void setIpass(String ipass) {
        this.ipass = ipass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
