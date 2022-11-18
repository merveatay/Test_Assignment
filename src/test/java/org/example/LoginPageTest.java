package org.example;

import org.junit.Test;

public class LoginPageTest extends PageTest{


    @Test
    public void login(){
        new LoginPage(driver, driver).login("test@gmail.com","test123");
    }

}