/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules.drupal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.api.ConnectionException;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.drupal.client.DrupalClient;
import org.mule.modules.drupal.client.DrupalClientFactory;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.User;

/**
 * Drupal is an open source content management platform powering millions of websites and applications. 
 * This connector allow you to integrate with a drupal server running the rest server.
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="drupal", schemaVersion="1.0", friendlyName = "Drupal", minMuleVersion = "3.4" )
public class DrupalConnector
{
	/**
	 * Instance of a {@link org.mule.modules.drupal.client.DrupalRestClient}
	 */
	protected DrupalClient client;

	/**
	 * Uri of the server
	 */
	@Configurable
	@Optional
	@Default(value="localhost")
	private String server;
	
	/**
	 * Path to the REST api
	 */
	@Configurable
	@Optional
	@Default(value="/rest/api")
	private String apiUrl;
	
	/**
	 * Port of the connecion
	 */
	@Configurable
	@Optional
	@Default(value="8888")
	private int port;

	/**
	 * Path relative to the REST api url for the User Resource.
	 */
	@Configurable
	@Optional
	@Default(value="user")
	private String userEndpoint;

	/**
	 * Path relative to the REST api url for the Node Resource.
	 */
	@Configurable
	@Optional
	@Default(value="node")
	private String nodeEndpoint;

	/**
	 * Path relative to the REST api url for the Comment Resource.
	 */
	@Configurable
	@Optional
	@Default(value="comment")
	private String commentEndpoint;
	
	/**
	 * Path relative to the REST api url for the File Resource.
	 */
	@Configurable
	@Optional
	@Default(value="file")
	private String fileEndpoint;
	
	/**
	 * Path relative to the REST api url for the TaxonomyTerm Resource.
	 */
	@Configurable
	@Optional
	@Default(value="taxonomy_term")
	private String taxonomyTermEndpoint;
	
	/**
	 * Path relative to the REST api url for the TaxonomyVocabulary Resource.
	 */
	@Configurable
	@Optional
	@Default(value="taxonomy_vocabulary")
	private String taxonomyVocabularyEndpoint;

    /**
     * Connect
     *
     * @param username A username
     * @param password A password
     * @throws ConnectionException
     */
    @Connect
    public void connect(@ConnectionKey String username,@Password String password)
        throws ConnectionException {
    	DrupalCollection.User.setEndpointName(userEndpoint);
        DrupalCollection.Node.setEndpointName(nodeEndpoint);
        DrupalCollection.Comment.setEndpointName(commentEndpoint);
        DrupalCollection.File.setEndpointName(fileEndpoint);
        DrupalCollection.TaxonomyTerm.setEndpointName(taxonomyTermEndpoint);
        DrupalCollection.TaxonomyVocabulary.setEndpointName(taxonomyVocabularyEndpoint);
        client = DrupalClientFactory.getClient(server,port,apiUrl);
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
        return client !=null && client.isConnected();
    }

    /**
     * Are we connected
     */
    @ConnectionIdentifier
    public String connectionId() {
        return client.connectionId();
    }

    /**
     * Read a node. See {@link org.mule.modules.drupal.model.Node}.
     * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-node}
     * 
     * @param nodeId Number of node
     * @return Node with the id specified
     * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
     */
	@Processor
	public Node readNode(String nodeId) throws DrupalException {
		return client.readNode(nodeId);
	}

	/**
	 * Read a comment. See {@link org.mule.modules.drupal.model.Comment}
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-comment}
     * 
	 * @param commentId Number of comment
	 * @return Comment with the id specified
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public Comment readComment(String commentId) throws DrupalException {
		return client.readComment(commentId);
	}

	/**
	 * Read an user. See {@link org.mule.modules.drupal.model.User}
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-user}
     * 
	 * @param userId Number of user
	 * @return User with the id specified
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public User readUser(String userId) throws DrupalException {
		return client.readUser(userId);
	}

	/**
	 * Read a taxonomy term. See {@link org.mule.modules.drupal.model.TaxonomyTerm}
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-taxonomy-term}
     * 
	 * @param taxonomyTermId Number of the term
	 * @return Taxonomy term with the id specified
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public TaxonomyTerm readTaxonomyTerm(String taxonomyTermId) throws DrupalException {
		return client.readTaxonomyTerm(taxonomyTermId);
	}

	/**
	 * Read a file. See {@link org.mule.modules.drupal.model.File}
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-file}
     * 
	 * @param fileId Number of file
	 * @return File with the id specified
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public File readFile(String fileId) throws DrupalException {
		return client.readFile(fileId);
	}

	/**
	 * Read a taxonomy vocabulary.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:read-taxonomy-vocabulary}
     * 
	 * @param vocabularyId Number of the vocabulary
	 * @return Taxonomy vocabulary with the id specified
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public TaxonomyVocabulary readTaxonomyVocabulary(String vocabularyId) throws DrupalException {
		return client.readTaxonomyVocabulary(vocabularyId);
	}

	/**
	 * Creates a node.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-node}
     * 
	 * @param node Node. The minimum required fields that need to be set are the Type and Title. See {@link org.mule.modules.drupal.model.Node}
	 * @return The node with the Id set by the server
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public Node createNode(@Optional @Default("#[payload]") Node node) throws DrupalException {
		return client.createNode(node);
	}

	/**
	 * Create a comment.<p/> 
	 * The comment needs to have the node id set. If the comment is related to another comment in the same node, you need to specify the comment id by setting the pid. See {@link org.mule.modules.drupal.model.Comment}.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-comment}
     * 
	 * @param comment Comment with a subject, body and node id
	 * @return The comment with the Id set by the server
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public Comment createComment(@Optional @Default("#[payload]") Comment comment) throws DrupalException {
		return client.createComment(comment);
	}

	/**
	 * Create a User
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-user}
     * 
	 * @param user User with at least Name, mail and password set.
	 * @return The user with the Id set by the server
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public User createUser(@Optional @Default("#[payload]") User user) throws DrupalException {
		return client.createUser(user);
	}

	/**
	 * Create a taxonomy term
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-taxonomy-term}
     * 
	 * @param taxonomyTerm TaxonomyTerm
	 * @return the same TaxonomyTerm created
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public void createTaxonomyTerm(@Optional @Default("#[payload]") TaxonomyTerm taxonomyTerm) throws DrupalException {
		client.createTaxonomyTerm(taxonomyTerm);
	}

	/**
	 * Create a file
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-file}
     * 
	 * @param file File that has the content encoded in Base64 and the name of the file.
	 * @return the file with the Id set.
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public File createFile(@Optional @Default("#[payload]") File file) throws DrupalException {
		return client.createFile(file);
	}

	/**
	 * Create a taxonomyVocabulary
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:create-taxonomy-vocabulary}
     * 
	 * @param taxonomyVocabulary TaxonomyVocabulary
	 * @return the TaxonomyVocabulary.
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned. Error 500 is thrown when the vocabulary already exists 
	 */
	@Processor
	public void createTaxonomyVocabulary(
			@Optional @Default("#[payload]") TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
		client.createTaxonomyVocabulary(taxonomyVocabulary);
	}

	/**
	 * Register a user
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:register-user}
     * 
	 * @param user User with at least Name, mail and password set.
	 * @return The user created
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public User registerUser(@Optional @Default("#[payload]") User user) throws DrupalException {
		return client.registerUser(user);
	}

	/**
	 * Update the node
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-node}
     * 
	 * @param node Node
	 * @return the same node
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public Node updateNode(@Optional @Default("#[payload]") Node node) throws DrupalException {
		return client.updateNode(node);
	}

	/**
	 * Update the comment
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-comment}
     * 
	 * @param comment Comment with new fields set
	 * @return the ID of the comment which was just updated
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public Integer updateComment(@Optional @Default("#[payload]") Comment comment) throws DrupalException {
		return client.updateComment(comment);
	}

	/**
	 * Update the user
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-user}
     * 
	 * @param user User with new fields set
	 * @return the same user
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public User updateUser(@Optional @Default("#[payload]") User user) throws DrupalException {
		return client.updateUser(user);
	}

	/**
	 * Update the taxonomy term
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-taxonomy-term}
     * 
	 * @param taxonomyTerm TaxonomyTerm with new fields set
	 * @return the same taxonomyTerm
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public TaxonomyTerm updateTaxonomyTerm(@Optional @Default("#[payload]") TaxonomyTerm taxonomyTerm) throws DrupalException {
		return client.updateTaxonomyTerm(taxonomyTerm);
	}

	/**
	 * Update the taxonomyVocabulary
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-taxonomy-vocabulary}
     * 
	 * @param taxonomyVocabulary Taxonomy vocabulary with new fields set
	 * @return The same taxonomyVocabulary
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 */
	@Processor
	public TaxonomyVocabulary updateTaxonomyVocabulary(
			@Optional @Default("#[payload]")TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
		return client.updateTaxonomyVocabulary(taxonomyVocabulary);
	}

	/**
	 * Delete a node
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-node}
     * 
	 * @param nodeId Id of the node
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned 
	 * @return Returns a boolean result indicating the success or failure of the API request
	 */
	@Processor
	public boolean deleteNode(int nodeId) throws DrupalException {
		return client.deleteNode(nodeId);
	}

	/**
	 * Delete a comment
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-comment}
     * 
	 * @param commentId Id of the comment
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 * @return Returns a boolean result indicating the success or failure of the API request
	 */
	@Processor
	public boolean deleteComment(int commentId) throws DrupalException {
		return client.deleteComment(commentId);
	}
	/**
	 * Delete a file. If the file is associated to any content, then it can not be deleted.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-file}
     * 
	 * @param fileId Id of the file
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 * @return Returns a boolean result indicating the success or failure of the API request
	 */
	@Processor
	public boolean deleteFile(int fileId) throws DrupalException {
		return client.deleteFile(fileId);
	}

	/**
	 * Delete a Vocabulary
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-taxonomy-vocabulary}
     * 
	 * @param taxonomyVocId Id of the vocabulary
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public void deleteTaxonomyVocabulary(int taxonomyVocId) throws DrupalException {
		client.deleteTaxonomyVocabulary(taxonomyVocId);
	}
	
	/**
	 * Delete a Term
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-taxonomy-term}
     * 
	 * @param taxonomyTermId Id of the term
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public void deleteTaxonomyTerm(int taxonomyTermId) throws DrupalException {
		client.deleteTaxonomyTerm(taxonomyTermId);
	}

	/**
	 * Delete an User
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:delete-user}
     * 
	 * @param userId Id of the User
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 * @return Returns a boolean result indicating the success or failure of the API request
	 */
	@Processor
	public boolean deleteUser(int userId) throws DrupalException {
		return client.deleteUser(userId);
	}
	/**
	 * Count all comments in a node
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:count-all-comments}
     * 
	 * @param nodeId Node Id
	 * @return Amount of comments on the given node
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public int countAllComments(int nodeId) throws DrupalException {
		return client.countAllComments(nodeId);
	}
	
	/**
	 * Counts all new comments in a node. 
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:count-new-comments}
     * 
	 * @param nodeId Node Id
	 * @param since Unix like date format. Default value is 0.
	 * @return Amount of new comments
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public int countNewComments(int nodeId,@Optional @Default("0") int since) throws DrupalException {
		return client.countNewComments(nodeId, since);
	}
	
	/**
	 * Get a list of Nodes with only the fields specified in the list of fields and the uri field for the node.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-nodes}
     * 
	 * @param fields List of fields of the node that we want to retrieve.See {@link org.mule.modules.drupal.model.Node}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of nodes with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<Node> indexNodes(List<String> fields, @Optional @Default("-1") int startPage,
			@Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexNodes(fields, startPage, pagesize);
	}
	
	/**
	 * Get a list of Comments with only the fields specified in the list of fields and the uri field for the comment.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-comments}
     * 
	 * @param fields List of fields of the comment that we want to retrieve. See {@link org.mule.modules.drupal.model.Comment}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of comments with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<Comment> indexComments(List<String> fields, @Optional @Default("-1") int startPage,
			@Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexComments(fields, startPage, pagesize);
	}

	/**
	 * Get a list of Users with only the fields specified in the list of fields and the uri field for the user.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-users}
     * 
	 * @param fields List of fields of the user that we want to retrieve. See {@link org.mule.modules.drupal.model.User}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of users with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<User> indexUsers(List<String> fields, @Optional @Default("-1") int startPage,
			@Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexUsers(fields, startPage, pagesize);
	}
	
	/**
	 * Get a list of taxonomy terms with only the fields specified in the list of fields and the uri field for the term.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-taxonomy-terms}
     * 
	 * @param fields List of fields of the TaxonomyTerm that we want to retrieve. See {@link org.mule.modules.drupal.model.TaxonomyTerm}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of terms with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<TaxonomyTerm> indexTaxonomyTerms(List<String> fields,
			@Optional @Default("-1") int startPage, @Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexTaxonomyTerms(fields, startPage, pagesize);
	}
	
	/**
	 * Get a list of taxonomy vocabulary with only the fields specified in the list of fields and the uri field for the vocabulary.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-taxonomy-vocabulary}
     * 
	 * @param fields List of fields of the TaxonomyVocabulary that we want to retrieve. See {@link org.mule.modules.drupal.model.TaxonomyVocabulary}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of vocabularies with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<TaxonomyVocabulary> indexTaxonomyVocabulary(
			List<String> fields, @Optional @Default("-1") int startPage, @Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexTaxonomyVocabulary(fields, startPage, pagesize);
	}
	
	/**
	 * Get a list of files with only the fields specified in the list of fields and the uri field for the File.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:index-files}
     * 
	 * @param fields List of fields of the File that we want to retrieve. See {@link org.mule.modules.drupal.model.File}
	 * @param startPage The start page of the result list. Default value is -1. In this case, parameter won't be used in the request
	 * @param pagesize The maximum amount of results per page. Default value is 0.In this case, parameter won't be used in the request 
	 * @return List of files with the required fields and the uri
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<File> indexFiles(List<String> fields, @Optional @Default("-1") int startPage,
			@Optional @Default("0") int pagesize) throws DrupalException {
		return client.indexFiles(fields, startPage, pagesize);
	}

	/**
	 * Get a list of Nodes related to the term
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:get-nodes-with-term}
     * 
	 * @param taxonomyTermId The term id of the tag we want the nodes to be associated with
	 * @return List of nodes that have the term.
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<Node> getNodesWithTerm(int taxonomyTermId) throws DrupalException {
		return client.getNodesWithTerm(taxonomyTermId);
	}

	/**
	 * The the list of terms that match the requirements of the request.
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:get-taxonomy-vocabulary-tree}
     * 
	 * @param vocabularyId Id of the TaxonomyVocabulary we want to get
	 * @param parent The TaxonomyTerm id we will use to filter the results. Defatuls to -1. In this case the parameter willl be ignored
	 * @param maxdepth Max depth value of the terms tree we want to retrieve. It is an exclusive limit. Only terms with lower depth value will be retrieved. Default value is 0. In this case parameter will be ignored
	 * @return A list of terms. The tree is actually the relationship that exists between the TaxonomyTerm parent field and the tid field. See {@link org.mule.modules.drupal.model.TaxonomyTerm}
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<TaxonomyTerm> getTaxonomyVocabularyTree(int vocabularyId,
			@Optional @Default("-1") int parent,@Optional @Default("0") int maxdepth) throws DrupalException {
		return client.getTaxonomyVocabularyTree(vocabularyId, parent, maxdepth);
	}

	/**
	 * Get the list of comments of a node
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:get-comments-for-node}
     * 
	 * @param nodeId The id of the node
	 * @return List of Comment
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<Comment> getCommentsForNode(int nodeId) throws DrupalException {
		return client.getCommentsForNode(nodeId);
	}

	/**
	 * Get the list of files attached of a node
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:get-files-for-node}
     * 
	 * @param nodeId the node Id
	 * @return List of Files
	 * @throws DrupalException When the server doesn't return code 200, it contains the code returned
	 */
	@Processor
	public List<File> getFilesForNode(int nodeId) throws DrupalException {
		return client.getFilesForNode(nodeId);
	}
	
	/**
	 * Update one custom field with the properties set at the map
	 * <p/>
     * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:update-custom-field-for-node}
     *  
	 * @param nodeId Id of the node
	 * @param fieldName Machine name of the field
	 * @param customProperties Map that contains the pair of key and values
	 * @throws DrupalException When the server doesn't return code 200
	 */
	@Processor
	public void updateCustomFieldForNode(int nodeId,String fieldName,Map<String,String> customProperties) throws DrupalException{
		Node nod=new Node();
		nod.setNid(nodeId);

		CustomField field=new CustomField();
		Map<String,CustomField> map=new HashMap<String,CustomField>();
		List<Map> list = new ArrayList<Map>();
		
		
		list.add(customProperties);
		field.setUnd(list);
		map.put(fieldName, field);
		nod.setCustomFields(map);
		
		client.updateNode(nod);
	}
	
	/**
	 * Attaches or overwrites file(s) to an existing node.
	 * {@sample.xml ../../../doc/mule-module-drupal.xml.sample drupal:attach-files-to-node}
	 *  
	 * @param files The list of files that you want to upload. These should be java File objects that point 
	 * to files on your file system.
	 * @param nodeId  Node ID of the node the file(s) is being attached to.
	 * @param fieldName Machine name of the field that is attached to the node.
	 * @param attach Optional. Defaults to true. This means that files will be attached to the
	 * node, alongside existing files. If the maximum number of files have already 
	 * been uploaded to this node an error is given.
	 * If false, it removes the files, and attaches the new files uploaded.
	 * @return An list of File with only the "fid" and "url" attributes set.
	 * @throws DrupalException When the server doesn't return code 200
	 */
	@Processor
	public List<File> attachFilesToNode(List<java.io.File> files, int nodeId, String fieldName, @Optional @Default("true") boolean attach) throws DrupalException {
		return client.attachFilesToNode(files, nodeId, fieldName, attach);
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

	public String getUserEndpoint() {
		return userEndpoint;
	}

	public void setUserEndpoint(String userEndpoint) {
		this.userEndpoint = userEndpoint;
	}
	
}
