package com.epam.webch.model.util;

import org.testng.annotations.Test;

import javax.xml.validation.Validator;

import static org.testng.Assert.*;

public class DataValidatorTest {

    @Test
    public void testCheckEmailOnCorrectness() {
       final String email = "iii@mail.ru";
       boolean valid= DataValidator.checkEmailOnCorrectness(email);
       assertTrue(valid);
    }
    @Test
    public void testCheckEmailOnIncorrectness() {
        final String email = "issfqwfqwf.ru";
        boolean notValid= DataValidator.checkEmailOnCorrectness(email);
        assertFalse(notValid);
    }

}