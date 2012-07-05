package net.projetx.plugin.swing;

import net.projetx.plugin.Plugin;
import net.projetx.plugin.PluginManager;

/**
 *
 */
public class SwingUIPlugin implements Plugin {
    @Override
    public void start(PluginManager manager) {
        manager.registerService(SwingUITester.class);
        
        SwingUITester st = manager.getServices(SwingUITester.class).get(0);
        st.registerTask(SwingUITask1.class);
    }

    @Override
    public void stop(PluginManager manager) {    	
        SwingUITester st = manager.getServices(SwingUITester.class).get(0);
        st.unregisterTask(SwingUITask1.class);
        
    	manager.unregisterService(SwingUITester.class);
    }
}
