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
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UpdateTaxonomyTermTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("updateTaxonomyTermTestData");

        TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");
        Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
        upsertOnTestRunMessage("vocabularyId", vocabularyId);

        TaxonomyTerm term = getTestRunMessageValue("taxonomyTerm");
        term.setVid(vocabularyId);
        Integer termId = createTaxonomyTermAndGetBackId(term);
        upsertOnTestRunMessage("term", term);
        upsertOnTestRunMessage("termId", termId);
    }

    @Category({RegressionTests.class})
    @Test
    public void testUpdateTaxonomyTerm() {
        try {
            Integer vocabularyId = getTestRunMessageValue("vocabularyId");
            Integer termId = getTestRunMessageValue("termId");
            TaxonomyTerm updatedTerm = getTestRunMessageValue("updatedTaxonomyTerm");
            updatedTerm.setTid(termId);
            updatedTerm.setVid(vocabularyId);
            upsertOnTestRunMessage("taxonomyTermRef", updatedTerm);

            runFlowAndGetPayload("update-taxonomy-term");

            TaxonomyTerm retrievedTerm = readTaxonomyTerm(termId);
            assertEquals(retrievedTerm.getTid(), updatedTerm.getTid());
            assertEquals(retrievedTerm.getVid(), updatedTerm.getVid());
            assertEquals(retrievedTerm.getName(), updatedTerm.getName());
            assertEquals(retrievedTerm.getDescription(), updatedTerm.getDescription());
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        Integer vocabularyId = getTestRunMessageValue("vocabularyId");
        deleteTaxonomyVocabulary(vocabularyId);
    }

}
