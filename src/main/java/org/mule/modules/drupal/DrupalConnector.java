/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 * <p/>
 * This file was automatically generated by the Mule Development Kit
 * <p/>
 * This file was automatically generated by the Mule Development Kit
 * <p/>
 * This file was automatically generated by the Mule Development Kit
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules.drupal;

import org.mule.api.annotations.ConnectionStrategy;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.*;
import org.mule.modules.drupal.strategy.DrupalConnectorConnectionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Drupal is an open source content management platform powering millions of websites and applications.
 * This connector allow you to integrate with a drupal server running the rest server.
 *
 * @author MuleSoft, Inc.
 */
@Connector(name = "drupal", schemaVersion = "1.0", friendlyName = "Drupal")
public class DrupalConnector {

    @ConnectionStrategy
    private DrupalConnectorConnectionStrategy connectionStrategy;


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
        return connectionStrategy.getClient().readNode(nodeId);
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
        return connectionStrategy.getClient().readComment(commentId);
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
        return connectionStrategy.getClient().readUser(userId);
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
        return connectionStrategy.getClient().readTaxonomyTerm(taxonomyTermId);
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
        return connectionStrategy.getClient().readFile(fileId);
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
        return connectionStrategy.getClient().readTaxonomyVocabulary(vocabularyId);
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
    public Node createNode(@Default("#[payload]") Node node) throws DrupalException {
        return connectionStrategy.getClient().createNode(node);
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
    public Comment createComment(@Default("#[payload]") Comment comment) throws DrupalException {
        return connectionStrategy.getClient().createComment(comment);
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
    public User createUser(@Default("#[payload]") User user) throws DrupalException {
        return connectionStrategy.getClient().createUser(user);
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
    public void createTaxonomyTerm(@Default("#[payload]") TaxonomyTerm taxonomyTerm) throws DrupalException {
        connectionStrategy.getClient().createTaxonomyTerm(taxonomyTerm);
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
    public File createFile(@Default("#[payload]") File file) throws DrupalException {
        return connectionStrategy.getClient().createFile(file);
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
            @Default("#[payload]") TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
        connectionStrategy.getClient().createTaxonomyVocabulary(taxonomyVocabulary);
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
    public User registerUser(@Default("#[payload]") User user) throws DrupalException {
        return connectionStrategy.getClient().registerUser(user);
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
    public Node updateNode(@Default("#[payload]") Node node) throws DrupalException {
        return connectionStrategy.getClient().updateNode(node);
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
    public Integer updateComment(@Default("#[payload]") Comment comment) throws DrupalException {
        return connectionStrategy.getClient().updateComment(comment);
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
    public User updateUser(@Default("#[payload]") User user) throws DrupalException {
        return connectionStrategy.getClient().updateUser(user);
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
    public TaxonomyTerm updateTaxonomyTerm(@Default("#[payload]") TaxonomyTerm taxonomyTerm) throws DrupalException {
        return connectionStrategy.getClient().updateTaxonomyTerm(taxonomyTerm);
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
            @Default("#[payload]") TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
        return connectionStrategy.getClient().updateTaxonomyVocabulary(taxonomyVocabulary);
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
        return connectionStrategy.getClient().deleteNode(nodeId);
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
        return connectionStrategy.getClient().deleteComment(commentId);
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
        return connectionStrategy.getClient().deleteFile(fileId);
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
        connectionStrategy.getClient().deleteTaxonomyVocabulary(taxonomyVocId);
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
        connectionStrategy.getClient().deleteTaxonomyTerm(taxonomyTermId);
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
        return connectionStrategy.getClient().deleteUser(userId);
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
        return connectionStrategy.getClient().countAllComments(nodeId);
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
    public int countNewComments(int nodeId, @Default("0") int since) throws DrupalException {
        return connectionStrategy.getClient().countNewComments(nodeId, since);
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
    public List<Node> indexNodes(List<String> fields, @Default("-1") int startPage,
                                 @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexNodes(fields, startPage, pagesize);
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
    public List<Comment> indexComments(List<String> fields, @Default("-1") int startPage,
                                       @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexComments(fields, startPage, pagesize);
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
    public List<User> indexUsers(List<String> fields, @Default("-1") int startPage,
                                 @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexUsers(fields, startPage, pagesize);
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
                                                 @Default("-1") int startPage, @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexTaxonomyTerms(fields, startPage, pagesize);
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
            List<String> fields, @Default("-1") int startPage, @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexTaxonomyVocabulary(fields, startPage, pagesize);
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
    public List<File> indexFiles(List<String> fields, @Default("-1") int startPage,
                                 @Default("0") int pagesize) throws DrupalException {
        return connectionStrategy.getClient().indexFiles(fields, startPage, pagesize);
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
        return connectionStrategy.getClient().getNodesWithTerm(taxonomyTermId);
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
                                                        @Default("-1") int parent, @Default("0") int maxdepth) throws DrupalException {
        return connectionStrategy.getClient().getTaxonomyVocabularyTree(vocabularyId, parent, maxdepth);
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
        return connectionStrategy.getClient().getCommentsForNode(nodeId);
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
        return connectionStrategy.getClient().getFilesForNode(nodeId);
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
    public void updateCustomFieldForNode(int nodeId, String fieldName, Map<String, String> customProperties) throws DrupalException {
        Node nod = new Node();
        nod.setNid(nodeId);

        CustomField field = new CustomField();
        Map<String, CustomField> map = new HashMap<String, CustomField>();
        List<Map> list = new ArrayList<Map>();


        list.add(customProperties);
        field.setUnd(list);
        map.put(fieldName, field);
        nod.setCustomFields(map);

        connectionStrategy.getClient().updateNode(nod);
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
    public List<File> attachFilesToNode(List<java.io.File> files, int nodeId, String fieldName, @Default("true") boolean attach) throws DrupalException {
        return connectionStrategy.getClient().attachFilesToNode(files, nodeId, fieldName, attach);
    }

    public DrupalConnectorConnectionStrategy getConnectionStrategy() {
        return connectionStrategy;
    }

    public void setConnectionStrategy(DrupalConnectorConnectionStrategy connectionStrategy) {
        this.connectionStrategy = connectionStrategy;
    }
}
