package RestAssured.EmployeeDetails;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Mrutyunjaya
 *
 *	Test Scenario:
	Following is a REST API with no parameters and its response is in Json format.  
	
	Endpoint = http://demo4032024.mockable.io/apitest
	Method = GET
	                                        
	Assertion 1 : Validate Response Status code as 200
	Assertion 2 : Validate Response Header for JSON response
	Assertion 3 : Validate Response body with following data using (JsonPath/ JsonParser/ etc.) -
	Status=200
	Age= 25
	Role=QA Automation Developer
	Dob=25-02-1994
	Message=data retrieved successful
	Assertion 4 : Validate Response body with following data
	              Company=ABC Infotech
 *
 */

public class EmployeeDetails {
	
	@Test
	public boolean verifyEmployeeStatus() {
		boolean flag = true;
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			int expectedStatus = jPath.getInt("status");
			Assert.assertEquals(200, expectedStatus, " ACTUAL AND EXPECTED STATUS OF EMPLOYEE FAILED TO MATCH ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	
	@Test
	public boolean verifyEmployeeAge() {
		boolean flag = true;
		int actualAge = 25;
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			int expectedAge = jPath.getInt("employeeData.age");
			Assert.assertEquals(actualAge, expectedAge, " ACTUAL AND EXPECTED AGE OF EMPLOYEE FAILED TO MATCH ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	
	@Test
	public boolean verifyEmployeeRole() {
		boolean flag = true;
		String actualRole = "[QA Automation Developer]";
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			String expectedRole = jPath.getString("employeeData.role");
			Assert.assertEquals(actualRole, expectedRole, " ACTUAL AND EXPECTED ROLE OF EMPLOYEE FAILED TO MATCH ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	
	@Test
	public boolean verifyEmployeeDOB() {
		boolean flag = true;
		String actualDOB = "[25-02-1994]";
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			String expectedDOB = jPath.getString("employeeData.dob");
			Assert.assertEquals(actualDOB, expectedDOB, " ACTUAL AND EXPECTED DOB OF EMPLOYEE FAILED TO MATCH ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	
	@Test
	public boolean verifyEmployeeDataRetrievedMesage() {
		boolean flag = true;
		String actualMsg = "data retrieved successful";
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			String expectedMsg = jPath.getString("message");
			Assert.assertEquals(actualMsg, expectedMsg, " ACTUAL AND EXPECTED MESSAGE OF EMPLOYEE FAILED TO MATCH ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	
	@Test
	public boolean verifyEmployeeCompany() {
		boolean flag = true;
		String actualCompany = "[ABC Infotech]";
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			String expectedCompany = jPath.getString("employeeData.company");
			Assert.assertEquals(actualCompany, expectedCompany,"ACTUAL COMPANY NAME IS : " + actualCompany + " AND EXPECTED COMPANY NAME IS : " + expectedCompany + " NOT EQUAL.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/**
	 * @description This is the Common Method for Every Test script
	 * @return Employee Response
	 * @throws Exception
	 */
	public String getEmployeeResponse() {
		try {
			RestAssured.baseURI = "http://demo4032024.mockable.io";
			String emplData = given().log().all().
			when().get("/apitest").
			then().log().all().extract().response().asString();
			return emplData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
