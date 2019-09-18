package io.byzas.encryptedproperty;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEncryptableProperties
@EnableConfigurationProperties(ItemConfig.class)
public class SpringbootJaspytEncryptedPropertiesApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJaspytEncryptedPropertiesApplication.class, args);
    }

    @Bean(name = "encryptorBean")
    static public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("12345");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.NoIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Override
    public void run(String... args) throws Exception {
        ItemConfig itemConfig = context.getBean(ItemConfig.class);
        PropertyService service = context.getBean(PropertyService.class);

        LoggerFactory.getLogger(getClass()).info("ItemConfig string password: {}", itemConfig.getPassword());
        LoggerFactory.getLogger(getClass()).info("ItemConfig integer password: {}", itemConfig.getIpassword());
        LoggerFactory.getLogger(getClass()).info("Propertservice string password: {}", service.getPass());
        LoggerFactory.getLogger(getClass()).info("Propertservice integer password: {}", service.getIpass());
    }
}

