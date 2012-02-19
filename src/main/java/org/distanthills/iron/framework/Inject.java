/**
 * 
 */
package org.distanthills.iron.framework;

import java.lang.annotation.*;

/**
 * @author jacob
 *
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
	Class<?> value();
}
