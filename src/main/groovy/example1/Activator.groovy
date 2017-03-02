package example1

import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.osgi.framework.ServiceListener
import org.osgi.framework.ServiceEvent

/**
 * This class implements a simple bundle that utilizes the OSGi
 * framework's event mechanism to listen for service events. Upon
 * receiving a service event, it prints out the event's details.
**/
class Activator implements BundleActivator, ServiceListener
{
    /**
     * Implements BundleActivator.start(). Prints
     * a message and adds itself to the bundle context as a service
     * listener.
     * @param context the framework context for the bundle.
    **/
    void start(BundleContext context)
    {
        println("Starting to listen for service events.")
        context.addServiceListener(this)
    }

    /**
     * Implements BundleActivator.stop(). Prints
     * a message and removes itself from the bundle context as a
     * service listener.
     * @param context the framework context for the bundle.
    **/
    void stop(BundleContext context)
    {
        context.removeServiceListener(this)
        println("Stopped listening for service events.")

        // Note: It is not required that we remove the listener here,
        // since the framework will do it automatically anyway.
    }

    /**
     * Implements ServiceListener.serviceChanged().
     * Prints the details of any service event from the framework.
     * @param event the fired service event.
    **/
    void serviceChanged(ServiceEvent event)
    {
        String[] objectClass = (String[])
            event.getServiceReference().getProperty("objectClass")

        if (event.getType() == ServiceEvent.REGISTERED)
        {
            println(
                "Ex1: Service of type " + objectClass[0] + " registered.")
        }
        else if (event.getType() == ServiceEvent.UNREGISTERING)
        {
            println(
                "Ex1: Service of type " + objectClass[0] + " unregistered.")
        }
        else if (event.getType() == ServiceEvent.MODIFIED)
        {
            println(
                "Ex1: Service of type " + objectClass[0] + " modified.")
        }
    }
}
