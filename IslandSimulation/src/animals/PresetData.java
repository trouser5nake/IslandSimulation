package animals;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PresetData {
    double weight();

    double maxSatiety();

    int maxAreaPopulation();

    int speed() default 0;
}
