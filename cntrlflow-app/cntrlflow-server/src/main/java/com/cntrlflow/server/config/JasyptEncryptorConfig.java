package com.cntrlflow.server.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cntrlflow.server.utility.Constants;

@Configuration
public class JasyptEncryptorConfig {
	
	private StringEncryptor stringEncryptor;

	@Bean(name = "jasyptStringEncryptor")
    public StringEncryptor passwordEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setAlgorithm(Constants.ENCRYPTION_ALGORITHM);
		config.setPassword(Constants.ENCRYPTION_KEY);
		config.setKeyObtentionIterations(1000);
		config.setPoolSize(1);
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		this.stringEncryptor = encryptor;
		return encryptor;
    }
	
	public StringEncryptor getEncryptor() {
		return stringEncryptor;
	}
}
