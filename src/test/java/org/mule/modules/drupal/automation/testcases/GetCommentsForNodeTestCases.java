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

public class GetCommentsForNodeTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("getCommentsForNodeTestData");
		
		String nodeTitle = getTestRunMessageValue("title");
		String nodeContent = getTestRunMessageValue("content");
		String nodeType = getTestRunMessageValue("type");
		
		Node node = createNode(nodeTitle, nodeContent, nodeType);
		int nodeId = node.getNid();
		
		upsertOnTestRunMessage("nodeId", nodeId);

		List<Comment> comments = getTestRunMessageValue("comments");
		
		List<Integer> createdCommentIds = new ArrayList<Integer>();
		for (Comment comment : comments) {
			Comment createdComment = createComment(comment, nodeId);
			createdCommentIds.add(createdComment.getCid());
		}
		upsertOnTestRunMessage("createdCommentIds", createdCommentIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testGetCommentsForNode() {
		try {
			List<Integer> createdCommentIds = getTestRunMessageValue("createdCommentIds");
			
			List<Comment> nodeComments = runFlowAndGetPayload("get-comments-for-node");

			assertEquals(createdCommentIds.size(), nodeComments.size());
			for (Comment comment : nodeComments) {
				assertTrue(createdCommentIds.contains(comment.getCid()));
			}
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}

}
