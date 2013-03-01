
package org.mule.modules.drupal.config;

import javax.annotation.Generated;
import org.mule.modules.drupal.model.holders.FileExpressionHolder;
import org.mule.modules.drupal.processors.UpdateFileMessageProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class UpdateFileDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(UpdateFileMessageProcessor.class.getName());
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        parseConfigRef(element, builder);
        if (!parseObjectRefWithDefault(element, builder, "file", "file", "#[payload]")) {
            BeanDefinitionBuilder fileBuilder = BeanDefinitionBuilder.rootBeanDefinition(FileExpressionHolder.class.getName());
            Element fileChildElement = DomUtils.getChildElementByTagName(element, "file");
            if (fileChildElement!= null) {
                parseProperty(fileBuilder, fileChildElement, "fid", "fid");
                parseProperty(fileBuilder, fileChildElement, "uid", "uid");
                parseProperty(fileBuilder, fileChildElement, "filename", "filename");
                parseProperty(fileBuilder, fileChildElement, "filemime", "filemime");
                parseProperty(fileBuilder, fileChildElement, "filesize", "filesize");
                parseProperty(fileBuilder, fileChildElement, "status", "status");
                parseProperty(fileBuilder, fileChildElement, "timestamp", "timestamp");
                parseProperty(fileBuilder, fileChildElement, "content", "content");
                parseProperty(fileBuilder, fileChildElement, "uriFull", "uriFull");
                parseProperty(fileBuilder, fileChildElement, "targetUri", "targetUri");
                if (hasAttribute(fileChildElement, "imageStyles-ref")) {
                    if (fileChildElement.getAttribute("imageStyles-ref").startsWith("#")) {
                        fileBuilder.addPropertyValue("imageStyles", fileChildElement.getAttribute("imageStyles-ref"));
                    } else {
                        fileBuilder.addPropertyValue("imageStyles", (("#[registry:"+ fileChildElement.getAttribute("imageStyles-ref"))+"]"));
                    }
                }
                builder.addPropertyValue("file", fileBuilder.getBeanDefinition());
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
