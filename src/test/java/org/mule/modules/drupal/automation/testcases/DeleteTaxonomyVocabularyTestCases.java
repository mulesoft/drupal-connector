/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is published under
 * the terms of the CPAL v1.0 license, a copy of which has been included with this
 * distribution in the LICENSE.md file.
 */

package org.mule.modules.drupal.automation.testcases;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DeleteTaxonomyVocabularyTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("deleteTaxonomyVocabularyTestData");

        TaxonomyVocabulary vocabulary = getTestRunMessageValue("taxonomyVocabulary");

        Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(vocabulary);
        upsertOnTestRunMessage("vocabularyId", vocabularyId);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testDeleteTaxonomyVocabulary() {
        try {
            Integer vocabularyId = getTestRunMessageValue("vocabularyId");
            deleteTaxonomyVocabulary(vocabularyId);

            List<TaxonomyVocabulary> taxonomyVocabularies = indexTaxonomyVocabulary();

            for (TaxonomyVocabulary taxonomyVocabulary : taxonomyVocabularies) {
                assertTrue(taxonomyVocabulary.getVid() != vocabularyId);
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

}
