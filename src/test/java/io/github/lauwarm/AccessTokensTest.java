package io.github.lauwarm;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AccessTokensTest {
    @Test public void testAccessTokensHasOauthConsumerKey() {
    	ConfigFile.readConfigFile();
    	new AccessTokens();
        assertNotNull("AccessTokens should have OAuth_ConsumerKey", AccessTokens.getOauth_consumerKey());
    }
}
