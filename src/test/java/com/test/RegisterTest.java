package com.test;

import com.google.common.base.Verify;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest {
    //Get URL base
    private final String URL_REGISTER = "http://localhost:5173/register";
    private final String URL_SUCCESS = "http://localhost:5173/success";
    private WebDriver driver;
    private WebElement fullNameInput;
    private WebElement emailInput;
    private WebElement passwordInput;
    private WebElement confirmPasswordInput;
    private WebElement submitButton;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(URL_REGISTER);

        Thread.sleep(500);

        fullNameInput = driver.findElement(By.xpath("//*[@id=\"form\"]/div[1]/input"));
        emailInput = driver.findElement(By.xpath("//*[@id=\"form\"]/div[2]/input"));
        passwordInput = driver.findElement(By.xpath("//*[@id=\"form\"]/div[3]/input"));
        confirmPasswordInput = driver.findElement(By.xpath("//*[@id=\"form\"]/div[4]/input"));
        submitButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/button"));
    }

    @AfterMethod
    public void cleanUp(){
        driver.quit();
    }
    @Test
    public void accessRegisterPage() throws InterruptedException {
        //Create driver
        WebDriver driver = new ChromeDriver();
        driver.get(URL_REGISTER);

        Thread.sleep(100);

        WebElement title = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/h1"));
        String actualTitle = title.getText();
        Assert.assertEquals(actualTitle, "Register");
    }

    @Test
    public void registerMemberSuccess() throws InterruptedException {
        fullNameInput.sendKeys("Tuyet Vi");
        emailInput.sendKeys(System.currentTimeMillis() + "PASS@gmail.com");
        passwordInput.sendKeys("1234");
        confirmPasswordInput.sendKeys("1234");

        submitButton.click();
        Thread.sleep(3000);

        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, URL_SUCCESS);
    }

    @Test
    public void RegisterWithoutFullName() throws InterruptedException {
        emailInput.sendKeys(System.currentTimeMillis() + "PASS@gmail.com");
        passwordInput.sendKeys("1234");
        confirmPasswordInput.sendKeys("1234");
        submitButton.click();
        Thread.sleep(3000);

        WebElement mess = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/span"));
        String actual_URL = driver.getCurrentUrl();
        String actualMess = mess.getText();
        Assert.assertEquals(actual_URL, URL_REGISTER);
        Assert.assertEquals(actualMess, "Full name is required");
    }

    @Test
    public void InvalidEmail() throws InterruptedException {
        fullNameInput.sendKeys("Nguyen Vi");
        emailInput.sendKeys(System.currentTimeMillis() + "PASS");
        passwordInput.sendKeys("1234");
        confirmPasswordInput.sendKeys("1234");
        submitButton.click();
        Thread.sleep(3000);

        WebElement mess = driver.findElement(By.xpath("//*[@id=\"message\"]/span"));
        String actualMess = mess.getText();
        Verify.verify(actualMess.equals("Invalid email"));
    }
}
