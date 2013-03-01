
package org.mule.modules.drupal.connectivity;

import javax.annotation.Generated;


/**
 * A tuple of connection parameters
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorConnectionKey {

    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String password;

    public DrupalConnectorConnectionKey(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets username
     * 
     * @param value Value to set
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Retrieves username
     * 
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public String getPassword() {
        return this.password;
    }

    public int hashCode() {
        int hash = 1;
        hash = ((hash* 31)+ this.username.hashCode());
        return hash;
    }

    public boolean equals(Object obj) {
        return (((obj instanceof DrupalConnectorConnectionKey)&&(this.username!= null))&&this.username.equals(((DrupalConnectorConnectionKey) obj).username));
    }

}
