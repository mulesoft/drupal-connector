/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class DeleteCommentTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("deleteCommentTestData");

		String nodeTitle = getTestRunMessageValue("title");
		String nodeContent = getTestRunMessageValue("content");
		String nodeType = getTestRunMessageValue("type");
		
		Node node = createNode(nodeTitle, nodeContent, nodeType);
		int nodeId = node.getNid();
		
		upsertOnTestRunMessage("nodeId", nodeId);
		
		Comment comment = getTestRunMessageValue("comment");
		Comment createdComment = createComment(comment, nodeId);
		int commentId = createdComment.getCid();
		
		upsertOnTestRunMessage("commentId", commentId);
	}
	
	@Category({SmokeTests.class, RegressionTests.class})
	@Test
	public void testDeleteComment() {
		try {
			Integer commentId = getTestRunMessageValue("commentId");
			boolean result = deleteComment(commentId);
			assertTrue(result);

			// Should throw an exception
			Comment retrievedComment = readComment(commentId);

			fail("An exception should have been thrown when reading the comment. "
					+ "The delete comment was found, when it should have been deleted."
					+ "ID of the deleted comment: "+retrievedComment.getCid());
		}
		catch (MessagingException e) {
			if (e.getCause() instanceof DrupalException) {
				DrupalException cause = (DrupalException) e.getCause();
				assertTrue(cause.getMessage().contains("406") && cause.getMessage().contains("Not Acceptable"));
			}
			else fail(ConnectorTestUtils.getStackTrace(e));
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
