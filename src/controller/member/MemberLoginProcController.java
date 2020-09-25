package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import utils.Script;

/**
 * Servlet implementation class MemberLoginProcController
 */
@WebServlet("/loginProc")
public class MemberLoginProcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginProcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//아이디
		String membername = request.getParameter("membername");
		//패스워드
		String password = request.getParameter("password");
		
		//아이디와 패스워드로 쿼리를 한후 아이디가 몇개인지 판별
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.login(membername, password);
		
		//result값이 1이면 아이디개수도 1이라는 뜻 = 아이디와 패스워드가 맞음
		if(result == 1) {
			//로그인한 아이디 정보를 브라우저의 session data로 저장
			HttpSession session = request.getSession();
			session.setAttribute("membername", membername);
			
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			//아이디와 패스워드가 틀렸을 경우
			String comment = "아이디 혹은 비밀번호가 잘못 입력했습니다.";
			Script script = new Script();
			script.myScript(comment, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
