
# Project Title

This is  the rest assured project for api automation added the List user api test cases in this project 

## Run Locally

1] Clone the project from the github link

2] Import the project in IDE

3] Go to the project directory

4] Install all maven dependencies 

5] Go to the GetListUser class inside src/test/java package

6] Run the class locally as testng test


## Test cases 

1] @VerifyUserListIsNotEmpty

    /*
	 * This Test is to verify the user list is not empty here we are
	    hitting the
	 * list user api and checking the total page count should be
	  greater then 0 And
	 * if page count is 0 it will fail the test case
	 */

2] @VerifyUserPagewiseDataList

    /*
	 * This Test is to verify the page wise data list is not empty here
	  we are hitting the
	 * list user api one by one based on the page count e.g.Page=1,Page=2
	 */

3] @VerifyTheLIstForAccessTotalPage

    /*
	 * This Test is to verify data list is empty when we are
	 * hitting the list user api with the page count more than total 
	   pages 
	 * e.g page=3
	 */