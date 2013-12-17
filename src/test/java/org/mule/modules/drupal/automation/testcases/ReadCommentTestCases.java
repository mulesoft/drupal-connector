/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class ReadCommentTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("readCommentTestData");

		String nodeTitle = getTestRunMessageValue("title");
		String nodeContent = getTestRunMessageValue("content");
		String nodeType = getTestRunMessageValue("type");
		
		Node node = createNode(nodeTitle, nodeContent, nodeType);
		int nodeId = node.getNid();
		
		upsertOnTestRunMessage("createdNodeId", nodeId);
		
		Comment comment = getTestRunMessageValue("comment");
		Comment createdComment = createComment(comment, nodeId);
		int commentId = createdComment.getCid();
		
		upsertOnTestRunMessage("createdCommentId", commentId);
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testReadComment_CommentExists() {
		try {
			Integer nodeId = getTestRunMessageValue("createdNodeId");
			Integer commentId = getTestRunMessageValue("createdCommentId");
			
			Comment retrievedComment = readComment(commentId);

			assertEquals(retrievedComment.getCid(), commentId);
			assertEquals(retrievedComment.getNid(), nodeId);
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testReadComment_CommentDoesNotExist() {
		try {
			Comment retrievedComment = readComment(-1);
			
			fail("An exception should have been thrown when reading the comment. "
					+ "The delete comment was found, when it should have been deleted."
					+ "ID of the deleted comment: "+retrievedComment.getCid());
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
