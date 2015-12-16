/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.api.MessagingException;
import org.mule.modules.drupal.client.DrupalException;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.*;

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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testReadComment_CommentDoesNotExist() {
        Integer nonExistentCommentId = getTestRunMessageValue("nonExistentCommentId");
        try {
            Comment retrievedComment = readComment(nonExistentCommentId);

            fail("An exception should have been thrown when reading the comment. "
                    + "The delete comment was found, when it should have been deleted."
                    + "ID of the deleted comment: " + retrievedComment.getCid());
        } catch (MessagingException e) {
            if (e.getCause() instanceof DrupalException) {
                DrupalException cause = (DrupalException) e.getCause();
                assertTrue(cause.getMessage().contains(nonExistentCommentId + " not found"));
            } else {
                fail(ConnectorTestUtils.getStackTrace(e));
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("createdNodeId");
        deleteNode(nodeId);
    }

}
