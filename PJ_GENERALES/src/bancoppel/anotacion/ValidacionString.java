package bancoppel.anotacion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidacionString {

	TipoDato tipoDato() default TipoDato.CARACTER;
	String patron() default "";
	String etiqueta() default "";

}
