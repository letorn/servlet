package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import service.UserService;

public class UserServlet extends HttpServlet {

	private UserService userService = new UserService();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		if (action.equals("page.do")) {
			response.setContentType("application/x-json; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			Integer start = Integer.parseInt(request.getParameter("start"));
			Integer limit = Integer.parseInt(request.getParameter("limit"));
			Map<String, Object> result = userService.page(start, limit);

			JSONObject jsonObject = new JSONObject(result);
			out.print(jsonObject.toString());
			out.close();
		}
	}
}
