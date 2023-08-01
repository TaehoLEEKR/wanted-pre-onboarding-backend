package com.example.wantedpreonboardingbackend.component.jwt;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {

    boolean required() default true;
}
