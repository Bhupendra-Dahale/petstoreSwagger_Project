package api.EndPoints;

/* 
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";
		
	//User
	public static String create_user_url = base_url + "/user";
	public static String get_user_url = base_url + "/user/{username}";
	public static String update_user_url = base_url + "/user/{username}";
	public static String delete_user_url = base_url + "/user/{username}";
}
