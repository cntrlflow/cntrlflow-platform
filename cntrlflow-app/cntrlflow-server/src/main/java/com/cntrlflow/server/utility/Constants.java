package com.cntrlflow.server.utility;

public class Constants {
    public final static String JWT_SECRET = "cntrlflow";
    public final static int JWT_EXPIRY_MS = 5000000;
    public final static String JWT_NAME = "JWT";
    public final static String COOKIE_PATH = "/";
    public final static String ENCRYPTION_ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";
    public final static String ENCRYPTION_KEY = "cntrlflow";

    //Configurations
    public final static String CONFIG_FILE_PATH = "config.file.path";
    public final static String CONFIG_IMPORT = "spring.config.import";
    
    //System property
    public final static String TRUSTSTORE_LOCATION = "javax.net.ssl.trustStore";
    public final static String TRUSTSTORE_PASSWORD = "javax.net.ssl.trustStorePassword";
}
