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
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Category({SmokeTests.class, RegressionTests.class})
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
