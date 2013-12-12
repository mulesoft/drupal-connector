package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

public class CountNewCommentsTestCases extends DrupalTestParent {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("countNewCommentsTestData");

		String nodeTitle = getTestRunMessageValue("title");
		String nodeContent = getTestRunMessageValue("content");
		String nodeType = getTestRunMessageValue("type");
		
		Node node = createNode(nodeTitle, nodeContent, nodeType);
		int nodeId = node.getNid();
		
		upsertOnTestRunMessage("nodeId", nodeId);
		
		List<Comment> comments = getTestRunMessageValue("comments");
		List<Integer> commentIds = new ArrayList<Integer>();
		
		for (Comment comment : comments) {
			comment.setNid(nodeId);
			Comment createdComment = createComment(comment);
			commentIds.add(createdComment.getCid());
		}
		
		upsertOnTestRunMessage("commentIds", commentIds);
	}
	
	@Category({RegressionTests.class})
	@Test
	public void testCountNewComments_AllComments() {
		try {
			List<Integer> commentIds = getTestRunMessageValue("commentIds");
			
			int count = (Integer) runFlowAndGetPayload("count-new-comments");
			assertEquals(count, commentIds.size());
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@Category({RegressionTests.class})
	@Test
	//@Ignore
	public void testCountNewComments_NoComments() {
		try {
			// Overwrite the "since" attribute such that we do not retrieve any new comments
			upsertOnTestRunMessage("since", (int) (System.currentTimeMillis() / 1000L));
			
			int count = (Integer) runFlowAndGetPayload("count-new-comments");
			assertEquals(count, 0);
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}

	@Category({RegressionTests.class})
	@Test
	//@Ignore
	public void testCountNewComments_OnlyOneComment() {
		try {
			List<Integer> commentIds = getTestRunMessageValue("commentIds");
			Integer nodeId = getTestRunMessageValue("nodeId");
			
			Comment comment = generateComment("New Comment", "This is a new comment");
			comment.setNid(nodeId);

			// Overwrite the "since" attribute such that we retrieve comments which are
			// created after this point
			upsertOnTestRunMessage("since", (int) (System.currentTimeMillis() / 1000L));
			Thread.sleep(5000);
			
			Comment newComment = createComment(comment);
			commentIds.add(newComment.getCid());
			
			int count = (Integer) runFlowAndGetPayload("count-new-comments");
			assertEquals(count, 1);
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		List<Integer> commentIds = getTestRunMessageValue("commentIds");
		for (Integer commentId : commentIds) {
			deleteComment(commentId);
		}
		
		Integer nodeId = getTestRunMessageValue("nodeId");
		deleteNode(nodeId);
	}
	
}
