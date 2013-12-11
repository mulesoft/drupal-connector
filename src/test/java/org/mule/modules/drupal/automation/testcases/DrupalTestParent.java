package org.mule.modules.drupal.automation.testcases;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.CustomField;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.User;
import org.mule.modules.tests.ConnectorTestCase;
import org.mule.util.Base64;
import org.mule.util.IOUtils;

public class DrupalTestParent extends ConnectorTestCase {
	// Set global timeout of tests to 10minutes
	@Rule
	public Timeout globalTimeout = new Timeout(600000);

	protected Node createNode(String title, String content, String type)
			throws Exception {
		upsertOnTestRunMessage("title", title);
		upsertOnTestRunMessage("content", content);
		upsertOnTestRunMessage("type", type);
		return runFlowAndGetPayload("create-node");
	}
	
	protected Node createNode(Node node) throws Exception {
		upsertOnTestRunMessage("nodeRef", node);
		
		return runFlowAndGetPayload("create-node-by-ref");
	}
	
	protected void deleteNode(Integer nodeId) throws Exception {
		upsertOnTestRunMessage("nodeId", nodeId);
		runFlowAndGetPayload("delete-node");
	}

	protected List<Node> indexNodes() throws Exception {
		return runFlowAndGetPayload("index-nodes");
	}
	
	protected Node readNode(Integer nodeId) throws Exception{
		upsertOnTestRunMessage("nodeId", nodeId);
		return runFlowAndGetPayload("read-node");
	}
	
	protected Comment createComment(String subject, String und, Integer nid) throws Exception{
		upsertOnTestRunMessage("subject", subject);
		upsertOnTestRunMessage("und", und);
		upsertOnTestRunMessage("nid", nid);
		return runFlowAndGetPayload("create-comment");
	}
	
	protected Comment createComment(Comment comment, Integer nodeId) throws Exception {
		comment.setNid(nodeId);
		return createComment(comment);
	}
	
	protected Comment createComment(Comment comment) throws Exception {
		upsertOnTestRunMessage("ref", comment);
		return runFlowAndGetPayload("create-comment-by-reference");
	}
	
	protected void deleteComment(Integer commentId) throws Exception {
		upsertOnTestRunMessage("commentId", commentId);
		runFlowAndGetPayload("delete-comment");
	}
	
	protected Comment readComment(Integer commentId) throws Exception {
		upsertOnTestRunMessage("commentId", commentId);
		
		return runFlowAndGetPayload("read-comment");
	}
	
	protected Node readNode(String nodeId) throws Exception {
		upsertOnTestRunMessage("nodeId", nodeId);
		
		return runFlowAndGetPayload("read-node");
	}
	
	protected File createFile(File file) throws Exception {
		upsertOnTestRunMessage("ref", file);
		
		return runFlowAndGetPayload("create-file");
	}
	
	protected File readFile(Integer fileId) throws Exception {
		upsertOnTestRunMessage("fileId", fileId);
		
		return runFlowAndGetPayload("read-file");
	}
	
	protected boolean deleteFile(Integer fileId) throws Exception {
		upsertOnTestRunMessage("fileId", fileId);
		
		return runFlowAndGetPayload("delete-file");
	}
	
	protected User createUser(User user) throws Exception {
		upsertOnTestRunMessage("userRef", user);
		
		return runFlowAndGetPayload("create-user");
	}
	
	protected User readUser(Integer userId) throws Exception {
		upsertOnTestRunMessage("userId", userId);
		
		return runFlowAndGetPayload("read-user");
	}
	
	protected void deleteUser(Integer userId) throws Exception {
		upsertOnTestRunMessage("userId", userId);
		
		runFlowAndGetPayload("delete-user");
	}

	protected TaxonomyVocabulary createTaxonomyVocabulary(TaxonomyVocabulary vocabulary) throws Exception {
		upsertOnTestRunMessage("taxonomyVocabularyRef", vocabulary);
		
		return runFlowAndGetPayload("create-taxonomy-vocabulary");
	}
	
	protected void deleteTaxonomyVocabulary(Integer vocabularyId) throws Exception {
		upsertOnTestRunMessage("taxonomyVocId", vocabularyId);
		
		runFlowAndGetPayload("delete-taxonomy-vocabulary");
	}
	
	protected TaxonomyVocabulary readTaxonomyVocabulary(Integer vocabularyId) throws Exception {
		upsertOnTestRunMessage("vocabularyId", vocabularyId);
		
		return runFlowAndGetPayload("read-taxonomy-vocabulary");
	}
	
	protected TaxonomyTerm createTaxonomyTerm(TaxonomyTerm term) throws Exception {
		upsertOnTestRunMessage("taxonomyTermRef", term);
		
		return runFlowAndGetPayload("create-taxonomy-term");
	}
	
	protected void deleteTaxonomyTerm(Integer termId) throws Exception {
		upsertOnTestRunMessage("taxonomyTermId", termId);
		
		runFlowAndGetPayload("delete-taxonomy-term");
	}
	
	protected TaxonomyTerm readTaxonomyTerm(Integer termId) throws Exception {
		upsertOnTestRunMessage("taxonomyTermId", termId);
		
		return runFlowAndGetPayload("read-taxonomy-term");
	}
	
	public static Node generateNode(String title, String content, String type) {
		Node node = new Node();
		
		node.setTitle(title);
		node.setType(type);

		HashMap<String, Object> undEntry = new HashMap<String, Object>();
		undEntry.put("value", content);

		ArrayList<Map> und = new ArrayList<Map>();
		und.add(undEntry);
		
		CustomField nodeBody = new CustomField();
		nodeBody.setUnd(und);
		
		node.setBody(nodeBody);
		
		return node;
	}
	
	public static Comment generateComment(String subject, String body) {
		Comment comment = new Comment();
		comment.setSubject(subject);
		
		HashMap<String, Object> undEntry = new HashMap<String, Object>();
		undEntry.put("value", body);

		ArrayList<Map> und = new ArrayList<Map>();
		und.add(undEntry);
		
		CustomField commentBody = new CustomField();
		commentBody.setUnd(und);
		
		comment.setCommentBody(commentBody);
		return comment;
	}
	
	protected static User generateUser(String name, String mail, String password) {
		User user = new User();
		user.setName(name);
		user.setMail(mail);
		user.setPassword(password);
		return user;
	}

	protected static File generateFile(String fileName, String filePath) throws IOException {
		File file = new File();
		file.setFilename(fileName);

		InputStream fileStream = DrupalTestParent.class.getClassLoader().getResourceAsStream(filePath);
		byte[] fileBytes = IOUtils.toByteArray(fileStream);
		
		String base64encode = Base64.encodeBytes(fileBytes);
		file.setContent(base64encode);
		file.setFilesize(fileBytes.length);
		
		return file;
	}
	
}
