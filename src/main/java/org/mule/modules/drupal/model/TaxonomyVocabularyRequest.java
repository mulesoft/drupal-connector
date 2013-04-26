/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.model;

import java.util.ArrayList;
/**
 * Container for the TaxonomyVocabulary requests
 * @author pablocabrera
 *
 */
public class TaxonomyVocabularyRequest extends DrupalEntity {

	private static final long serialVersionUID = -8472333223052455228L;

	/**
	 * List of taxonomy vocabulary that will be in the request
	 */
	ArrayList<TaxonomyVocabulary> vocabulary;

	public ArrayList<TaxonomyVocabulary> getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(ArrayList<TaxonomyVocabulary> vocabulary) {
		this.vocabulary = vocabulary;
	}
}
