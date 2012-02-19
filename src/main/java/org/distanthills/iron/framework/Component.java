package org.distanthills.iron.framework;

import java.lang.reflect.Field;

public abstract class Component {
	public void onEnable() {
		System.out.println(this.getClass().getName() + ": Enabled!");
	}
	public void onDisable() {
		this.cleanup();
		System.out.println(this.getClass().getName() + ": Enabled!");
	}
	public void cleanup() {
		for (Field field:this.getClass().getDeclaredFields()) {
			try {
				field.set(null, null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
