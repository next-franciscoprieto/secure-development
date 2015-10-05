package com.beeva.validation;

import javax.validation.Constraint;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Annotation for Cardholder.
 * 
 * @author BEEVA
 * 
 */
@Documented
@Constraint(validatedBy = {})
@Target({ FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Size(max = Message.MAX)
public @interface Message {

	int MAX = 5000;

	/**
	 * @return
	 */
	String message() default "{name.validation.error}";

	/**
	 * @return
	 */
	Class<?>[] groups() default {};

	/**
	 * @return
	 */
	Class<?>[] payload() default {};

}
