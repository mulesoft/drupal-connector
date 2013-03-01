
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
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.holders.FileExpressionHolder;
import org.mule.modules.drupal.processors.AbstractExpressionEvaluator;
import org.mule.transformer.types.DataTypeFactory;

@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class FileExpressionHolderTransformer
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
        return (aClass == FileExpressionHolder.class);
    }

    public boolean isSourceDataTypeSupported(DataType<?> dataType) {
        return (dataType.getType() == FileExpressionHolder.class);
    }

    public List<Class<?>> getSourceTypes() {
        return Arrays.asList(new Class<?> [] {FileExpressionHolder.class });
    }

    public List<DataType<?>> getSourceDataTypes() {
        return Arrays.asList(new DataType<?> [] {DataTypeFactory.create(FileExpressionHolder.class)});
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
        return File.class;
    }

    public void setReturnDataType(DataType<?> type) {
        throw new UnsupportedOperationException();
    }

    public DataType<?> getReturnDataType() {
        return DataTypeFactory.create(File.class);
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
        FileExpressionHolder holder = ((FileExpressionHolder) src);
        File result = new File();
        try {
            final Integer _transformedFid = ((Integer) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_fidType").getGenericType(), null, holder.getFid()));
            result.setFid(_transformedFid);
            final Integer _transformedUid = ((Integer) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_uidType").getGenericType(), null, holder.getUid()));
            result.setUid(_transformedUid);
            final String _transformedFilename = ((String) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_filenameType").getGenericType(), null, holder.getFilename()));
            result.setFilename(_transformedFilename);
            final String _transformedFilemime = ((String) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_filemimeType").getGenericType(), null, holder.getFilemime()));
            result.setFilemime(_transformedFilemime);
            final Integer _transformedFilesize = ((Integer) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_filesizeType").getGenericType(), null, holder.getFilesize()));
            result.setFilesize(_transformedFilesize);
            final Integer _transformedStatus = ((Integer) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_statusType").getGenericType(), null, holder.getStatus()));
            result.setStatus(_transformedStatus);
            final Integer _transformedTimestamp = ((Integer) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_timestampType").getGenericType(), null, holder.getTimestamp()));
            result.setTimestamp(_transformedTimestamp);
            final String _transformedContent = ((String) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_contentType").getGenericType(), null, holder.getContent()));
            result.setContent(_transformedContent);
            final String _transformedUriFull = ((String) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_uriFullType").getGenericType(), null, holder.getUriFull()));
            result.setUriFull(_transformedUriFull);
            final String _transformedTargetUri = ((String) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_targetUriType").getGenericType(), null, holder.getTargetUri()));
            result.setTargetUri(_transformedTargetUri);
            final Object _transformedImageStyles = ((Object) evaluateAndTransform(this.muleContext, event, FileExpressionHolder.class.getDeclaredField("_imageStylesType").getGenericType(), null, holder.getImageStyles()));
            result.setImageStyles(_transformedImageStyles);
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
