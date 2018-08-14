package ru.tsystem.javaschool.ordinaalena.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class SignUpSeleniumTest {
    private WebDriver browser;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "/Tool/chromedriver.exe");
        browser = new ChromeDriver();
    }

    @Test
    public void SignUpPositiveSeleniumTest1() {
        browser.get(Url.ACCOUNT);

        browser.findElement(By.className("sign-up")).findElement(By.tagName("a")).click();

        browser.findElement(By.id("email")).sendKeys("someEmail01@gmail.com");
        browser.findElement(By.id("parole")).sendKeys("11111");
        browser.findElement(By.id("paroleConfirm")).sendKeys("11111");
        browser.findElement(By.className("knopka01")).click();
        assertEquals(Url.ACCOUNT, browser.getCurrentUrl());
        assertEquals("someEmail01@gmail.com", browser.findElement(By.className("email")).getText());
    }


    @After
    public void destroy() {
        browser.close();
    }
}

