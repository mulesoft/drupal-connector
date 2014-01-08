/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.client;

import java.util.List;

import org.mule.api.ConnectionException;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.User;

/**
 * Interface of a Drupal Client to interact with the server.
 * @author pablocabrera
 *
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
	public TaxonomyTerm	readTaxonomyTerm(String taxonomyTermId) throws DrupalException;
	public File	readFile(String fileId) throws DrupalException;
	public TaxonomyVocabulary readTaxonomyVocabulary(String taxonomyVocabularyId) throws DrupalException;
	
	//Create
	public Node createNode(Node node) throws DrupalException;
	public Comment createComment(Comment comment) throws DrupalException;
	public User createUser(User user) throws DrupalException;
	public TaxonomyTerm	createTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException;
	public File	createFile(File file) throws DrupalException;
	public void createTaxonomyVocabulary(TaxonomyVocabulary taxonomyVocabulary) throws DrupalException;

	public User registerUser(User user) throws DrupalException;
	
	public List<File> attachFilesToNode(List<java.io.File> files, int nodeId, String fieldName, boolean attach) throws DrupalException;

	//update
	public Node updateNode(Node node) throws DrupalException;
	public Comment updateComment(Comment comment) throws DrupalException;
	public User updateUser(User user) throws DrupalException;
	public TaxonomyTerm	updateTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException;
	public TaxonomyVocabulary updateTaxonomyVocabulary(TaxonomyVocabulary taxonomyVocabulary) throws DrupalException;
	
	//delete
	public void deleteNode(int nodeId) throws DrupalException;
	public void deleteComment(int commentId) throws DrupalException;
	public void deleteFile(int fileId) throws DrupalException;
	public void deleteTaxonomyVocabulary(int taxonomyVocId) throws DrupalException;
	public void deleteTaxonomyTerm(int taxonomyTermId) throws DrupalException;
	public void deleteUser(int userId) throws DrupalException;
	
	public int countAllComments(int nodeId) throws DrupalException;
	int countNewComments(int nodeId,int since) throws DrupalException;
	
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
	List<TaxonomyTerm> getTaxonomyVocabularyTree(int vocabularyId,int parent, int maxdepth) throws DrupalException;
	
	List<Comment> getCommentsForNode(int nodeId) throws DrupalException;
	List<File> getFilesForNode(int nodeId) throws DrupalException;
}
