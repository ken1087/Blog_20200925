package controller.board;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class BoardWriteController
 */
@WebServlet("/boardWriteProc")
public class BoardWriteProcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteProcController() {
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
		
		//글쓰는 사람
		String membername = request.getParameter("membername");
		//글 제목
		String title = request.getParameter("title");
		//글 내용
		String content = request.getParameter("content");
		
		//board 객체 생성한 후 파라메터 데이터를 객체에 담는다.
		Board board = new Board();
		board.setMembername(membername);
		board.setTitle(title);
		board.setContent(content);
		board.setCreateDate(LocalDate.now());
		board.setUpdateDate(LocalDate.now());
		
		//받은 객체를 DB에 저장한다.
		BoardDAO boardDAO = new BoardDAO();
		//result 값이 1일 경우 성공
		int result = boardDAO.boardWrite(board);
		
		if(result == 1) {
			//글쓰기를 성공한 경우 페이지 이동
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			String comment = "글쓰기 에러";
			if(result == 2) {
				//잘못된 접근으로 글을 쓰는 경우의 메세지
				comment = "잘못된 접근입니다";
			}
			//글쓰기를 실패한 경우 alert
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
