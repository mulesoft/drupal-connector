package org.mule.modules.drupal.automation.testcases;

import java.util.List;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.modules.tests.ConnectorTestCase;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;

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
	
	protected void deleteComment(Integer commentId) throws Exception{
		upsertOnTestRunMessage("commentId", commentId);
		runFlowAndGetPayload("delete-comment");
	}

}
