
package org.mule.modules.drupal.config;

import java.util.Map;
import javax.annotation.Generated;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.config.AbstractDefinitionParser.ParseDelegate;
import org.mule.modules.drupal.model.holders.CustomFieldExpressionHolder;
import org.mule.modules.drupal.model.holders.NodeExpressionHolder;
import org.mule.modules.drupal.processors.UpdateNodeMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class UpdateNodeDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(UpdateNodeMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "node", "node", "#[payload]")) {
            BeanDefinitionBuilder nodeBuilder = BeanDefinitionBuilder.rootBeanDefinition(NodeExpressionHolder.class.getName());
            Element nodeChildElement = DomUtils.getChildElementByTagName(element, "node");
            if (nodeChildElement!= null) {
                parseProperty(nodeBuilder, nodeChildElement, "nid", "nid");
                parseProperty(nodeBuilder, nodeChildElement, "uid", "uid");
                parseProperty(nodeBuilder, nodeChildElement, "vid", "vid");
                parseProperty(nodeBuilder, nodeChildElement, "type", "type");
                parseProperty(nodeBuilder, nodeChildElement, "language", "language");
                parseProperty(nodeBuilder, nodeChildElement, "title", "title");
                parseProperty(nodeBuilder, nodeChildElement, "status", "status");
                parseProperty(nodeBuilder, nodeChildElement, "comment", "comment");
                parseProperty(nodeBuilder, nodeChildElement, "promote", "promote");
                parseProperty(nodeBuilder, nodeChildElement, "sticky", "sticky");
                parseProperty(nodeBuilder, nodeChildElement, "tnid", "tnid");
                parseProperty(nodeBuilder, nodeChildElement, "translated", "translated");
                parseProperty(nodeBuilder, nodeChildElement, "revisionTimestamp", "revisionTimestamp");
                parseProperty(nodeBuilder, nodeChildElement, "revisionUid", "revisionUid");
                if (!parseObjectRef(nodeChildElement, nodeBuilder, "body", "body")) {
                    BeanDefinitionBuilder _bodyBuilder = BeanDefinitionBuilder.rootBeanDefinition(CustomFieldExpressionHolder.class.getName());
                    Element _bodyChildElement = DomUtils.getChildElementByTagName(nodeChildElement, "body");
                    if (_bodyChildElement!= null) {
                        parseListAndSetProperty(_bodyChildElement, _bodyBuilder, "und", "und", "und", new ParseDelegate<Map>() {


                            public Map parse(Element element) {
                                return parseMap(element, "inner-und", new ParseDelegate<String>() {


                                    public String parse(Element element) {
                                        return element.getTextContent();
                                    }

                                }
                                );
                            }

                        }
                        );
                        nodeBuilder.addPropertyValue("body", _bodyBuilder.getBeanDefinition());
                    }
                }
                parseProperty(nodeBuilder, nodeChildElement, "created", "created");
                parseProperty(nodeBuilder, nodeChildElement, "changed", "changed");
                parseProperty(nodeBuilder, nodeChildElement, "cid", "cid");
                parseProperty(nodeBuilder, nodeChildElement, "lastCommentTimestamp", "lastCommentTimestamp");
                parseProperty(nodeBuilder, nodeChildElement, "lastCommentName", "lastCommentName");
                parseProperty(nodeBuilder, nodeChildElement, "lastCommentUid", "lastCommentUid");
                parseProperty(nodeBuilder, nodeChildElement, "commentCount", "commentCount");
                parseProperty(nodeBuilder, nodeChildElement, "name", "name");
                parseProperty(nodeBuilder, nodeChildElement, "picture", "picture");
                parseProperty(nodeBuilder, nodeChildElement, "data", "data");
                parseProperty(nodeBuilder, nodeChildElement, "path", "path");
                builder.addPropertyValue("node", nodeBuilder.getBeanDefinition());
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
