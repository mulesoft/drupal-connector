
package org.mule.modules.drupal.processors;

import javax.annotation.Generated;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public abstract class AbstractConnectedProcessor
    extends AbstractExpressionEvaluator
{

    protected Object username;
    protected String _usernameType;
    protected Object password;
    protected String _passwordType;

    /**
     * Sets username
     * 
     * @param value Value to set
     */
    public void setUsername(Object value) {
        this.username = value;
    }

    /**
     * Retrieves username
     * 
     */
    public Object getUsername() {
        return this.username;
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(Object value) {
        this.password = value;
    }

    /**
     * Retrieves password
     * 
     */
    public Object getPassword() {
        return this.password;
    }

}
