
package org.mule.modules.drupal.processors;

import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MessagingException;
import org.mule.api.MuleContext;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.construct.FlowConstruct;
import org.mule.api.lifecycle.Disposable;
import org.mule.api.lifecycle.Initialisable;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;
import org.mule.api.process.ProcessAdapter;
import org.mule.api.process.ProcessCallback;
import org.mule.api.process.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.config.i18n.CoreMessages;
import org.mule.modules.drupal.DrupalConnector;
import org.mule.modules.drupal.connectivity.DrupalConnectorConnectionManager;


/**
 * IndexNodesMessageProcessor invokes the {@link org.mule.modules.drupal.DrupalConnector#indexNodes(java.util.List, int, int)} method in {@link DrupalConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@Generated(value = "Mule DevKit Version 3.4.0-RC1", date = "2013-03-01T07:18:25-03:00", comments = "Build master.1476.47e461c")
public class IndexNodesMessageProcessor
    extends AbstractMessageProcessor<Object>
    implements Disposable, Initialisable, Startable, Stoppable, MessageProcessor
{

    protected Object fields;
    protected List<String> _fieldsType;
    protected Object startPage;
    protected int _startPageType;
    protected Object pagesize;
    protected int _pagesizeType;

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    public void start()
        throws MuleException
    {
    }

    public void stop()
        throws MuleException
    {
    }

    public void dispose() {
    }

    /**
     * Set the Mule context
     * 
     * @param context Mule context to set
     */
    public void setMuleContext(MuleContext context) {
        super.setMuleContext(context);
    }

    /**
     * Sets flow construct
     * 
     * @param flowConstruct Flow construct to set
     */
    public void setFlowConstruct(FlowConstruct flowConstruct) {
        super.setFlowConstruct(flowConstruct);
    }

    /**
     * Sets pagesize
     * 
     * @param value Value to set
     */
    public void setPagesize(Object value) {
        this.pagesize = value;
    }

    /**
     * Sets startPage
     * 
     * @param value Value to set
     */
    public void setStartPage(Object value) {
        this.startPage = value;
    }

    /**
     * Sets fields
     * 
     * @param value Value to set
     */
    public void setFields(Object value) {
        this.fields = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws MuleException
     */
    public MuleEvent process(final MuleEvent event)
        throws MuleException
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(DrupalConnectorConnectionManager.class, true, event);
            final List<String> _transformedFields = ((List<String> ) evaluateAndTransform(getMuleContext(), event, IndexNodesMessageProcessor.class.getDeclaredField("_fieldsType").getGenericType(), null, fields));
            final Integer _transformedStartPage = ((Integer) evaluateAndTransform(getMuleContext(), event, IndexNodesMessageProcessor.class.getDeclaredField("_startPageType").getGenericType(), null, startPage));
            final Integer _transformedPagesize = ((Integer) evaluateAndTransform(getMuleContext(), event, IndexNodesMessageProcessor.class.getDeclaredField("_pagesizeType").getGenericType(), null, pagesize));
            Object resultPayload;
            ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((DrupalConnector) object).indexNodes(_transformedFields, _transformedStartPage, _transformedPagesize);
                }

            }
            , this, event);
            overwritePayload(event, resultPayload);
            return event;
        } catch (MessagingException messagingException) {
            messagingException.setProcessedEvent(event);
            throw messagingException;
        } catch (Exception e) {
            throw new MessagingException(CoreMessages.failedToInvoke("indexNodes"), event, e);
        }
    }

}
