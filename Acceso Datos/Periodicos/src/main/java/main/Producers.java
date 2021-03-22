package main;

import config.Configuration;

import javax.enterprise.inject.Produces;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


public class Producers {


    @Produces
    public Configuration createConfiguration() {

        return Configuration.getInstance();
    }

    @Produces
    public Validator createValidator()
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

}
