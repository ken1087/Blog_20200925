package controller.board;

import java.io.IOException;
import java.time.LocalDate;

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
 * Servlet implementation class BoardUpdateProcController
 */
@WebServlet("/boardUpdateProc")
public class BoardUpdateProcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateProcController() {
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

		//업데이트할 board번호 String으로 받았기때문에 int타입으로 변경한다.
		int boardid = Integer.parseInt(request.getParameter("boardid"));
		//업데이트할 제목
		String title = request.getParameter("title");
		//업데이트할 내용
		String content = request.getParameter("content");
		//작성자 (혹시 작성한사람이 다른사람일 경우 확인 가능)
		String membername = request.getParameter("membername");
		//작성자가 null일 경우는 업데이트 못하게끔 한다.
		//로그인 한 아이디
		HttpSession session = request.getSession();
		String sessionname = "";
		try {
			sessionname = session.getAttribute("membername").toString();
		} catch (Exception e) {}
		
		if(sessionname.equals(membername)) {
			//board객체 생성
			Board board = new Board();
			//DB연결&쿼리 준비
			BoardDAO boardDAO = new BoardDAO();
			//객체에 파라메터 값을 담는다.
			board.setBoardid(boardid);
			board.setMembername(membername);
			board.setTitle(title);
			board.setContent(content);
			board.setUpdateDate(LocalDate.now());
			//DB에 쿼리를 한다음, 결과값을 받는다 1일경우 성공
			int result = boardDAO.boardUpdate(board);
			
			if(result == 1) {
				//페이지 이동
				RequestDispatcher dis = 
						request.getRequestDispatcher(url);
				dis.forward(request, response);
			}else {
				//업데이트 실패할 경우 alert
				String comment = "수정 에러";
				Script script = new Script();
				script.myScript(comment, response);
			}
		}else {
			//업데이트 실패할 경우 alert
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
