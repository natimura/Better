package Group.Better.validation;

import Group.Better.form.PostForm;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ChoiceValidator implements ConstraintValidator<ValidChoice, PostForm> {

    @Override
    public void initialize(ValidChoice constraintAnnotation) {
    }

    @Override
    public boolean isValid(PostForm postForm, ConstraintValidatorContext context) {
        if (postForm.getChoices().get(0).getChoiceContent() == null ||
                postForm.getChoices().get(1).getChoiceContent() == null) {
            return false;
        }

        return true;
    }
}
