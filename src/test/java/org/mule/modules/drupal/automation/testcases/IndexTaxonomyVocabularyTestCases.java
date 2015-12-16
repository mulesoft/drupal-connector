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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class IndexTaxonomyVocabularyTestCases extends DrupalTestParent {

    @Before
    public void setUp() throws Exception {
        initializeTestRunMessage("indexTaxonomyVocabularyTestData");

        List<TaxonomyVocabulary> taxonomyVocabularies = getTestRunMessageValue("taxonomyVocabularies");
        List<Integer> vocabularyIds = new ArrayList<Integer>();
        for (TaxonomyVocabulary taxonomyVocabulary : taxonomyVocabularies) {
            Integer vocabularyId = createTaxonomyVocabularyAndGetBackId(taxonomyVocabulary);
            vocabularyIds.add(vocabularyId);
        }
        upsertOnTestRunMessage("vocabularyIds", vocabularyIds);
    }

    @Category({SmokeTests.class, RegressionTests.class})
    @Test
    public void testIndexTaxonomyVocabulary() {
        try {
            List<Integer> vocabularyIds = getTestRunMessageValue("vocabularyIds");
            List<TaxonomyVocabulary> taxonomyVocabularies = indexTaxonomyVocabulary();

            // Drupal is initialized with a number of taxonomy vocabularies
            assertTrue(taxonomyVocabularies.size() >= vocabularyIds.size());

            for (Integer vocabularyId : vocabularyIds) {
                boolean found = false;
                for (TaxonomyVocabulary taxonomyVocabulary : taxonomyVocabularies) {
                    if (taxonomyVocabulary.getVid() == vocabularyId) {
                        found = true;
                        break;
                    }
                }
                assertTrue(found);
            }
        } catch (Exception e) {
            fail(ConnectorTestUtils.getStackTrace(e));
        }
    }

    @After
    public void tearDown() throws Exception {
        List<Integer> vocabularyIds = getTestRunMessageValue("vocabularyIds");
        for (Integer vocabularyId : vocabularyIds) {
            deleteTaxonomyVocabulary(vocabularyId);
        }
    }

}
