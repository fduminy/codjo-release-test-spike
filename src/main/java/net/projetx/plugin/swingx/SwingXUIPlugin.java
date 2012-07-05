package net.projetx.plugin.swingx;

import java.util.List;

import net.projetx.plugin.Plugin;
import net.projetx.plugin.PluginManager;
import net.projetx.plugin.swing.SwingUITester;

/**
 *
 */
public class SwingXUIPlugin implements Plugin {
    @Override
    public void start(PluginManager manager) {
    	List<SwingUITester> sts = manager.getServices(SwingUITester.class);
        SwingUITester st = sts.get(0);
        st.registerTask(SwingXUITask1.class);
    }

    @Override
    public void stop(PluginManager manager) {
        SwingUITester st = manager.getServices(SwingUITester.class).get(0);
    	st.unregisterTask(SwingXUITask1.class);
    }
}
