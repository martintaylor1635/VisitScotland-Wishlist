package com.visitscotland.wishlistapi.validation;

import com.visitscotland.wishlistapi.domain.Category;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<ValidCategory, String> {
    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if(!input.isEmpty()) {
            for(Category category : Category.values()) {
                if(input.equalsIgnoreCase(category.name())) {
                    return true;
                }
            }
        }

        return false;
    }
}