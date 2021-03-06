/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.mule.modules.drupal.model.*;
import org.mule.modules.tests.ConnectorTestCase;
import org.mule.util.Base64;
import org.mule.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrupalTestParent extends ConnectorTestCase {
    // Set global timeout of tests to 10minutes
    @Rule
    public Timeout globalTimeout = new Timeout(600000);

    protected Node createNode(String title, String content, String type)
            throws Exception {
        upsertOnTestRunMessage("title", title);
        upsertOnTestRunMessage("content", content);
        upsertOnTestRunMessage("type", type);
        return runFlowAndGetPayload("create-node");
    }

    protected Node createNode(Node node) throws Exception {
        upsertOnTestRunMessage("nodeRef", node);

        return runFlowAndGetPayload("create-node-by-ref");
    }

    protected Boolean deleteNode(Integer nodeId) throws Exception {
        upsertOnTestRunMessage("nodeId", nodeId);
        return runFlowAndGetPayload("delete-node");
    }

    protected Node readNode(Integer nodeId) throws Exception {
        upsertOnTestRunMessage("nodeId", nodeId);
        return runFlowAndGetPayload("read-node");
    }

    protected Comment createComment(String subject, String und, Integer nid) throws Exception {
        upsertOnTestRunMessage("subject", subject);
        upsertOnTestRunMessage("und", und);
        upsertOnTestRunMessage("nid", nid);
        return runFlowAndGetPayload("create-comment");
    }

    protected Comment createComment(Comment comment, Integer nodeId) throws Exception {
        comment.setNid(nodeId);
        return createComment(comment);
    }

    protected Comment createComment(Comment comment) throws Exception {
        upsertOnTestRunMessage("ref", comment);
        return runFlowAndGetPayload("create-comment-by-reference");
    }

    protected Boolean deleteComment(Integer commentId) throws Exception {
        upsertOnTestRunMessage("commentId", commentId);
        return runFlowAndGetPayload("delete-comment");
    }

    protected Comment readComment(Integer commentId) throws Exception {
        upsertOnTestRunMessage("commentId", commentId);

        return runFlowAndGetPayload("read-comment");
    }

    protected Node readNode(String nodeId) throws Exception {
        upsertOnTestRunMessage("nodeId", nodeId);

        return runFlowAndGetPayload("read-node");
    }

    protected File createFile(File file) throws Exception {
        upsertOnTestRunMessage("ref", file);

        return runFlowAndGetPayload("create-file");
    }

    protected File readFile(Integer fileId) throws Exception {
        upsertOnTestRunMessage("fileId", fileId);

        return runFlowAndGetPayload("read-file");
    }

    protected Boolean deleteFile(Integer fileId) throws Exception {
        upsertOnTestRunMessage("fileId", fileId);

        return runFlowAndGetPayload("delete-file");
    }

    protected User createUser(User user) throws Exception {
        upsertOnTestRunMessage("userRef", user);

        return runFlowAndGetPayload("create-user");
    }

    protected User readUser(Integer userId) throws Exception {
        upsertOnTestRunMessage("userId", userId);

        return runFlowAndGetPayload("read-user");
    }

    protected Boolean deleteUser(Integer userId) throws Exception {
        upsertOnTestRunMessage("userId", userId);

        return runFlowAndGetPayload("delete-user");
    }

    protected void createTaxonomyVocabulary(TaxonomyVocabulary vocabulary) throws Exception {
        upsertOnTestRunMessage("taxonomyVocabularyRef", vocabulary);

        runFlowAndGetPayload("create-taxonomy-vocabulary");
    }

    protected Integer createTaxonomyVocabularyAndGetBackId(TaxonomyVocabulary vocabulary) throws Exception {
        createTaxonomyVocabulary(vocabulary);

        List<TaxonomyVocabulary> vocabularies = indexTaxonomyVocabulary();
        TaxonomyVocabulary createdVocabulary = searchListVocab(vocabularies, vocabulary.getMachineName());

        Integer vocabularyId = createdVocabulary.getVid();
        return vocabularyId;
    }

    protected void deleteTaxonomyVocabulary(Integer vocabularyId) throws Exception {
        upsertOnTestRunMessage("taxonomyVocId", vocabularyId);

        runFlowAndGetPayload("delete-taxonomy-vocabulary");
    }

    protected TaxonomyVocabulary readTaxonomyVocabulary(Integer vocabularyId) throws Exception {
        upsertOnTestRunMessage("vocabularyId", vocabularyId);

        return runFlowAndGetPayload("read-taxonomy-vocabulary");
    }

    protected void createTaxonomyTerm(TaxonomyTerm term) throws Exception {
        upsertOnTestRunMessage("taxonomyTermRef", term);

        runFlowAndGetPayload("create-taxonomy-term");
    }

    protected Integer createTaxonomyTermAndGetBackId(TaxonomyTerm term) throws Exception {
        createTaxonomyTerm(term);

        String name = term.getName();
        List<TaxonomyTerm> indexTaxonomyTerms = indexTaxonomyTerms();

        TaxonomyTerm found = searchListTerm(indexTaxonomyTerms, name);
        return found.getTid();
    }

    protected List<TaxonomyTerm> indexTaxonomyTerms() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("tid");
        fields.add("vid");
        fields.add("name");
        fields.add("description");

        return indexTaxonomyTerms(-1, 0, fields);
    }

    protected List<TaxonomyTerm> indexTaxonomyTerms(Integer pageSize, Integer startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-taxonomy-terms");
    }

    protected void deleteTaxonomyTerm(Integer termId) throws Exception {
        upsertOnTestRunMessage("taxonomyTermId", termId);

        runFlowAndGetPayload("delete-taxonomy-term");
    }

    protected TaxonomyTerm readTaxonomyTerm(Integer termId) throws Exception {
        upsertOnTestRunMessage("taxonomyTermId", termId);

        return runFlowAndGetPayload("read-taxonomy-term");
    }

    protected List<Comment> indexComments() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("cid");
        fields.add("nid");
        fields.add("subject");
        fields.add("created");
        fields.add("changed");

        return indexComments(-1, 0, fields);
    }

    protected List<Comment> indexComments(Integer pageSize, Integer startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-comments");
    }

    protected List<File> indexFiles() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("fid");

        return indexFiles(-1, 0, fields);
    }

    protected List<File> indexFiles(int pageSize, int startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-files");
    }

    protected List<Node> indexNodes() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("nid");
        fields.add("title");
        fields.add("type");

        return indexNodes(-1, 0, fields);
    }

    protected List<Node> indexNodes(int pageSize, int startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-nodes");
    }

    protected List<User> indexUsers() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("uid");
        fields.add("name");
        fields.add("mail");

        return indexUsers(-1, 0, fields);
    }

    protected List<User> indexUsers(int pageSize, int startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-users");
    }

    protected List<TaxonomyVocabulary> indexTaxonomyVocabulary() throws Exception {
        List<String> fields = new ArrayList<String>();

        fields.add("vid");
        fields.add("name");
        fields.add("machine_name");
        fields.add("description");

        return indexTaxonomyVocabulary(-1, 0, fields);
    }

    protected List<TaxonomyVocabulary> indexTaxonomyVocabulary(Integer pageSize, Integer startPage, List<String> fields) throws Exception {
        upsertOnTestRunMessage("pagesize", pageSize);
        upsertOnTestRunMessage("startPage", startPage);
        upsertOnTestRunMessage("fieldsRef", fields);

        return runFlowAndGetPayload("index-taxonomy-vocabulary");
    }

    protected List<File> getFilesForNode(Integer nodeId) throws Exception {
        upsertOnTestRunMessage("nodeId", nodeId);

        return runFlowAndGetPayload("get-files-for-node");
    }

    protected List<File> attachFilesToNode(Integer nodeId, List<java.io.File> files, String fieldName) throws Exception {
        return attachFilesToNode(nodeId, files, fieldName, true);
    }

    protected List<File> attachFilesToNode(Integer nodeId, List<java.io.File> files, String fieldName, boolean attach) throws Exception {
        upsertOnTestRunMessage("nodeId", nodeId);
        upsertOnTestRunMessage("filesRef", files);
        upsertOnTestRunMessage("fieldName", fieldName);
        upsertOnTestRunMessage("attach", attach);

        return runFlowAndGetPayload("attach-files-to-node");
    }

    public static Node generateNode(String title, String content, String type) {
        Node node = new Node();

        node.setTitle(title);
        node.setType(type);

        HashMap<String, Object> undEntry = new HashMap<String, Object>();
        undEntry.put("value", content);

        ArrayList<Map> und = new ArrayList<Map>();
        und.add(undEntry);

        CustomField nodeBody = new CustomField();
        nodeBody.setUnd(und);

        node.setBody(nodeBody);

        return node;
    }

    public static Comment generateComment(String subject, String body) {
        Comment comment = new Comment();
        comment.setSubject(subject);

        HashMap<String, Object> undEntry = new HashMap<String, Object>();
        undEntry.put("value", body);

        ArrayList<Map> und = new ArrayList<Map>();
        und.add(undEntry);

        CustomField commentBody = new CustomField();
        commentBody.setUnd(und);

        comment.setCommentBody(commentBody);
        return comment;
    }

    protected static User generateUser(String name, String mail, String password) {
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setPassword(password);
        return user;
    }

    protected static File generateFile(String fileName, String filePath) throws IOException {
        File file = new File();
        file.setFilename(fileName);

        InputStream fileStream = DrupalTestParent.class.getClassLoader().getResourceAsStream(filePath);
        byte[] fileBytes = IOUtils.toByteArray(fileStream);

        String base64encode = Base64.encodeBytes(fileBytes);
        file.setContent(base64encode);
        file.setFilesize(fileBytes.length);

        return file;
    }

    // Machine name is a field that is unique
    protected TaxonomyVocabulary searchListVocab(List<TaxonomyVocabulary> vocabularies, String machineName) {
        for (TaxonomyVocabulary taxonomyVocabulary : vocabularies) {
            if (machineName.equals(taxonomyVocabulary.getMachineName())) {
                return taxonomyVocabulary;
            }
        }
        return null;
    }

    protected TaxonomyTerm searchListTerm(List<TaxonomyTerm> terms, String name) {
        for (TaxonomyTerm taxonomyTerm : terms) {
            if (taxonomyTerm.getName().equals(name)) {
                return taxonomyTerm;
            }
        }
        return null;
    }

    protected void addTermToNode(Node node, int termId) {
        if (node.getCustomFields() == null) {
            node.setCustomFields(new HashMap<String, CustomField>());
        }

        CustomField field = node.getCustomFields().get("field_tags");

        if (field == null) {
            field = new CustomField();
            List<Map> und = new ArrayList<Map>();
            field.setUnd(und);
        }

        List<Map> und = field.getUnd();
        Map<String, Object> termEntry = new HashMap<String, Object>();
        termEntry.put("tid", termId);
        und.add(termEntry);

        node.getCustomFields().put("field_tags", field);
    }

}
