package Group.Better.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface ValidUser {

    String message() default "入力されたユーザー名はすでに登録されています。別のユーザー名を入力してください";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}