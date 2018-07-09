package io.github.lauwarm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author fabian
 *
 */
public class ConfigFile {
	
	private static Properties properties = new Properties();


	public ConfigFile() {
		// TODO Auto-generated constructor stub
	}

	public static Properties getProperties() {
		return properties;
	}
	
	public static void readConfigFile() {
		InputStream input = null;
		
		try {
			input = new FileInputStream("./twitter4j.properties");
			properties.load(input);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	public static void writeConfigFile() {
		OutputStream output = null;
		
		try {
			output = new FileOutputStream("./twitter4j.properties");
			
			properties.setProperty("timestamp", String.valueOf(TimeStamps.getTimestampOld()));
			properties.setProperty("oauth.consumerKey", AccessTokens.getOauth_consumerKey());
			properties.setProperty("oauth.consumerSecret", AccessTokens.getOauth_consumerSecret());
			properties.setProperty("oauth.accessToken", AccessTokens.getOauth_accessToken());
			properties.setProperty("oauth.accessTokenSecret", AccessTokens.getOauth_accessTokenSecret());
			
			properties.store(output, null);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

}
