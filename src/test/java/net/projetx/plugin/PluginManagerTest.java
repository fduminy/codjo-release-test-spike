package net.projetx.plugin;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.projetx.plugin.DefaultPluginManager;
import net.projetx.plugin.PluginManager;
import net.projetx.plugin.swing.SwingUIPlugin;
import net.projetx.plugin.swing.SwingUITask;
import net.projetx.plugin.swing.SwingUITask1;
import net.projetx.plugin.swing.SwingUITester;
import net.projetx.plugin.swingx.SwingXUIPlugin;
import net.projetx.plugin.swingx.SwingXUITask1;
import net.projetx.plugin.web.WebUIPlugin;
import net.projetx.plugin.web.WebUITask1;
import net.projetx.plugin.web.WebUITester;

import org.junit.Before;
import org.junit.Test;
/**
 *
 */
public class PluginManagerTest {
    private PluginManager manager;

    @Before
    public void setUp() {
        manager = new DefaultPluginManager();
        manager.addPlugin(WebUIPlugin.class);
        manager.addPlugin(SwingUIPlugin.class);
        manager.addPlugin(SwingXUIPlugin.class);
    }

    @Test
    public void testStart() {
        manager.start();
    }

    @Test
    public void testStop() {
        manager.start();
        manager.stop();
    }

    @Test
    public void testGetServices() {
        manager.start();

        List<UITester> services = manager.getServices(UITester.class);
        assertEquals(2, services.size());
        assertEquals(SwingUITester.class, services.get(1).getClass());
        assertEquals(WebUITester.class, services.get(0).getClass());
        
        List<SwingUITester> swingTester = manager.getServices(SwingUITester.class);
        List<? extends SwingUITask> swingTasks = new ArrayList(swingTester.get(0).getTasks());
        assertEquals(2, swingTasks.size());
        assertEquals(SwingUITask1.class, swingTasks.get(0));
        assertEquals(SwingXUITask1.class, swingTasks.get(1));
        
        List<WebUITester> webTester = manager.getServices(WebUITester.class);
        List<? extends SwingUITask> webTasks = new ArrayList(webTester.get(0).getTasks());
        assertEquals(1, webTasks.size());
        assertEquals(WebUITask1.class, webTasks.get(0));

        List<UITask> tasks = manager.getServices(UITask.class);
        assertEquals(3, tasks.size());
        assertEquals(WebUITask1.class, tasks.get(0).getClass());
        assertEquals(SwingUITask1.class, tasks.get(1).getClass());
        assertEquals(SwingXUITask1.class, tasks.get(2).getClass());
    }
}
