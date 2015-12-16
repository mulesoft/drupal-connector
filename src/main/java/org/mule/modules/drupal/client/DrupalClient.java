/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.client;

import org.mule.api.ConnectionException;
import org.mule.modules.drupal.model.*;

import java.util.List;

/**
 * Interface of a Drupal Client to interact with the server.
 *
 * @author pablocabrera
 */
public interface DrupalClient {

    public void login(String username, String password) throws ConnectionException;

    public void logout() throws ConnectionException;

    public boolean isConnected();

    public String connectionId();

    //Retrieve data
    public Node readNode(String nodeId) throws DrupalException;

    public Comment readComment(String commentId) throws DrupalException;

    public User readUser(String userId) throws DrupalException;

    public TaxonomyTerm readTaxonomyTerm(String taxonomyTermId) throws DrupalException;

    public File readFile(String fileId) throws DrupalException;

    public TaxonomyVocabulary readTaxonomyVocabulary(String taxonomyVocabularyId) throws DrupalException;

    //Create
    public Node createNode(Node node) throws DrupalException;

    public Comment createComment(Comment comment) throws DrupalException;

    public User createUser(User user) throws DrupalException;

    public void createTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException;

    public File createFile(File file) throws DrupalException;

    public void createTaxonomyVocabulary(TaxonomyVocabulary taxonomyVocabulary) throws DrupalException;

    public User registerUser(User user) throws DrupalException;

    public List<File> attachFilesToNode(List<java.io.File> files, int nodeId, String fieldName, boolean attach) throws DrupalException;

    //update
    public Node updateNode(Node node) throws DrupalException;

    public int updateComment(Comment comment) throws DrupalException;

    public User updateUser(User user) throws DrupalException;

    public TaxonomyTerm updateTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException;

    public TaxonomyVocabulary updateTaxonomyVocabulary(TaxonomyVocabulary taxonomyVocabulary) throws DrupalException;

    //delete
    public boolean deleteNode(int nodeId) throws DrupalException;

    public boolean deleteComment(int commentId) throws DrupalException;

    public boolean deleteFile(int fileId) throws DrupalException;

    public void deleteTaxonomyVocabulary(int taxonomyVocId) throws DrupalException;

    public void deleteTaxonomyTerm(int taxonomyTermId) throws DrupalException;

    public boolean deleteUser(int userId) throws DrupalException;

    public int countAllComments(int nodeId) throws DrupalException;

    int countNewComments(int nodeId, int since) throws DrupalException;

    //Index entities
    List<Node> indexNodes(List<String> fields, int startPage, int pagesize) throws DrupalException;

    List<Comment> indexComments(List<String> fields, int startPage, int pagesize) throws DrupalException;

    List<User> indexUsers(List<String> fields, int startPage, int pagesize) throws DrupalException;

    List<TaxonomyTerm> indexTaxonomyTerms(List<String> fields, int startPage,
                                          int pagesize) throws DrupalException;

    List<TaxonomyVocabulary> indexTaxonomyVocabulary(List<String> fields,
                                                     int startPage, int pagesize) throws DrupalException;

    List<File> indexFiles(List<String> fields,
                          int startPage, int pagesize) throws DrupalException;


    List<Node> getNodesWithTerm(int termId) throws DrupalException;

    List<TaxonomyTerm> getTaxonomyVocabularyTree(int vocabularyId, int parent, int maxdepth) throws DrupalException;

    List<Comment> getCommentsForNode(int nodeId) throws DrupalException;

    List<File> getFilesForNode(int nodeId) throws DrupalException;
}
