package org.distanthills.iron.framework;

import java.net.MalformedURLException;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.jar.JarFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

public class JarLoader implements ComponentLoader {
	private static final Yaml yaml = new Yaml();
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
		URL[] urls;
		urls = jars.toArray(new URL[jars.size()]);
		loader = new URLClassLoader(urls);
		for (File file: files) {
			if (file.isFile() && file.getName().toLowerCase().endsWith(".jar")) {
				JarFile jar;
				try {
					jar = new JarFile(file);
					getConfig(jar);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		//loader.
		// TODO Auto-generated method stub

	}

	public void unload() {
		// TODO Auto-generated method stub

	}
	
	private static Yaml getConfig(JarFile jar) {
		Object dump = null;
		try {
			dump = yaml.load(jar.getInputStream(jar.getEntry("components.yml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
