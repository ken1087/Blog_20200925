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
import model.Board;
import utils.Script;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/updateForm")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/update.jsp";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//업데이트할 board 번호
		String boardid = request.getParameter("boardid");
		//등록한 사람의 아이디
		String membername = request.getParameter("membername");
		//로그인 한 아이디
		HttpSession session = request.getSession();
		String sessionname = "";
		try {
			sessionname = session.getAttribute("membername").toString(); 
		} catch (Exception e) {}
		//로그인 한 아이디와 등록한 사람의 아이디가 같을 경우 수정페이지 이동 가능
		if(sessionname.equals(membername)) {
			//업데이트 할 board의 데이터를 받는다.
			BoardDAO boardDAO = new BoardDAO();
			Board board = boardDAO.boardObject(boardid);
			
			if(board != null) {
				//board데이터가 있을 경우 JSP로 board 객체를 넘겨준다.
				request.setAttribute("board", board);
				//페이지 이동
				RequestDispatcher dis = 
						request.getRequestDispatcher(url);
				dis.forward(request, response);
			}else {
				//board 데이터가 없을 경우 (DB문제)
				String comment = "board를 찾지 못하였습니다.";
				Script script = new Script();
				script.myScript(comment, response);
			}
		}else {
			//로그인한 아이디와 게시글을 등록한 아이디가 틀렸을 경우
			String comment = "권한이 없습니다";
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
