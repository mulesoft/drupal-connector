
package org.mule.modules.drupal.config;

import javax.annotation.Generated;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.model.holders.TaxonomyTermExpressionHolder;
import org.mule.modules.drupal.processors.UpdateTaxonomyTermMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class UpdateTaxonomyTermDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(UpdateTaxonomyTermMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "taxonomy-term", "taxonomyTerm", "#[payload]")) {
            BeanDefinitionBuilder taxonomyTermBuilder = BeanDefinitionBuilder.rootBeanDefinition(TaxonomyTermExpressionHolder.class.getName());
            Element taxonomyTermChildElement = DomUtils.getChildElementByTagName(element, "taxonomy-term");
            if (taxonomyTermChildElement!= null) {
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "description", "description");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "format", "format");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "name", "name");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "tid", "tid");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "vid", "vid");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "weight", "weight");
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "depth", "depth");
                parseListAndSetProperty(taxonomyTermChildElement, taxonomyTermBuilder, "parents", "parents", "parent", new ParseDelegate<String>() {


                    public String parse(Element element) {
                        return element.getTextContent();
                    }

                }
                );
                parseProperty(taxonomyTermBuilder, taxonomyTermChildElement, "vocabularyMachineName", "vocabularyMachineName");
                builder.addPropertyValue("taxonomyTerm", taxonomyTermBuilder.getBeanDefinition());
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
