package com.validation;

import java.util.Set;

import org.junit.jupiter.api.Test;

import jakarta.validation.metadata.BeanDescriptor;
import jakarta.validation.metadata.ConstructorDescriptor;

public class BeanDescriptorTest extends AbstracValidatorTest { 
    
    @Test
    public void testBeanDescriptior() {
        BeanDescriptor beanDescriptor = this.validator.getConstraintsForClass(Person.class);
        Set<ConstructorDescriptor> constrainedConstructors = beanDescriptor.getConstrainedConstructors();

        /**
         * dan masih banyak lagi informasi yang bisa kita dapatkan
        */
        constrainedConstructors.forEach(constrain -> {
            System.out.println(constrain.getName());
            constrain.getConstraintDescriptors().forEach(d -> {
                System.out.println(d.getMessageTemplate());
                System.out.println(d.getAnnotation());
            });
        });
    }
}
