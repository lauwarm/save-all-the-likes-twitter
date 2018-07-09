package io.github.lauwarm;

import java.util.Properties;

/**
 * @author fabian
 *
 */
public class AccessTokens {
	
	private static String oauth_consumerKey = null;
	private static String oauth_consumerSecret = null;
	private static String oauth_accessToken = null;
	private static String oauth_accessTokenSecret = null;

	public AccessTokens() {
		Properties properties = ConfigFile.getProperties();
		
		setOauth_consumerKey(properties.getProperty("oauth.consumerKey"));
		setOauth_consumerSecret(properties.getProperty("oauth.consumerSecret"));
		setOauth_accessToken(properties.getProperty("oauth.accessToken"));
		setOauth_accessTokenSecret(properties.getProperty("oauth.accessTokenSecret"));
	}

	public static String getOauth_consumerKey() {
		return oauth_consumerKey;
	}

	public static void setOauth_consumerKey(String oauth_consumerKey) {
		AccessTokens.oauth_consumerKey = oauth_consumerKey;
	}

	public static String getOauth_consumerSecret() {
		return oauth_consumerSecret;
	}

	public static void setOauth_consumerSecret(String oauth_consumerSecret) {
		AccessTokens.oauth_consumerSecret = oauth_consumerSecret;
	}

	public static String getOauth_accessToken() {
		return oauth_accessToken;
	}

	public static void setOauth_accessToken(String oauth_accessToken) {
		AccessTokens.oauth_accessToken = oauth_accessToken;
	}

	public static String getOauth_accessTokenSecret() {
		return oauth_accessTokenSecret;
	}

	public static void setOauth_accessTokenSecret(String oauth_accessTokenSecret) {
		AccessTokens.oauth_accessTokenSecret = oauth_accessTokenSecret;
	}

}
