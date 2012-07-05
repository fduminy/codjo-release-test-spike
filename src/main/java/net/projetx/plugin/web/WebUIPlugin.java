package net.projetx.plugin.web;

import net.projetx.plugin.Plugin;
import net.projetx.plugin.PluginManager;

/**
 *
 */
public class WebUIPlugin implements Plugin {
    @Override
    public void start(PluginManager manager) {
        manager.registerService(WebUITester.class);
        
        WebUITester st = manager.getServices(WebUITester.class).get(0);
        st.registerTask(WebUITask1.class);
    }

    @Override
    public void stop(PluginManager manager) {
    	WebUITester st = manager.getServices(WebUITester.class).get(0);
        st.unregisterTask(WebUITask1.class);
        
    	manager.unregisterService(WebUITester.class);
    }
}
