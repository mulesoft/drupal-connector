/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class IndexCommentsTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("indexCommentsTestData");
		
		Node node = getTestRunMessageValue("node");
		
		Node createdNode = createNode(node);
		Integer createdNodeId = createdNode.getNid();
		upsertOnTestRunMessage("createdNodeId", createdNodeId);
		
		List<Comment> comments = getTestRunMessageValue("comments");
		List<Integer> commentIds = new ArrayList<Integer>();
		
		for (Comment comment : comments) {
			comment.setNid(createdNodeId);
			Comment createdComment = createComment(comment);
			commentIds.add(createdComment.getCid());
		}
		
		upsertOnTestRunMessage("commentIds", commentIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testIndexComments() {
		try {
			Integer nodeId = getTestRunMessageValue("createdNodeId");
			List<Integer> commentIds = getTestRunMessageValue("commentIds");
			
			List<Comment> retrievedComments = runFlowAndGetPayload("index-comments");
			
			assertEquals(commentIds.size(), retrievedComments.size());
			for (Comment comment : retrievedComments) {
				assertTrue(commentIds.contains(comment.getCid()));
				assertEquals(comment.getNid(), nodeId);
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer nodeId = getTestRunMessageValue("createdNodeId");
		deleteNode(nodeId);
	}
	
}
