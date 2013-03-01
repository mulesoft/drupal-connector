
package org.mule.modules.drupal.model.transformers;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transformer.DataType;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.MessageTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.api.transformer.TransformerMessagingException;
import org.mule.config.i18n.CoreMessages;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.holders.TaxonomyVocabularyExpressionHolder;
import org.mule.modules.drupal.processors.AbstractExpressionEvaluator;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class TaxonomyVocabularyExpressionHolderTransformer
    extends AbstractExpressionEvaluator
    implements DiscoverableTransformer, MessageTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;
    private ImmutableEndpoint endpoint;
    private MuleContext muleContext;
    private String name;

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

    public boolean isSourceTypeSupported(Class<?> aClass) {
        return (aClass == TaxonomyVocabularyExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == TaxonomyVocabularyExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {TaxonomyVocabularyExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(TaxonomyVocabularyExpressionHolder.class)});
    }

    public boolean isAcceptNull() {
        return false;
    }

    public boolean isIgnoreBadInput() {
        return false;
    }

    public Object transform(Object src)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public Object transform(Object src, String encoding)
        throws TransformerException
    {
        throw new UnsupportedOperationException();
    }

    public void setReturnClass(Class<?> theClass) {
        throw new UnsupportedOperationException();
    }

    public Class<?> getReturnClass() {
        return TaxonomyVocabulary.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(TaxonomyVocabulary.class);
    }

    public void setEndpoint(ImmutableEndpoint ep) {
        endpoint = ep;
    }

    public ImmutableEndpoint getEndpoint() {
        return endpoint;
    }

    public void dispose() {
    }

    public void initialise()
        throws InitialisationException
    {
    }

    public void setMuleContext(MuleContext context) {
        muleContext = context;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getName() {
        return name;
    }

    public Object transform(Object src, MuleEvent event)
        throws TransformerMessagingException
    {
        return transform(src, null, event);
    }

    public Object transform(Object src, String encoding, MuleEvent event)
        throws TransformerMessagingException
    {
        if (src == null) {
            return null;
        }
        TaxonomyVocabularyExpressionHolder holder = ((TaxonomyVocabularyExpressionHolder) src);
        TaxonomyVocabulary result = new TaxonomyVocabulary();
        try {
            final Integer _transformedVid = ((Integer) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_vidType").getGenericType(), null, holder.getVid()));
            result.setVid(_transformedVid);
            final Integer _transformedHierarchy = ((Integer) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_hierarchyType").getGenericType(), null, holder.getHierarchy()));
            result.setHierarchy(_transformedHierarchy);
            final Integer _transformedWeight = ((Integer) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_weightType").getGenericType(), null, holder.getWeight()));
            result.setWeight(_transformedWeight);
            final String _transformedMachineName = ((String) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_machineNameType").getGenericType(), null, holder.getMachineName()));
            result.setMachineName(_transformedMachineName);
            final String _transformedDescription = ((String) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_descriptionType").getGenericType(), null, holder.getDescription()));
            result.setDescription(_transformedDescription);
            final String _transformedName = ((String) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_nameType").getGenericType(), null, holder.getName()));
            result.setName(_transformedName);
            final String _transformedModule = ((String) evaluateAndTransform(this.muleContext, event, TaxonomyVocabularyExpressionHolder.class.getDeclaredField("_moduleType").getGenericType(), null, holder.getModule()));
            result.setModule(_transformedModule);
        } catch (NoSuchFieldException e) {
            throw new TransformerMessagingException(CoreMessages.createStaticMessage("internal error"), event, this, e);
        } catch (TransformerException e) {
            throw new TransformerMessagingException(e.getI18nMessage(), event, this, e);
        }
        return result;
    }

    public MuleEvent process(MuleEvent event) {
        return null;
    }

    public String getMimeType() {
        return null;
    }

    public String getEncoding() {
        return null;
    }

    public MuleContext getMuleContext() {
        return muleContext;
    }

}
