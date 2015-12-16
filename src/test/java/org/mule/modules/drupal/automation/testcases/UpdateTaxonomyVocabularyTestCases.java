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
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UpdateTaxonomyVocabularyTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("updateTaxonomyVocabularyTestData");

        TaxonomyVocabulary vocabulary = getTestRunMessageValue("vocabulary");
        Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
        upsertOnTestRunMessage("vocabularyId", vocabularyId);
    }

    @Category({RegressionTests.class})
    @Test
    public void testUpdateTaxonomyVocabulary() {
        try {
            Integer vocabularyId = getTestRunMessageValue("vocabularyId");
            TaxonomyVocabulary toUpdate = getTestRunMessageValue("updatedVocabulary");
            toUpdate.setVid(vocabularyId);

            upsertOnTestRunMessage("taxonomyVocabularyRef", toUpdate);
            runFlowAndGetPayload("update-taxonomy-vocabulary");

            TaxonomyVocabulary updatedTaxonomyVocabulary = readTaxonomyVocabulary(vocabularyId);
            assertEquals(updatedTaxonomyVocabulary.getVid(), vocabularyId);
            assertEquals(updatedTaxonomyVocabulary.getMachineName(), toUpdate.getMachineName());
            assertEquals(updatedTaxonomyVocabulary.getName(), toUpdate.getName());
            assertEquals(updatedTaxonomyVocabulary.getDescription(), toUpdate.getDescription());
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
