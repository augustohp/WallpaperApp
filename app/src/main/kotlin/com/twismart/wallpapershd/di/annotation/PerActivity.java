package com.twismart.wallpapershd.di.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sneyd on 6/6/2017.
* */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}