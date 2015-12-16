package org.mule.modules.drupal.strategy;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.*;
import org.mule.api.annotations.components.ConnectionManagement;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.modules.drupal.DrupalCollection;
import org.mule.modules.drupal.client.DrupalClient;
import org.mule.modules.drupal.client.DrupalClientFactory;

@ConnectionManagement(configElementName = "config-type",
        friendlyName = "Basic Auth type strategy")
public class DrupalConnectorConnectionStrategy {

    /**
     * Instance of a {@link org.mule.modules.drupal.client.DrupalRestClient}
     */
    protected DrupalClient client;

    /**
     * Uri of the server
     */
    @Configurable
    @Default(value = "localhost")
    private String server;

    /**
     * Path to the REST api
     */
    @Configurable
    @Default(value = "/rest/api")
    private String apiUrl;

    /**
     * Port of the connecion
     */
    @Configurable
    @Default(value = "8888")
    private int port;

    /**
     * Path relative to the REST api url for the User Resource.
     */
    @Configurable
    @Default(value = "user")
    private String userEndpoint;

    /**
     * Path relative to the REST api url for the Node Resource.
     */
    @Configurable
    @Default(value = "node")
    private String nodeEndpoint;

    /**
     * Path relative to the REST api url for the Comment Resource.
     */
    @Configurable
    @Default(value = "comment")
    private String commentEndpoint;

    /**
     * Path relative to the REST api url for the File Resource.
     */
    @Configurable
    @Default(value = "file")
    private String fileEndpoint;

    /**
     * Path relative to the REST api url for the TaxonomyTerm Resource.
     */
    @Configurable
    @Default(value = "taxonomy_term")
    private String taxonomyTermEndpoint;

    /**
     * Path relative to the REST api url for the TaxonomyVocabulary Resource.
     */
    @Configurable
    @Default(value = "taxonomy_vocabulary")
    private String taxonomyVocabularyEndpoint;

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    @TestConnectivity
    public void connect(@ConnectionKey String username, @Password String password)
            throws ConnectionException {
        DrupalCollection.User.setEndpointName(userEndpoint);
        DrupalCollection.Node.setEndpointName(nodeEndpoint);
        DrupalCollection.Comment.setEndpointName(commentEndpoint);
        DrupalCollection.File.setEndpointName(fileEndpoint);
        DrupalCollection.TaxonomyTerm.setEndpointName(taxonomyTermEndpoint);
        DrupalCollection.TaxonomyVocabulary.setEndpointName(taxonomyVocabularyEndpoint);
        client = DrupalClientFactory.getClient(server, port, apiUrl);
        client.login(username, password);
    }

    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        try {
            client.logout();
        } catch (ConnectionException e) {
            //ignore the exception since we are disconnecting and nothing else will be made
        }
        client = null;
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        return client != null && client.isConnected();
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return client.connectionId();
    }


    public DrupalClient getClient() {
        return client;
    }

    public void setClient(DrupalClient client) {
        this.client = client;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserEndpoint() {
        return userEndpoint;
    }

    public void setUserEndpoint(String userEndpoint) {
        this.userEndpoint = userEndpoint;
    }

    public String getNodeEndpoint() {
        return nodeEndpoint;
    }

    public void setNodeEndpoint(String nodeEndpoint) {
        this.nodeEndpoint = nodeEndpoint;
    }

    public String getCommentEndpoint() {
        return commentEndpoint;
    }

    public void setCommentEndpoint(String commentEndpoint) {
        this.commentEndpoint = commentEndpoint;
    }

    public String getFileEndpoint() {
        return fileEndpoint;
    }

    public void setFileEndpoint(String fileEndpoint) {
        this.fileEndpoint = fileEndpoint;
    }

    public String getTaxonomyTermEndpoint() {
        return taxonomyTermEndpoint;
    }

    public void setTaxonomyTermEndpoint(String taxonomyTermEndpoint) {
        this.taxonomyTermEndpoint = taxonomyTermEndpoint;
    }

    public String getTaxonomyVocabularyEndpoint() {
        return taxonomyVocabularyEndpoint;
    }

    public void setTaxonomyVocabularyEndpoint(String taxonomyVocabularyEndpoint) {
        this.taxonomyVocabularyEndpoint = taxonomyVocabularyEndpoint;
    }
}
