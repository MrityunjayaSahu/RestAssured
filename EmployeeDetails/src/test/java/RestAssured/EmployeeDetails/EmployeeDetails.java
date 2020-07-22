package RestAssured.EmployeeDetails;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeDetails {
	
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
	
	@Test
	public boolean verifyEmployeeStatus() {
		boolean flag = true;
		try {
			JsonPath jPath = new JsonPath(getEmployeeResponse());
			int expectedStatus = jPath.getInt("status");
			Assert.assertEquals(200, expectedStatus);
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
			Assert.assertEquals(actualAge, expectedAge);
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
			Assert.assertEquals(actualRole, expectedRole);
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
			Assert.assertEquals(actualDOB, expectedDOB);
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
			String expectedDOB = jPath.getString("message");
			Assert.assertEquals(actualMsg, expectedDOB);
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

}
