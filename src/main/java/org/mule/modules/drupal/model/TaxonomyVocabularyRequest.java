/**
 * Mule Drupal Cloud Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
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
