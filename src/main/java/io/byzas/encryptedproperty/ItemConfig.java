package io.byzas.encryptedproperty;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.style.ToStringCreator;

@ConfigurationProperties(prefix = "itemconfig")
public class ItemConfig {

    private String password;
    private String ipassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpassword() {
        return ipassword;
    }

    public void setIpassword(String ipassword) {
        this.ipassword = ipassword;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("password", password)
                .toString();
    }
}
