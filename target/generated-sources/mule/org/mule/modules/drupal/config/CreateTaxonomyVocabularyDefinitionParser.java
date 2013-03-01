
package org.mule.modules.drupal.config;

import javax.annotation.Generated;
import org.mule.modules.drupal.model.holders.TaxonomyVocabularyExpressionHolder;
import org.mule.modules.drupal.processors.CreateTaxonomyVocabularyMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class CreateTaxonomyVocabularyDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(CreateTaxonomyVocabularyMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "taxonomy-vocabulary", "taxonomyVocabulary", "#[payload]")) {
            BeanDefinitionBuilder taxonomyVocabularyBuilder = BeanDefinitionBuilder.rootBeanDefinition(TaxonomyVocabularyExpressionHolder.class.getName());
            Element taxonomyVocabularyChildElement = DomUtils.getChildElementByTagName(element, "taxonomy-vocabulary");
            if (taxonomyVocabularyChildElement!= null) {
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "vid", "vid");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "hierarchy", "hierarchy");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "weight", "weight");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "machineName", "machineName");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "description", "description");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "name", "name");
                parseProperty(taxonomyVocabularyBuilder, taxonomyVocabularyChildElement, "module", "module");
                builder.addPropertyValue("taxonomyVocabulary", taxonomyVocabularyBuilder.getBeanDefinition());
            }
        }
        parseProperty(builder, element, "username", "username");
        parseProperty(builder, element, "password", "password");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
