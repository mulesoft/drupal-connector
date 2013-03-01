
package org.mule.modules.drupal.connectivity;

import javax.annotation.Generated;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.mule.api.Capabilities;
import org.mule.api.Capability;
import org.mule.api.ConnectionManager;
import org.mule.api.MetadataAware;
import org.mule.api.MuleContext;
import org.mule.api.config.MuleProperties;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.retry.RetryPolicyTemplate;
import org.mule.config.PoolingProfile;
import org.mule.modules.drupal.DrupalConnector;
import org.mule.modules.drupal.adapters.DrupalConnectorConnectionIdentifierAdapter;


/**
 * A {@code DrupalConnectorConnectionManager} is a wrapper around {@link DrupalConnector } that adds connection management capabilities to the pojo.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class DrupalConnectorConnectionManager implements Capabilities, ConnectionManager<DrupalConnectorConnectionKey, DrupalConnectorConnectionIdentifierAdapter> , MetadataAware, MuleContextAware, Initialisable, ProcessAdapter<DrupalConnectorConnectionIdentifierAdapter>
{

    /**
     * 
     */
    private String username;
    /**
     * 
     */
    private String password;
    private String server;
    private String apiUrl;
    private int port;
    private String nodeEndpoint;
    private String commentEndpoint;
    private String fileEndpoint;
    private String taxonomyTermEndpoint;
    private String taxonomyVocabularyEndpoint;
    /**
     * Mule Context
     * 
     */
    protected MuleContext muleContext;
    /**
     * Flow Construct
     * 
     */
    protected FlowConstruct flowConstruct;
    /**
     * Connector Pool
     * 
     */
    private GenericKeyedObjectPool connectionPool;
    protected PoolingProfile connectionPoolingProfile;
    protected RetryPolicyTemplate retryPolicyTemplate;
    private final static String MODULE_NAME = "drupal";
    private final static String MODULE_VERSION = "1.0-SNAPSHOT";
    private final static String DEVKIT_VERSION = "3.4.0-RC1";
    private final static String DEVKIT_BUILD = "master.1476.47e461c";

    /**
     * Sets server
     * 
     * @param value Value to set
     */
    public void setServer(String value) {
        this.server = value;
    }

    /**
     * Retrieves server
     * 
     */
    public String getServer() {
        return this.server;
    }

    /**
     * Sets apiUrl
     * 
     * @param value Value to set
     */
    public void setApiUrl(String value) {
        this.apiUrl = value;
    }

    /**
     * Retrieves apiUrl
     * 
     */
    public String getApiUrl() {
        return this.apiUrl;
    }

    /**
     * Sets port
     * 
     * @param value Value to set
     */
    public void setPort(int value) {
        this.port = value;
    }

    /**
     * Retrieves port
     * 
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Sets nodeEndpoint
     * 
     * @param value Value to set
     */
    public void setNodeEndpoint(String value) {
        this.nodeEndpoint = value;
    }

    /**
     * Retrieves nodeEndpoint
     * 
     */
    public String getNodeEndpoint() {
        return this.nodeEndpoint;
    }

    /**
     * Sets commentEndpoint
     * 
     * @param value Value to set
     */
    public void setCommentEndpoint(String value) {
        this.commentEndpoint = value;
    }

    /**
     * Retrieves commentEndpoint
     * 
     */
    public String getCommentEndpoint() {
        return this.commentEndpoint;
    }

    /**
     * Sets fileEndpoint
     * 
     * @param value Value to set
     */
    public void setFileEndpoint(String value) {
        this.fileEndpoint = value;
    }

    /**
     * Retrieves fileEndpoint
     * 
     */
    public String getFileEndpoint() {
        return this.fileEndpoint;
    }

    /**
     * Sets taxonomyTermEndpoint
     * 
     * @param value Value to set
     */
    public void setTaxonomyTermEndpoint(String value) {
        this.taxonomyTermEndpoint = value;
    }

    /**
     * Retrieves taxonomyTermEndpoint
     * 
     */
    public String getTaxonomyTermEndpoint() {
        return this.taxonomyTermEndpoint;
    }

    /**
     * Sets taxonomyVocabularyEndpoint
     * 
     * @param value Value to set
     */
    public void setTaxonomyVocabularyEndpoint(String value) {
        this.taxonomyVocabularyEndpoint = value;
    }

    /**
     * Retrieves taxonomyVocabularyEndpoint
     * 
     */
    public String getTaxonomyVocabularyEndpoint() {
        return this.taxonomyVocabularyEndpoint;
    }

    /**
     * Sets muleContext
     * 
     * @param value Value to set
     */
    public void setMuleContext(MuleContext value) {
        this.muleContext = value;
    }

    /**
     * Retrieves muleContext
     * 
     */
    public MuleContext getMuleContext() {
        return this.muleContext;
    }

    /**
     * Sets flowConstruct
     * 
     * @param value Value to set
     */
    public void setFlowConstruct(FlowConstruct value) {
        this.flowConstruct = value;
    }

    /**
     * Retrieves flowConstruct
     * 
     */
    public FlowConstruct getFlowConstruct() {
        return this.flowConstruct;
    }

    /**
     * Sets connectionPoolingProfile
     * 
     * @param value Value to set
     */
    public void setConnectionPoolingProfile(PoolingProfile value) {
        this.connectionPoolingProfile = value;
    }

    /**
     * Retrieves connectionPoolingProfile
     * 
     */
    public PoolingProfile getConnectionPoolingProfile() {
        return this.connectionPoolingProfile;
    }

    /**
     * Sets retryPolicyTemplate
     * 
     * @param value Value to set
     */
    public void setRetryPolicyTemplate(RetryPolicyTemplate value) {
        this.retryPolicyTemplate = value;
    }

    /**
     * Retrieves retryPolicyTemplate
     * 
     */
    public RetryPolicyTemplate getRetryPolicyTemplate() {
        return this.retryPolicyTemplate;
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

    public void initialise() {
        GenericKeyedObjectPool.Config config = new GenericKeyedObjectPool.Config();
        if (connectionPoolingProfile!= null) {
            config.maxIdle = connectionPoolingProfile.getMaxIdle();
            config.maxActive = connectionPoolingProfile.getMaxActive();
            config.maxWait = connectionPoolingProfile.getMaxWait();
            config.whenExhaustedAction = ((byte) connectionPoolingProfile.getExhaustedAction());
            config.timeBetweenEvictionRunsMillis = connectionPoolingProfile.getEvictionCheckIntervalMillis();
            config.minEvictableIdleTimeMillis = connectionPoolingProfile.getMinEvictionMillis();
        }
        connectionPool = new GenericKeyedObjectPool(new DrupalConnectorConnectionFactory(this), config);
        if (retryPolicyTemplate == null) {
            retryPolicyTemplate = muleContext.getRegistry().lookupObject(MuleProperties.OBJECT_DEFAULT_RETRY_POLICY_TEMPLATE);
        }
    }

    public DrupalConnectorConnectionIdentifierAdapter acquireConnection(DrupalConnectorConnectionKey key)
        throws Exception
    {
        return ((DrupalConnectorConnectionIdentifierAdapter) connectionPool.borrowObject(key));
    }

    public void releaseConnection(DrupalConnectorConnectionKey key, DrupalConnectorConnectionIdentifierAdapter connection)
        throws Exception
    {
        connectionPool.returnObject(key, connection);
    }

    public void destroyConnection(DrupalConnectorConnectionKey key, DrupalConnectorConnectionIdentifierAdapter connection)
        throws Exception
    {
        connectionPool.invalidateObject(key, connection);
    }

    /**
     * Returns true if this module implements such capability
     * 
     */
    public boolean isCapableOf(Capability capability) {
        if (capability == Capability.LIFECYCLE_CAPABLE) {
            return true;
        }
        if (capability == Capability.CONNECTION_MANAGEMENT_CAPABLE) {
            return true;
        }
        return false;
    }

    @Override
    public<P >ProcessTemplate<P, DrupalConnectorConnectionIdentifierAdapter> getProcessTemplate() {
        return new ManagedConnectionProcessTemplate(this, muleContext);
    }

    public DrupalConnectorConnectionKey getDefaultConnectionKey() {
        return new DrupalConnectorConnectionKey(getUsername(), getPassword());
    }

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
