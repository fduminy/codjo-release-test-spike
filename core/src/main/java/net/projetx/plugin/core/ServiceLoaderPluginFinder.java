package net.projetx.plugin.core;

import java.util.ServiceLoader;

import net.projetx.plugin.Plugin;
import net.projetx.plugin.PluginManager;
import net.projetx.plugin.swing.SwingUIPlugin;

public class ServiceLoaderPluginFinder implements PluginFinder {
	@Override
	public void addPlugins(PluginManager manager) {
		ServiceLoader<Plugin> plugins = ServiceLoader.load(Plugin.class, SwingUIPlugin.class.getClassLoader());
	     for (Plugin plugin : plugins) {
	    	 manager.addPlugin(plugin);
	     }		
	}
}
