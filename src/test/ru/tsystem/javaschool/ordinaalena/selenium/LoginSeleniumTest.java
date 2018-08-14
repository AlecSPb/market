package ru.tsystem.javaschool.ordinaalena.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class LoginSeleniumTest {
    private WebDriver browser;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "/Tool/chromedriver.exe");
        browser=new ChromeDriver();
    }
    @Test
    public void loginPositiveSeleniumTest1() {
        browser.get(Url.ACCOUNT);

        browser.findElement(By.id("email")).sendKeys("test@gmail.com");
        browser.findElement(By.id("parole")).sendKeys("123456");

        browser.findElement(By.className("knopka01")).click();
        String email = browser.findElement(By.className("email")).getText();
        assertEquals(Url.ACCOUNT, browser.getCurrentUrl());
        assertEquals("test@gmail.com", email);
    }
    @Test
    public void loginNegativeSeleniumTest2() {
        browser.get(Url.ACCOUNT);

        browser.findElement(By.id("email")).sendKeys("test@gmail.com");
        browser.findElement(By.id("parole")).sendKeys("1236");

        browser.findElement(By.className("knopka01")).click();

        assertEquals(Url.LOGIN + "?error", browser.getCurrentUrl());
    }
    @After
    public void destroy() {
        browser.close();
    }
}
