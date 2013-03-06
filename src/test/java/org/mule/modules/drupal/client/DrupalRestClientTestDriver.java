/**
 * Mule Drupal Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.modules.drupal.client;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.User;


/**
 * This unit test require a lot of configuration. 
 * TODO: Provide scripts to regenerate the database to make this test runnable in a continuous integration environment.
 * @author pablocabrera
 *
 */
public class DrupalRestClientTestDriver {

	private DrupalClient client;
	private Properties properties;
	//Property names
	private static final String USER_ID = "userId";
	private static final String NODE_ID = "nodeId";
	private static final String NODE_ID2 = "nodeId2";
	private static final String COMMENT_ID = "commentId";
	private static final String FILE_ID = "fileId";
	private static final String VOC_ID = "vocId";
	private static final String TERM_ID = "termId";
	
	@Before
	public void setUp() throws Exception {
		
		File file = new File("src/test/resources/drupal.properties");
		FileInputStream fileInput = new FileInputStream(file);
		properties = new Properties();
		properties.load(fileInput);
		fileInput.close();
		
		client = DrupalClientFactory.getClient(properties.getProperty("endpoint"),Integer.valueOf(properties.getProperty("port")),properties.getProperty("apiUrl"));
		client.login(properties.getProperty("adminUsername"),properties.getProperty("adminPassword"));
	}

	@Test
	public void readNode() throws DrupalException {
		//Read node with Id number
		Node node = client.readNode(properties.getProperty(NODE_ID));
		assertEquals("Could not read node",node.getNid(),Integer.valueOf(properties.getProperty(NODE_ID)));
		
		Map mapa =node.getBody().getUnd().get(0);
		String value=(String) mapa.get("value");
		String summary=(String) mapa.get("summary");
		assertNotNull(value);
		assertNotNull(summary);
		
		String term = (String) node.getCustomFields().get("field_tags").getUnd().get(0).get("tid");
		assertNotNull(term);
	}

	@Test
	public void readComment() throws DrupalException {
		//Read node with Id number
		Comment comment = client.readComment(properties.getProperty(COMMENT_ID));
		assertEquals("Could not read node",comment.getCid(),Integer.valueOf(properties.getProperty(COMMENT_ID)));
	}
	
	@Test
	public void readTaxonomyVocabulary() throws DrupalException {
		//Read taxonomy vocabulary with Id number
		TaxonomyVocabulary tvo = client.readTaxonomyVocabulary(properties.getProperty(VOC_ID));
		assertEquals("Could not read Taxonomy Vocabulary",tvo.getVid(),Integer.valueOf(properties.getProperty(VOC_ID)));
	}
	
	@Test
	public void readTaxonomyTerm() throws DrupalException {
		//Read term with Id number
		TaxonomyTerm taxonomyTerm = client.readTaxonomyTerm(properties.getProperty(TERM_ID));
		assertEquals("Could not read node",taxonomyTerm.getTid(),Integer.valueOf(properties.getProperty(TERM_ID)));
	}
	
	@Test
	public void readFile() throws DrupalException {
		//Read file with Id number
		org.mule.modules.drupal.model.File file = client.readFile(properties.getProperty(FILE_ID));
		assertEquals("Could not read File",file.getFid(),Integer.valueOf(properties.getProperty(FILE_ID)));
	}
	
	@Test
	public void readUser() throws DrupalException {
		//Read user with Id number
		User user = client.readUser(properties.getProperty(USER_ID));
		assertEquals("Could not read User",user.getUid(),Integer.valueOf(properties.getProperty(USER_ID)));
	}
	
	@Test
	public void createUser() throws DrupalException {
		User newUser=new User();
		newUser.setName("NewUser");
		newUser.setMail("foo@foo.com");
		newUser.setPassword("dummy00");
		client.createUser(newUser);
		assertNotNull("Could not create user",newUser.getUid());
	}

	@Test
	public void createNode() throws DrupalException {
		Node newNode = new Node();
		newNode.setTitle("Created From Connector");
		newNode.setType("page");
		CustomField map=new CustomField();
		Map fields=new HashMap();
		fields.put("value", "body value");
		List<Map> lm = new ArrayList<Map>();
		lm.add(fields);
		map.setUnd(lm);
		newNode.setBody(map);
		
		map=new CustomField();
		fields=new HashMap();
		fields.put("value", "10.00");
		lm = new ArrayList<Map>();
		lm.add(fields);
		map.setUnd(lm);
		Map customField=new HashMap<String,CustomField>();
		customField.put("field_test", map);
		newNode.setCustomFields(customField);
		client.createNode(newNode);
		assertNotNull("Could not create node",newNode.getNid());
		
		newNode = new Node();
		newNode.setTitle("No Content - Created From Connector");
		newNode.setType("article");
		client.createNode(newNode);
		assertNotNull("Could not create node with no content",newNode.getNid());
		
	}
	
	@Test
	public void createComment() throws DrupalException{
		Comment cc=new Comment();
		cc.setSubject("Comment from connector");
		cc.setNid(1);
		cc.setName("pcabrera");
		cc.setUid(1);
		cc.setPid(0);
		
		CustomField map=new CustomField();
		Map fields=new HashMap();
		fields.put("value", "Comment");
		List<Map> lm = new ArrayList<Map>();
		lm.add(fields);
		map.setUnd(lm);
		
		cc.setCommentBody(map);
		client.createComment(cc);
		assertNotNull(cc.getCid());
	}
	
	@Test
	public void registerUser() throws DrupalException {
		User newUser=new User();
		newUser.setName("NewUser");
		newUser.setMail("foo@foo.com");
		newUser.setPassword("dummy00");
		client.registerUser(newUser);
		assertNotNull("Could not register user",newUser.getUid());
	}
	
	@Test
	public void createTerm() throws DrupalException{
		TaxonomyTerm term=new TaxonomyTerm();
		term.setName("ConnectorTag");
		term.setDescription("Description");
		term.setVid(15);
		client.createTaxonomyTerm(term);
		assertNotNull(term.getName());
	}
	@Test
	public void createTaxonomyVocabulary() throws DrupalException {
		TaxonomyVocabulary newVoc=new TaxonomyVocabulary();
		newVoc.setName("NewVoc19");
		newVoc.setDescription("New taxonomy Vocabulary");
		newVoc.setMachineName("new_voc19");
		client.createTaxonomyVocabulary(newVoc);
		assertNotNull("Could not create user",newVoc.getName());
	}
	
	@Test
	public void testCreateFile() throws DrupalException {
		org.mule.modules.drupal.model.File file = client.readFile(properties.getProperty(FILE_ID));
		file.setFilename("newFile.jpg");
		
		org.mule.modules.drupal.model.File file2=new org.mule.modules.drupal.model.File();
		file2.setFilename("newFile.jpg");
		file2.setContent(file.getContent());
		file2.setUid(file.getUid());
		file2 = client.createFile(file2);
		assertNotNull("Could not create user",file2.getFid());
	}

	@Test
	public void testDeleteUser() throws NumberFormatException, DrupalException {
		//Read file with Id number
		User newUser=new User();
		client.deleteUser(Integer.valueOf(properties.getProperty(USER_ID)));		
		newUser = client.readUser(newUser.getUid().toString());
		assertNull("User was not deleted", newUser);
	}
	
	@Test
	public void testDeleteFile() throws NumberFormatException, DrupalException {
		//Read file with Id number
		org.mule.modules.drupal.model.File file = new org.mule.modules.drupal.model.File();
		client.deleteFile(Integer.valueOf(properties.getProperty(FILE_ID)));
		file = client.readFile(file.getFid().toString());
		assertNull("User was not deleted", file);
	}
	
	@Test
	public void updateNodeTest() throws DrupalException{
		Node node = client.readNode(properties.getProperty(NODE_ID));
		assertEquals("Could not read node",node.getNid(),Integer.valueOf(properties.getProperty(NODE_ID)));
		String oldTittle=node.getTitle();
		String newTittle = "Updated tittle";
		node.setTitle(newTittle);
		client.updateNode(node);
		node = client.readNode(properties.getProperty(NODE_ID));
		assertEquals("Could not update node",newTittle,node.getTitle());
		
		node.setTitle(oldTittle);
		client.updateNode(node);
	}
	
	@Test
	public void updateCommentTest() throws DrupalException{
		Comment comment = client.readComment(properties.getProperty(COMMENT_ID));
		assertEquals("Could not read comment",comment.getNid(),Integer.valueOf(properties.getProperty(COMMENT_ID)));
		String oldTittle=comment.getSubject();
		String newTittle = "Updated Subject";
		comment.setSubject(newTittle);
		client.updateComment(comment);
		comment = client.readComment(properties.getProperty(COMMENT_ID));
		assertEquals("Could not update comment",newTittle,comment.getSubject());
		
		comment.setSubject(oldTittle);
		client.updateComment(comment);
	}
	
	@Test
	public void updateTermTest() throws DrupalException{
		TaxonomyTerm taxonomyTerm = client.readTaxonomyTerm(properties.getProperty(TERM_ID));
		assertEquals("Could not read term",taxonomyTerm.getTid(),Integer.valueOf(properties.getProperty(TERM_ID)));
		String oldName=taxonomyTerm.getName();
		String newName = "Updated Term";
		taxonomyTerm.setName(newName);
		client.updateTaxonomyTerm(taxonomyTerm);
		taxonomyTerm = client.readTaxonomyTerm(properties.getProperty(TERM_ID));
		assertEquals("Could not update term",newName,taxonomyTerm.getName());
		
		taxonomyTerm.setName(oldName);
		client.updateTaxonomyTerm(taxonomyTerm);
	}
	
	@Test
	public void updateVocabularyTest() throws DrupalException{
		TaxonomyVocabulary taxonomyVocabulary = client.readTaxonomyVocabulary(properties.getProperty(VOC_ID));
		assertEquals("Could not read vocabulary",taxonomyVocabulary.getVid(),Integer.valueOf(properties.getProperty(VOC_ID)));
		String oldName=taxonomyVocabulary.getName();
		String newName = "Updated Vocabulary";
		taxonomyVocabulary.setName(newName);
		client.updateTaxonomyVocabulary(taxonomyVocabulary);
		taxonomyVocabulary = client.readTaxonomyVocabulary(properties.getProperty(VOC_ID));
		assertEquals("Could not update vocabulary",newName,taxonomyVocabulary.getName());
		
		taxonomyVocabulary.setName(oldName);
		client.updateTaxonomyVocabulary(taxonomyVocabulary);
	}

	@Test
	public void updateUserTest() throws DrupalException{
		User user = client.readUser(properties.getProperty(USER_ID));
		assertEquals("Could not read user",user.getUid(),Integer.valueOf(properties.getProperty(USER_ID)));
		String oldName=user.getName();
		String newName = "Updated User Name";
		user.setName(newName);
		client.updateUser(user);
		user = client.readUser(properties.getProperty(USER_ID));
		assertEquals("Could not update user",newName,user.getName());		
		user.setName(oldName);
		client.updateUser(user);
	}
	
	@Test
	public void updateFile() throws DrupalException{
		org.mule.modules.drupal.model.File file = client.readFile(properties.getProperty(FILE_ID));
		assertEquals("Could not read file",file.getFid(),Integer.valueOf(properties.getProperty(FILE_ID)));
		Integer oldStatus=file.getStatus();
		Integer newStatus = (oldStatus.intValue() == 0) ? 1 : 0;
		file.setStatus(newStatus);
		client.updateFile(file);
		file = client.readFile(properties.getProperty(FILE_ID));
		assertEquals("Could not update File",newStatus,file.getStatus());		
		file.setStatus(oldStatus);
		client.updateFile(file);
	}
	
	@Test
	public void countAll() throws DrupalException{
		int count = client.countAllComments(Integer.valueOf(properties.getProperty(NODE_ID)));
		assertEquals("Amount of comments didn't match the expected",6,count);
	}
	
	@Test
	public void countNew() throws DrupalException{
		int count = client.countNewComments(Integer.valueOf(properties.getProperty(COMMENT_ID)),1361903400);
		assertEquals("Amount of comments didn't match the expected",1,count);
	}
	
	@Test
	public void indexUserTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		fields.add("uid");
		List<User> users=client.indexUsers(fields, -1, 0);
		assertNotNull(users);
		//Provided allways when you make an index action on a resource
		assertNotNull("Uri should not be null",users.get(0).getUri());
		assertNotNull("uid should not be null",users.get(0).getUid());
		//We didn't ask for name, so it should be empty
		assertNull(users.get(0).getName());
		
		users=client.indexUsers(null, -1, 0);
		assertNotNull(users);
		
		//Some Fields retrieve that should not be null 
		assertNotNull("Name should not be null",users.get(0).getName());
		assertNotNull("uri should not be null",users.get(0).getUri());
		assertNotNull("uid should not be null",users.get(0).getUid());
		assertNotNull("Mail should not be null",users.get(0).getMail());
		assertNotNull("Created should not be null",users.get(0).getCreated());
	}
	
	@Test
	public void indexNodeTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		fields.add("nid");
		fields.add("title");
		List<Node> nodes=client.indexNodes(fields, -1, 0);
		assertNotNull(nodes);
		assertNotNull(nodes.get(0).getTitle());
		
		nodes=client.indexNodes(null, 0, 2);
		assertNotNull("Name should not be null",nodes.get(0).getTitle());
		assertNotNull("uri should not be null",nodes.get(0).getUri());
		assertNotNull("Type should not be null",nodes.get(0).getType());
		assertNotNull("Created should not be null",nodes.get(0).getCreated());
		
		assertEquals("Amount of items are not correct",2,nodes.size());
		
		nodes=client.indexNodes(null, 1, 2);
		assertEquals("Amount of items are not correct",1,nodes.size());
		
		nodes=client.indexNodes(null, 2, 2);
		assertEquals("Amount of items are not correct",0,nodes.size());
		
		nodes=client.indexNodes(null, 0, 6);
		assertEquals("Amount of items are not correct",3,nodes.size());
	}
	
	@Test
	public void indexCommentTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		fields.add("cid");
		List<Comment> comments=client.indexComments(fields, -1, 0);
		assertNotNull(comments);
		assertNotNull(comments.get(0).getCid());
		assertNotNull(comments.get(0).getUri());
		assertNull(comments.get(0).getSubject());
	}
	
	@Test
	public void indexVocabularyTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		List<TaxonomyVocabulary> voc=client.indexTaxonomyVocabulary(fields, 0, 0);
		assertNotNull(voc);
		assertNotNull(voc.get(0).getVid());
		assertNotNull(voc.get(0).getUri());
	}
	
	@Test
	public void indexTermTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		List<TaxonomyTerm> voc=client.indexTaxonomyTerms(fields, 0, 0);
		assertNotNull(voc);
		assertNotNull(voc.get(0).getTid());
		assertNotNull(voc.get(0).getUri());
	}
	
	@Test
	public void indexFileTest() throws DrupalException{
		List<String> fields=new ArrayList<String>();
		List<org.mule.modules.drupal.model.File> voc=client.indexFiles(fields, 0, 0);
		assertNotNull(voc);
		assertNotNull(voc.get(0).getFid());
		assertNotNull(voc.get(0).getUri());
	}
	
	@Test
	public void testGetNodeWithTerm() throws DrupalException{
		List<Node> nodes=client.getNodesWithTerm(Integer.valueOf(properties.getProperty(TERM_ID)));
		assertNotNull("No nodes retrieved",nodes);
		assertNotNull("Couldn't get title",nodes.get(0).getTitle());
	}
	
	@Test
	public void testGetTaxonomyTree() throws DrupalException{
		List<TaxonomyTerm> nodes=client.getTaxonomyVocabularyTree(Integer.valueOf(properties.getProperty(VOC_ID)), -1, 0);
		
		assertNotNull("No nodes retrieved",nodes);
		assertNotNull("Couldn't get Name",nodes.get(0).getName());
		
		assertEquals("Amount of items are not correct",7,nodes.size());
		
		nodes=client.getTaxonomyVocabularyTree(Integer.valueOf(properties.getProperty(VOC_ID)), 7, 0);
		
		assertEquals("Amount of items are not correct wheh parent restricted",1,nodes.size());
		
		nodes=client.getTaxonomyVocabularyTree(Integer.valueOf(properties.getProperty(VOC_ID)), -1, 2);
		
		assertEquals("Amount of items are not correct wheh parent restricted",5,nodes.size());
	}
	
	@Test
	public void testGetComments() throws DrupalException{
		List<Comment> comments=client.getCommentsForNode(Integer.valueOf(properties.getProperty(NODE_ID)));
		assertNotNull(comments);
		assertTrue(comments.isEmpty());
		comments=client.getCommentsForNode(Integer.valueOf(properties.getProperty(NODE_ID2)));
		assertNotNull(comments);
		assertNotNull(comments.get(0).getCid());
		assertNotNull(comments.get(0).getUri());		
	}
	
	@Test
	public void testGetFiles() throws DrupalException{
		List<org.mule.modules.drupal.model.File> voc=client.getFilesForNode(Integer.valueOf(properties.getProperty(NODE_ID2)));
		assertNotNull(voc);
		assertNotNull(voc.get(0).getFid());
		assertNotNull(voc.get(0).getUri());	
	}
	
	@Test
	public void customField() throws DrupalException{
		Node node=new Node();
		node.setNid(14);
		CustomField map=new CustomField();
		Map fields=new HashMap();
		List<Map> lm = new ArrayList<Map>();

		fields.put("fid", "1");
		lm = new ArrayList<Map>();
		lm.add(fields);
		map.setUnd(lm);
		Map customField=new HashMap<String,CustomField>();
		customField.put("field_image", map);
		node.setCustomFields(customField);
		client.updateNode(node);

	}
	
	@Test
	public void createFile() throws DrupalException{
		org.mule.modules.drupal.model.File file=client.readFile("4");
		org.mule.modules.drupal.model.File file2=new org.mule.modules.drupal.model.File();
		file2.setFilename("temp.gif");
		file2.setContent(file.getContent());
		file2 = client.createFile(file2);
		assertNotNull(file2.getFid());
	}
}
