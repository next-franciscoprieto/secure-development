package com.beeva.validation;

import javax.validation.Constraint;
import javax.validation.constraints.Pattern;
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
@Size(max = Name.MAX)
@Pattern(regexp = "^$|([A-Za-z]*)")
public @interface Name {

	int MAX = 64;

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
