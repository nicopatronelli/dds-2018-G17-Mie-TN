package spark;

import static spark.Spark.get;
import static spark.Spark.port;
 
public class Login {
	
	public static void main(String[] args) {
		port(8080);
		get("/login", (req, res) -> generarLogin(req,res));
	}
	
	public static String generarLogin(Request req, Response res) {
		return "<html>\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n" + 
				"    <!-- Title Page -->  \r\n" + 
				"    <title>Login</title>\r\n" + 
				" \r\n" + 
				"    <!-- CSS -->\r\n" + 
				"    <link rel=\"stylesheet\" href=\"css/reset.css\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"css/animate.css\">\r\n" + 
				"    <link rel=\"stylesheet\" href=\"css/styles.css\"> \r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"    <div id=\"container\">\r\n" + 
				"        <form>\r\n" + 
				"            <!-- Username -->\r\n" + 
				"			<table>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>\r\n" + 
				"					<label for=\"name\">Usuario:</label>\r\n" + 
				"				</td>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"name\">\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>\r\n" + 
				"					<!-- Contraseña -->\r\n" + 
				"					<label for=\"name\">Contraseña:</label>\r\n" + 
				"				</td>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"password\">\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<td>\r\n" + 
				"					<!-- Submit Button -->\r\n" + 
				"					<input type=\"submit\" value=\"Login\">\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"        </form>       \r\n" + 
				"    </div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
	}
	
}