package org.mule.modules.drupal.automation.testcases;

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
import org.mule.modules.tests.ConnectorTestCase;

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

	protected void deleteNode(Integer nodeId) throws Exception {
		upsertOnTestRunMessage("nodeId", nodeId);
		runFlowAndGetPayload("delete-node");
	}

	protected List<Node> indexNodes()
			throws Exception {
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
	
	protected Comment createComment(Comment comment) throws Exception {
		upsertOnTestRunMessage("ref", comment);
		return runFlowAndGetPayload("create-comment-by-reference");
	}
	
	protected void deleteComment(Integer commentId) throws Exception{
		upsertOnTestRunMessage("commentId", commentId);
		runFlowAndGetPayload("delete-comment");
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

}
