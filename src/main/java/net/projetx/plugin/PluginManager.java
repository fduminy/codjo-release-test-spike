package net.projetx.plugin;
import java.util.List;
/**
 *
 */
public interface PluginManager {
    <T> void registerService(Class<T> serviceImplementation);
    <T> void unregisterService(Class<T> serviceImplementation);
    <T> List<T> getServices(Class<T> serviceInterface);

    <T extends Plugin> void addPlugin(Class<T> plugin);
    void start();
    void stop();
}
