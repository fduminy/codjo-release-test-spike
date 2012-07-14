package net.projetx.plugin.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.projetx.plugin.UITask;
import net.projetx.plugin.UITester;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class PluginManagerTest {
    private DefaultPluginManager manager;

    @Before
    public void setUp() {
        manager = new DefaultPluginManager();
        manager.addPlugins(new ServiceLoaderPluginFinder());
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
    @SuppressWarnings("unchecked")
    public void testGetServices() throws Exception {
    	String swingPkg = "net.projetx.plugin.swing.";
    	String swingTesterCls = swingPkg + "SwingUITester";
    	String webPkg = "net.projetx.plugin.web.";
    	String webTesterCls = webPkg + "WebUITester";
        String swingUITask1 = "net.projetx.plugin.swing.SwingUITask1";
        String swingXUITask1 = "net.projetx.plugin.swingx.SwingXUITask1";
        String webUITask1 = webPkg + "WebUITask1";
        
        manager.start();

        List<UITester> services = manager.getServices(UITester.class);
        assertEquals(2, services.size());
        assertEquals(swingTesterCls, services.get(0).getClass().getName());
        assertEquals(webTesterCls, services.get(1).getClass().getName());
        
		List<UITester<UITask>> swingTester = (List<UITester<UITask>>) manager.getServices(Class.forName(swingTesterCls));
        List<Class<? extends UITask>> swingTasks = new ArrayList(swingTester.get(0).getTasks());
        assertEquals(2, swingTasks.size());
        assertEquals(swingUITask1, swingTasks.get(0).getName());
        assertEquals(swingXUITask1, swingTasks.get(1).getName());
        
        List<UITester<UITask>> webTester = (List<UITester<UITask>>) manager.getServices(Class.forName(webTesterCls));
        List<Class<? extends UITask>> webTasks = new ArrayList(webTester.get(0).getTasks());
        assertEquals(1, webTasks.size());
        assertEquals(webUITask1, webTasks.get(0).getName());

        List<UITask> tasks = manager.getServices(UITask.class);
        assertEquals(3, tasks.size());
        assertEquals(swingUITask1, tasks.get(0).getClass().getName());
        assertEquals(swingXUITask1, tasks.get(1).getClass().getName());
        assertEquals(webUITask1, tasks.get(2).getClass().getName());
    }
}
