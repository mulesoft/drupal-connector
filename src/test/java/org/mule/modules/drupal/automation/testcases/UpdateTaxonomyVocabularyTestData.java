/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.automation.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.tests.ConnectorTestUtils;

public class UpdateTaxonomyVocabularyTestData extends DrupalTestParent {
	
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
		}
		catch (Exception e) {
			fail(ConnectorTestUtils.getStackTrace(e));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		Integer vocabularyId = getTestRunMessageValue("vocabularyId");
		deleteTaxonomyVocabulary(vocabularyId);
	}

}
