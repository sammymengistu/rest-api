package Automation.RestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;


public class GetListUser {
	int totalPage=0;
	
	@BeforeMethod
    public static void setup() {
		RestAssured.baseURI="https://reqres.in/";
    }
	
	/*
	 * This Test is to verify the user list is not empty here we are hitting the
	 * list user api and checking the total page count should be greater then 0 And
	 * if page count is 0 it will fail the test case
	 */

	@Test(priority = 1)
	public void VerifyUserListIsNotEmpty() {
		Response response = given().log().all().when().get("/api/users").then().extract().response();
		Assert.assertEquals(200, response.statusCode(), "status code is not 200 ");
		totalPage = response.jsonPath().getInt("total_pages");
		Assert.assertTrue(totalPage > 0, "user list is empty");
	}

	/*
	 * This Test is to verify the page wise data list is not empty here we are hitting the
	 * list user api one by one based on the page count e.g.Page=1,Page=2
	 */
	@Test(priority = 2)
	public void VerifyUserPagewiseDataList() {
		for (int i = 1; i <= totalPage; i++) {
			Response response = given().log().all().queryParam("page", i).when().get("/api/users").then().extract()
					.response();
			Assert.assertEquals(200, response.statusCode(), "status code is not 200");
			Assert.assertEquals(i, response.jsonPath().getInt("page"), "list page is mismatched");
			JsonPath jsonPathEvaluator = response.jsonPath();
			List<Map<String, Object>> dataList = jsonPathEvaluator.getList("data");
			Assert.assertFalse(dataList.isEmpty());
		}
	}

	/*
	 * This Test is to verify data list is empty when we are
	 * hitting the list user api with the page count more than total pages 
	 * e.g page=3
	 */
	@Test(priority = 3)
	public void VerifyTheLIstForAccessTotalPage() {
		totalPage = totalPage + 1;
		Response response = given().log().all().queryParam("page", totalPage).when().get("/api/users").then().extract()
				.response();
		Assert.assertEquals(200, response.statusCode(), "status code is not 200");
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Map<String, Object>> dataList = jsonPathEvaluator.getList("data");
		Assert.assertTrue(dataList.isEmpty(), "List is not empty");

	}
}
