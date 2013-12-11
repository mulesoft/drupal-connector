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

public class UpdateCommentTestCases extends DrupalTestParent {
	
	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("updateCommentTestData");

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
	
	@Category({RegressionTests.class})
	@Test
	public void testUpdateComment() {
		try {
			Integer commentId = getTestRunMessageValue("commentId");
			Comment toUpdate = getTestRunMessageValue("update");
			toUpdate.setCid(commentId);
			
			upsertOnTestRunMessage("commentRef", toUpdate);
			
			Comment returnedComment = runFlowAndGetPayload("update-comment");
			assertEquals(returnedComment.getCid(), commentId);
			assertEquals(returnedComment.getName(), toUpdate.getName());
			assertEquals(returnedComment.getMail(), toUpdate.getMail());
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