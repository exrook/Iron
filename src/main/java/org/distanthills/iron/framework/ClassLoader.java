package org.distanthills.iron.framework;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.io.File;

public class ClassLoader implements ComponentLoader {
	private URLClassLoader loader;
	private Collection<Component> comps = new LinkedList<Component>();
	
	public ClassLoader(URL directory) {
		URL[] directories = new URL[1];
		directories[0] = directory;
		loader = new URLClassLoader(directories);
	}

	public void load() {
		@SuppressWarnings("rawtypes")
		Collection<Class> clas= new ArrayList<Class>();
		for (URL url:loader.getURLs()) {
			File file = new File(url.getPath());
			for (File cf: file.listFiles()) {
				if (cf.isFile() & cf.getName().toLowerCase().endsWith(".class")) {
					try {
						clas.add(loader.loadClass(cf.getName().substring(0, cf.getName().lastIndexOf('.'))));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		for (@SuppressWarnings("rawtypes") Class classs:clas) {
			if (Component.class.isAssignableFrom(classs)) {
				Component comp = null;
				try {
					comp = (Component) classs.newInstance();
					comp.onEnable();
					comps.add(comp);
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void unload() {
		for (Component comp:comps) {
			comp.onDisable();
			comp.cleanup();
			comp = null;
		}
		// TODO Auto-generated method stub
		loader = null;
	}

}
