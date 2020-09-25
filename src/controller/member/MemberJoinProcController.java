package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import model.Member;
import utils.Script;

/**
 * Servlet implementation class MemberJoinProcController
 */
@WebServlet("/memberJoinProc")
public class MemberJoinProcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinProcController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login.jsp";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//회원 등록할 객체 생성
		Member member = new Member();
		//DB연결&쿼리 준비
		MemberDAO memberDAO = new MemberDAO();
		
		//아이디
		String membername = request.getParameter("membername");
		//패스워드
		String password = request.getParameter("password");
		//이름
		String name = request.getParameter("name");
		//성별
		String gender = request.getParameter("gender");
		//Email
		String email = request.getParameter("email");
		//첫번째 주소
		String address1 = request.getParameter("address1");
		//두번째 주소
		String address2 = request.getParameter("address2");
		//첫번째 주소와 두번째 주소를 더해서 DB에 저장하도록 합친다.
		String address = address1+" "+address2;
		//010,011,016등
		String phone1 = request.getParameter("phone1");
		//두번째 전화번호
		String phone2 = request.getParameter("phone2");
		//세번째 전화번호
		String phone3 = request.getParameter("phone3");
		//첫번째,두번째,세번째전화번호를 합친다. ex) 010-1234-5678
		String phone = phone1+"-"+phone2+"-"+phone3;
		
		//객체에 파라메터 값들을 담는다.
		member.setMembername(membername);
		member.setPassword(password);
		member.setName(name);
		member.setGender(gender);
		member.setEmail(email);
		member.setAddress(address);
		member.setPhone(phone);
		
		//DB연결 후 insert쿼리를 해서 데이터등록 성공할 경우 1
		int result = memberDAO.join(member);
		
		if(result == 1) {
			//페이지 이동
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			String comment = "회원가입 에러";
			if(result == 2) {
				comment = "잘못된 접근입니다";
			}
			//DB Insert가 실패할 경우 alert
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
