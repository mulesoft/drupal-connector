
package org.mule.modules.drupal.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.modules.drupal.adapters.DrupalConnectorConnectionIdentifierAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorConnectionFactory implements KeyedPoolableObjectFactory
{

    private static Logger logger = LoggerFactory.getLogger(DrupalConnectorConnectionFactory.class);
    private DrupalConnectorConnectionManager connectionManager;

    public DrupalConnectorConnectionFactory(DrupalConnectorConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Object makeObject(Object key)
        throws Exception
    {
        if (!(key instanceof DrupalConnectorConnectionKey)) {
            throw new RuntimeException("Invalid key type");
        }
        DrupalConnectorConnectionIdentifierAdapter connector = new DrupalConnectorConnectionIdentifierAdapter();
        connector.setServer(connectionManager.getServer());
        connector.setApiUrl(connectionManager.getApiUrl());
        connector.setPort(connectionManager.getPort());
        connector.setNodeEndpoint(connectionManager.getNodeEndpoint());
        connector.setCommentEndpoint(connectionManager.getCommentEndpoint());
        connector.setFileEndpoint(connectionManager.getFileEndpoint());
        connector.setTaxonomyTermEndpoint(connectionManager.getTaxonomyTermEndpoint());
        connector.setTaxonomyVocabularyEndpoint(connectionManager.getTaxonomyVocabularyEndpoint());
        if (connector instanceof Initialisable) {
            ((Initialisable) connector).initialise();
        }
        if (connector instanceof MuleContextAware) {
            ((MuleContextAware) connector).setMuleContext(connectionManager.getMuleContext());
        }
        if (connector instanceof Startable) {
            ((Startable) connector).start();
        }
        return connector;
    }

    public void destroyObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof DrupalConnectorConnectionKey)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof DrupalConnectorConnectionIdentifierAdapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
            ((DrupalConnectorConnectionIdentifierAdapter) obj).disconnect();
        } catch (Exception e) {
            throw e;
        } finally {
            if (((DrupalConnectorConnectionIdentifierAdapter) obj) instanceof Stoppable) {
                ((Stoppable) obj).stop();
            }
            if (((DrupalConnectorConnectionIdentifierAdapter) obj) instanceof Disposable) {
                ((Disposable) obj).dispose();
            }
        }
    }

    public boolean validateObject(Object key, Object obj) {
        if (!(obj instanceof DrupalConnectorConnectionIdentifierAdapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
            return ((DrupalConnectorConnectionIdentifierAdapter) obj).isConnected();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public void activateObject(Object key, Object obj)
        throws Exception
    {
        if (!(key instanceof DrupalConnectorConnectionKey)) {
            throw new RuntimeException("Invalid key type");
        }
        if (!(obj instanceof DrupalConnectorConnectionIdentifierAdapter)) {
            throw new RuntimeException("Invalid connector type");
        }
        try {
            if (!((DrupalConnectorConnectionIdentifierAdapter) obj).isConnected()) {
                ((DrupalConnectorConnectionIdentifierAdapter) obj).connect(((DrupalConnectorConnectionKey) key).getUsername(), ((DrupalConnectorConnectionKey) key).getPassword());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void passivateObject(Object key, Object obj)
        throws Exception
    {
    }

}
