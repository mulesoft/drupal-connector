
package org.mule.modules.drupal.adapters;

import javax.annotation.Generated;
import org.mule.api.Connection;
import org.mule.modules.drupal.DrupalConnector;


/**
 * A <code>DrupalConnectorConnectionIdentifierAdapter</code> is a wrapper around {@link DrupalConnector } that implements {@link org.mule.api.Connection} interface.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorConnectionIdentifierAdapter
    extends DrupalConnectorProcessAdapter
    implements Connection
{


    public String getConnectionIdentifier() {
        return super.connectionId();
    }

}
