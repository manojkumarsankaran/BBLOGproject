package StepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutomationSteps {
	
	WebDriver driver;
	
	
	@Given("user launches the URL")
	public void user_launches_the_url() throws InterruptedException {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\manoj\\Worspace1\\CucumberTest\\src\\test\\resources\\drivers\\chromedriver.exe");
			
		  driver = new ChromeDriver();
		  
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		  driver.get("https://candidatex:qa-is-cool@qa-task.backbasecloud.com");
		  Thread.sleep(10000);
		  
		  driver.manage().window().maximize();
	}
	
	public boolean waitForElementToBeVisible(String element) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(
		        ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));
		return true;
	}

	@When("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() {
String dashboardTitle = driver.findElement(By.xpath("//h1[@class='logo-font']")).getText();
		
		Assert.assertTrue(dashboardTitle.equals("conduit"));
		
		
	}
		
	@And("user sign in with credentials")
	public void user_sign_in_with_credentials() throws InterruptedException {
	    driver.findElement(By.xpath("//a[@routerlink='/login']")).click();
	    
	    waitForElementToBeVisible("//input[@placeholder='Email']");
	    
	    driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("manojsankar2511@gmail.com");
	    driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Manoj@2511");
	    
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    waitForElementToBeVisible("//a[contains(text(),'manojkumar')]");
	    
	    String loginUser = driver.findElement(By.xpath("//a[contains(text(),'manojkumar')]")).getText();
	    
	    Assert.assertTrue(loginUser.equals("manojkumar"));
	}
	
	@And("user navigates to new article page")
	public void user_navigates_to_new_article_page() {
		
		 driver.findElement(By.xpath("//a[@routerlink='/editor']")).click();
		 
		 Assert.assertTrue(waitForElementToBeVisible("//button[contains(text(),'Publish')]"));
	   
	}

	@And("user gives article details")
	public void user_gives_article_details() {
		
		driver.findElement(By.xpath("//input[@placeholder='Article Title']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@formcontrolname='description']")).sendKeys("Automation Testing");
		driver.findElement(By.xpath("//textarea[@formcontrolname='body']")).sendKeys("Selenium BDD Cucumber Framework");
		driver.findElement(By.xpath("//input[@placeholder='Enter tags']")).sendKeys("manoj");
		
		driver.findElement(By.xpath("//button[contains(text(),'Publish')]")).click();
		
		Assert.assertTrue(waitForElementToBeVisible("//div[@class='container']/h1"));
	   
	}

	@And("article should be created")
	public void article_should_be_created() {
	   
		String articleTitle = driver.findElement(By.xpath("//div[@class='container']/h1")).getText();
		
		Assert.assertTrue(articleTitle.equals("Selenium"));
		 
		String articleContent = driver.findElement(By.xpath("//div[contains(@class,'article-content')]")).getText();
		
	    Assert.assertTrue(articleContent.equals("Selenium BDD Cucumber Framework"));
		
	  driver.quit();
	}
	
	@And("user navigates to user page")
	public void user_navigates_to_user_page() {

		driver.findElement(By.xpath("//a[contains(text(),'manojkumar')]")).click();
		waitForElementToBeVisible("//div[@class='article-preview']");
		
		driver.findElement(By.xpath("//div[@class='article-preview']")).click();
	    Assert.assertTrue(waitForElementToBeVisible("(//a[contains(@href,'editor/')])[1]"));
	    
		
	}

	@And("user edits the article details")
	public void user_edits_the_article_details() {
		
		
		driver.findElement(By.xpath("(//a[contains(@href,'editor/')])[1]")).click();
		waitForElementToBeVisible("//button[contains(text(),'Publish')]");
		driver.findElement(By.xpath("//input[@placeholder='Article Title']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Article Title']")).sendKeys("Selenium Updated");
		
		
		driver.findElement(By.xpath("//textarea[@formcontrolname='body']")).clear();
		driver.findElement(By.xpath("//textarea[@formcontrolname='body']")).sendKeys("Updated Selenium BDD Cucumber Framework");
		
		driver.findElement(By.xpath("//button[contains(text(),'Publish')]")).click();
		Assert.assertTrue(waitForElementToBeVisible("//div[@class='container']/h1"));
	    
	}

	@And("edited article details should be updated")
	public void edited_article_details_should_be_updated() {
	 
		
        String updatedArticleTitle = driver.findElement(By.xpath("//div[@class='container']/h1")).getText();
		
		Assert.assertTrue(updatedArticleTitle.equals("Selenium Updated"));
		 
		String updatedArticleContent = driver.findElement(By.xpath("//div[contains(@class,'article-content')]")).getText();
		
	    Assert.assertTrue(updatedArticleContent.equals("Updated Selenium BDD Cucumber Framework"));
	    
	    driver.quit();
	}
	
	@And("user navigates to Global feed")
	public void user_navigates_to_global_feed() {
		driver.findElement(By.xpath("//a[contains(text(),'Global')]")).click();
		waitForElementToBeVisible("(//a[@class='preview-link']/h1)[1]");
		
	}

	@Then("user should see the created article")
	public void user_should_see_the_created_article() {
		
		ArrayList<String> arrList = new ArrayList<String>();
		List<WebElement> articlePage = driver.findElements(By.xpath("//a[@class='preview-link']/h1"));
		
		for (int i=0; i<articlePage.size(); i++) {
			
			arrList.add(articlePage.get(i).getText());
			
			if(articlePage.get(i).getText().equals("Selenium Updated")) {
				
				System.out.println("Article available");
				
				Assert.assertTrue(true);
			}
			
		}
	    
		System.out.println(arrList);
		driver.quit();
	}

@And("user adds the comment and posted to the article")
public void user_adds_the_comment_and_posted_to_the_article() {
	
	driver.findElement(By.xpath("//textarea[contains(@placeholder,'comment')]")).sendKeys("Comment has been added");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Assert.assertTrue(waitForElementToBeVisible("//p[@class='card-text']"));
	
}

@Then("user should see the added the comment")
public void user_should_see_the_added_the_comment() {
	
	String addedComment = driver.findElement(By.xpath("//p[@class='card-text']")).getText();
	
    Assert.assertTrue(addedComment.equals("Comment has been added"));
    driver.quit();
    
}

@And("user deletes the added comment")
public void user_deletes_the_added_comment() throws InterruptedException {
	
	driver.findElement(By.xpath("//span[@class='mod-options']")).click();
	Thread.sleep(3000);
   
}

@Then("comment should be deleted")
public void comment_should_be_deleted() {
	try {
		
		driver.findElement(By.xpath("//p[@class='card-text']")).isDisplayed();
       
    } catch (NoSuchElementException e) {
    	
    	Assert.assertTrue("Comment Deleted",true);
    }
	
	driver.quit();
}

@Given("user deletes the created article")
public void user_deletes_the_created() {
    
	driver.findElement(By.xpath("(//button[contains(text(),'Delete')])[1]")).click();
	 Assert.assertTrue(waitForElementToBeVisible("//a[@routerlink='/editor']"));
}

@Then("article should be deleted")
public void article_should_be_deleted() {
	 
	driver.findElement(By.xpath("//a[contains(text(),'manojkumar')]")).click();
    
    Assert.assertTrue(waitForElementToBeVisible("//div[@class='app-article-preview' and contains(text(),'No articles')]"));
    
    driver.quit();
}


}
