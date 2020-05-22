package com.github.dzieniu.libsysbe.security.token;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_MILLIS = 24*24*60*60*1000;  // <- HOURS <- MINUTES <- SECONDS (24DAYS)
    public static final String SIGNING_KEY = "dzieniu"; // SIGNATURE'S SECRET KEY
    public static final String TOKEN_ISSUER = "com.dzieniu";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
