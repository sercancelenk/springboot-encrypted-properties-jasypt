# SpringBoot Encrypted Properties with Jasypt

##Steps:
1. Add @EnableEncryptableProperties to any configuration class.
2. Define below properties in application.yml/properties 
<pre>
jasypt.encryptor.password= ${JASYPT_ENCRYPTOR_PASSWORD:12345}
OR in your code, use System.setProperty()
</pre>
3. if you use string password, follow these instructions:
    1. Set this property -> jasypt.encryptor.bean= encryptorBean
    2. And define below bean:
        <pre>
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
        </pre>
       



