package net.projetx.plugin;

import java.util.Collection;


public interface UITester<T extends UITask> {
	void registerTask(Class<? extends T> task);
	void unregisterTask(Class<? extends T> task);
	
	Collection<Class<? extends T>> getTasks();
}
