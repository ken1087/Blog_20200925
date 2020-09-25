package controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import utils.Script;

/**
 * Servlet implementation class BoardDeleteProc
 */
@WebServlet("/deleteProc")
public class BoardDeleteProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteProc() {
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
		
		//삭제할 board 번호
		String boardid = request.getParameter("boardid");
		//등록한 사람의 아이디
		String membername = request.getParameter("membername");
		//로그인 한 아이디
		HttpSession session = request.getSession();
		String sessionname = "";
		try {
			sessionname = session.getAttribute("membername").toString(); 
		} catch (Exception e) {}
		//로그인 한 아이디와 등록한 사람의 아이디가 같을 경우 삭제 가능
		if(sessionname.equals(membername)) {
			BoardDAO boardDAO = new BoardDAO();
			//삭제가 성곡한 경우 1의 결과값을 받는다.
			int result = boardDAO.boardDelete(boardid);
			
			if(result == 1) {
				//페이지 이동
				RequestDispatcher dis = 
						request.getRequestDispatcher(url);
				dis.forward(request, response);
			}else {
				//삭제가 안됬을 경우 (DB문제)
				String comment = "삭제 에러";
				Script script = new Script();
				script.myScript(comment, response);
			}
		}else {
			//로그인한 아이디와 등록한 아이디가 틀렸을 경우
			String comment = "권한이 잘못 되었습니다.";
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
