
package org.mule.modules.drupal.transformers;

import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class StringToClassTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private int priorityWeighting = 1;

    public StringToClassTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(Class.class);
        setName("StringToClassTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        try {
            return Class.forName(((String) src));
        } catch (ClassNotFoundException e) {
            throw new TransformerException(MessageFactory.createStaticMessage(String.format("Could not parse %s as a class", src)), this, e);
        }
    }

    /**
     * Retrieves priorityWeighting
     * 
     */
    public int getPriorityWeighting() {
        return this.priorityWeighting;
    }

    /**
     * Sets priorityWeighting
     * 
     * @param value Value to set
     */
    public void setPriorityWeighting(int value) {
        this.priorityWeighting = value;
    }

}
