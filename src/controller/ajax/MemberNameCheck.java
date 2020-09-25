package controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;

/**
 * Servlet implementation class UserNameCheck
 */
@WebServlet("/MemberNameCheck")
public class MemberNameCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberNameCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String membername = request.getParameter("membername");
		MemberDAO memberDAO = new MemberDAO();
		//아이디 중복 체크하는  select
		//result가 1이상인 경우 아이디 중복
		int result = memberDAO.MemberNameCheck(membername);
		
		if(result == 0) {
			//아이디 중복이 없을 경우 ok 신호를 보낸다.
			PrintWriter out = response.getWriter();
			out.print("ok");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
