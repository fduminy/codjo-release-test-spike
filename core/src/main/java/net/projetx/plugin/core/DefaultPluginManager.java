package net.projetx.plugin.core;
import java.util.List;

import net.projetx.plugin.Plugin;
import net.projetx.plugin.PluginManager;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.behaviors.Caching;
/**
 *
 */
public class DefaultPluginManager implements PluginManager {
    private final DefaultPicoContainer container = new DefaultPicoContainer(new Caching());

    public DefaultPluginManager() {
    	container.addComponent(this);
    }

    @Override
    public <T> void registerService(Class<T> serviceImplementation) {
        container.addComponent(serviceImplementation);
    }

    @Override
    public <T> void unregisterService(Class<T> serviceImplementation) {
    	container.removeComponent(serviceImplementation);
    }

    public <T> List<T> getServices(Class<T> serviceInterface) {
        return container.getComponents(serviceInterface);
    }

    public <T extends Plugin> void addPlugin(T plugin) {
    	container.addComponent(plugin);
    }

    public void start() {
        container.start();
        for (Plugin p : container.getComponents(Plugin.class)) {
            p.start(this);
        }
    }

    public void stop() {
        container.stop();
    }
    
	public void addPlugins(PluginFinder finder) {
		finder.addPlugins(this);
	}
}
