package com.github.colindean.java;
/**
** jarloader, by colindean
**
** This is a very simple jar loading class I constructed, referencing a few examples and modifying them to suit my 
** needs. It could probably be improved by having a loadSeveralJars() method, but it suffices for my purposes.
**
** License: CC0 Public Domain. See LICENSE.HTML for more information.
**
**/
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class JarLoader {
        /**
         * Load a single Jar file at the path specified
         *
         * @param String path the path to the jar file, absolute or relative to the currently running application
         */
	static public void loadJar(String path) throws MalformedURLException, FileNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method addURL = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] {URL.class});
		addURL.setAccessible(true);
		File file = new File(path);
		if(!file.exists()) throw new FileNotFoundException(file.getPath());
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		URL url = file.toURI().toURL();
		addURL.invoke(cl, new Object[] { url });
	}
}