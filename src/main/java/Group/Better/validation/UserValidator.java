package Group.Better.validation;


import Group.Better.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<ValidUser, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userRepository.findByUsernameLogin(value).isEmpty();
    }
}
