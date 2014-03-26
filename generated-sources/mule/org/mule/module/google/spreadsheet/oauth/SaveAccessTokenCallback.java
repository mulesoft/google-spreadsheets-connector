
package org.mule.module.google.spreadsheet.oauth;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.4.3", date = "2014-03-26T12:36:12-05:00", comments = "Build 3.4.3.1620.30ea288")
public interface SaveAccessTokenCallback {

        /**
     * Save access token and secret
     *
     * @param accessToken       Access token to be saved
     * @param accessTokenSecret Access token secret to be saved
     */
    void saveAccessToken(String accessToken, String accessTokenSecret);
}
