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
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer nodeId = getTestRunMessageValue("nodeId");
        deleteNode(nodeId);
    }

}
