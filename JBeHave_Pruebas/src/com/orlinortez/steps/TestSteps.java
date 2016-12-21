package com.orlinortez.steps;

import java.io.File;
import java.io.IOException;
import com.orlinortez.test.Step;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import com.orlinortez.test.Step;


public class TestSteps {

	@Autowired
	WebDriver dr;

	@Given("I go to \"$url\"")
	public void Goto(String url) throws IOException {
		/*System.setProperty("webdriver.chrome.driver",
				"C:\\chromedriver_win32\\chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();
		dr.get(url);*/
		
		File src = new File("C:/Phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
		dr = new PhantomJSDriver();
		dr.get(url);
	}

	
	@When("I enter username as \"$strArg1\" and password \"$strArg2\"")
	public void whenIEnterUsernameAsusuarioAndPasswordusuario(String strArg1,
			String strArg2) {
		dr.findElement(By.id("username")).sendKeys(strArg1);
		dr.findElement(By.id("password")).sendKeys(strArg2);
	}

	@When("I press Login")
	public void i_press_login() {
		dr.findElement(By.className("btn-primary")).click();

	}

	@Then("I should go to \"$ur\"")
	public void i_should_go_to_something(String ur) {
		System.out.println("URL: " + dr.getCurrentUrl());
	}
	// ----------------- LOGUIN FALLIDO
	// ------------------------------------------
	
	@Then("I go to \"$url\"")
	public void GotoInv(String url) {
		/*System.setProperty("webdriver.chrome.driver",
				"C:\\chromedriver_win32\\chromedriver.exe");
		dr = new ChromeDriver();
		dr.manage().window().maximize();*/
		File src = new File("C:/Phantomjs/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
		dr = new PhantomJSDriver();
		dr.get(url);
	}
	
	@When("I enter username as \"$strArg3\" and password \"$strArg4\"")
	public void whenIEnterUsernameAsusuarioAndPasswordusuarioInv(String strArg3,
			String strArg4) {
		dr.findElement(By.id("username")).sendKeys(strArg3);
		dr.findElement(By.id("password")).sendKeys(strArg4);
	}
	
	@Then("I press Login")
	public void i_press_loginInv() {
		dr.findElement(By.className("btn-primary")).click();
	}
	
	@Then("I should go to \"$ur2\"")
	public void i_should_go_to_somethingInv(String ur2) {
		System.out.println("URL: " + dr.getCurrentUrl());
		//Assert.assertEquals(ur2,dr.getCurrentUrl());
	}
}
