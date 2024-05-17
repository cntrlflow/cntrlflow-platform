package com.cntrlflow.server;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cntrlflow.server.utility.Constants;
import com.cntrlflow.server.utility.CrytoUtil;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j  
@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableEncryptableProperties
public class CntrlflowServer {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CntrlflowServer.class);
		app.setBannerMode(Banner.Mode.OFF);

		// Create options for start, encrypt, and decrypt
		Options options = new Options();
		options.addOption("start", false, "Start the application");
		options.addOption("config", true, "Config file path");
		options.addOption("encrypt", true, "Encrypt a value");
		options.addOption("decrypt", true, "Decrypt a value");

		if (args.length > 0) {

			// Parse the command-line arguments
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd;
			
			try {
				cmd = parser.parse(options, args);
			} catch (ParseException e) {
				System.err.println("Error parsing command-line arguments: " + e.getMessage());
				return;
			}

			if (cmd.hasOption("help")) {
				displayUsage(options);
				return;
			}

			if (cmd.hasOption("start")) {
				if (cmd.hasOption("config")) {
					String configFilePath = cmd.getOptionValue("config");
					log.info("[cntrlflow] configFilePath: " + configFilePath);
					Path path = Paths.get(configFilePath);
					try {
						if (Files.exists(path)) {
							Properties customProperties = getCustomProperties(configFilePath);
					        // Set or change properties at runtime
					        app.setDefaultProperties(customProperties);
							app.run(args);
						} else {
							System.err.println("config file not found");
							displayUsage(options);
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("config file not found");
						displayUsage(options);
						return;
					}
				} else {
					System.err.println("config file not found");
					displayUsage(options);
					return;
				}
			} else {
				CrytoUtil cryptoUtil = new CrytoUtil();
				if (cmd.hasOption("encrypt")) {
					String valueToEncrypt = cmd.getOptionValue("encrypt");
					System.out.println("Encrypted Token: " + cryptoUtil.getEncryptor().encrypt(valueToEncrypt));
				} else if (cmd.hasOption("decrypt")) {
					String valueToDecrypt = cmd.getOptionValue("decrypt");
					System.out.println("Decrypted Token: " + cryptoUtil.getEncryptor().decrypt(valueToDecrypt));
				}
			}
		} else {
			System.err.println("No arguments passed");
			displayUsage(options);
			System.exit(0);
		}
	}

	private static void displayUsage(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("cntrlflow-server", options);
	}
	
	private static Properties getCustomProperties(String filePath) {
        Properties properties = new Properties();
        properties.setProperty(Constants.CONFIG_IMPORT, filePath);
        return properties;
    }
}
