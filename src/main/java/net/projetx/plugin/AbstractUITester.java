package net.projetx.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.projetx.plugin.PluginManager;

public abstract class AbstractUITester<T extends UITask> implements UITester<T> {
	private final List<Class<? extends T>> tasks = new ArrayList<Class<? extends T>>();
	private final PluginManager manager;
	
	public AbstractUITester(PluginManager manager) {
		System.out.println("new " + getClass().getName());
		this.manager = manager;
	}

	@Override
	public final void registerTask(Class<? extends T> task) {
		tasks.add(task);
		
		//TODO should we register task in a child container instead ?
		manager.registerService(task);
	}

	@Override
	public final void unregisterTask(Class<? extends T> task) {
		tasks.remove(task);
	}

	@Override
	public final Collection<Class<? extends T>> getTasks() {
		return tasks;
	}
}
