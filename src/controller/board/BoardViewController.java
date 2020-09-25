package controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import model.Board;
import utils.Script;

/**
 * Servlet implementation class BoardViewController
 */
@WebServlet("/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/view.jsp";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//상세보기할 board번호
		String boardid = request.getParameter("boardid");
		//로그인한 아이디
		String session = request.getParameter("session");
		
		//상세볼 board객체 생성
		BoardDAO boardDAO = new BoardDAO();
		//유니크한 boardid로 board의 데이터중 하나를 select하고 데이터를 취득한다.
		Board board = boardDAO.boardView(boardid);
		
		if(board != null) {
			//취득한 데이터를 JSP로 보낸다.
			request.setAttribute("board", board);
			//JSP페이지에서 수정 삭제버튼을 볼 수 있는 권한이 있는지 확인하기 위해
			//board를 등록한 아이디와 session의 아이디가 다를 경우 볼 수 없음
			request.setAttribute("myname",session);
			
			//페이지 이동
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			//게시글이 없을 경우 alert
			String comment = "게시글이 없습니다.";
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
