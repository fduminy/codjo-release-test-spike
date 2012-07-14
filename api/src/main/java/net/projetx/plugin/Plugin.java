package net.projetx.plugin;
/**
 *
 */
public interface Plugin {
    void start(PluginManager manager);
    void stop(PluginManager manager);
}
