package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;

public class CreateCommentTestCases extends DrupalTestParent  {

	@Before
	public void setUp() throws Exception {
		initializeTestRunMessage("createCommentTestData");
		String title = getTestRunMessageValue("title");
		String content = getTestRunMessageValue("content");
		String type = getTestRunMessageValue("type");
		Node node = createNode(title, content, type);

		// set nodeId for createcomment testcase
		upsertOnTestRunMessage("nid", node.getNid());
	}
	
	@Test
	public void testCreateComment() throws Exception{
		Integer nodeId = getTestRunMessageValue("nid");
		String commentSubject = getTestRunMessageValue("subject");
		String commentContent =  getTestRunMessageValue("und");
		Comment comment = createComment(commentSubject, commentContent, nodeId);
		assertEquals(comment.getSubject(), getTestRunMessageValue("subject"));
	}
	
	
	@After
	public void tearDown() throws Exception{
		Integer nodeId = getTestRunMessageValue("nid");
		deleteNode(nodeId);
	}
	
	
}
