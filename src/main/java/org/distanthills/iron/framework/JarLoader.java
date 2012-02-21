package org.distanthills.iron.framework;

import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.jar.JarFile;
import java.io.File;

public class JarLoader implements ComponentLoader {
	private URLClassLoader loader;
	private File jardir;
	
	public JarLoader(File directory) {
		this.jardir = directory;
	}

	public void load() {
		File[] files = jardir.listFiles();
		Collection<URL> jars = new LinkedList<URL>();
		for (File file: files) {
			if (file.isFile() && file.getName().toLowerCase().endsWith(".jar")) {
				try {
					jars.add(new URL(file.getAbsolutePath()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		URL[] urls = null;
		urls = jars.toArray(urls);
		loader = new URLClassLoader(urls);
		for (File file: files) {
			if (file.isFile() && file.getName().toLowerCase().endsWith(".jar")) {
				JarFile jar = new JarFile(file);
				jar.getJarEntry("component.yml");
				
			}
		}
		//loader.
		// TODO Auto-generated method stub

	}

	public void unload() {
		// TODO Auto-generated method stub

	}

}
