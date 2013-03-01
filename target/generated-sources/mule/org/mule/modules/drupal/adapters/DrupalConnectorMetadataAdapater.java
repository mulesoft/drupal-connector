
package org.mule.modules.drupal.adapters;

import javax.annotation.Generated;
import org.mule.api.MetadataAware;
import org.mule.modules.drupal.DrupalConnector;


/**
 * A <code>DrupalConnectorMetadataAdapater</code> is a wrapper around {@link DrupalConnector } that adds support for querying metadata about the extension.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorMetadataAdapater
    extends DrupalConnectorCapabilitiesAdapter
    implements MetadataAware
{

    private final static String MODULE_NAME = "drupal";
    private final static String MODULE_VERSION = "1.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.4.0-RC1";
    private final static String DEVKIT_BUILD = "master.1476.47e461c";

    public String getModuleName() {
        return MODULE_NAME;
    }

    public String getModuleVersion() {
        return MODULE_VERSION;
    }

    public String getDevkitVersion() {
        return DEVKIT_VERSION;
    }

    public String getDevkitBuild() {
        return DEVKIT_BUILD;
    }

}
