package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import utils.DBConnection;
import utils.StringToLocalDate;

public class BoardDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * methodName:boardList
	 * @return list
	 * @return null
	 * */
	public List<Board> boardList(){
		//쿼리 작성
		final String SQL = "SELECT boardid,membername,title,createDate,updateDate FROM board ORDER BY boardid DESC";
		//DB연결
		Connection conn = DBConnection.getConnection();
		
		try {
			//DB에서 받아온 데이터를 넣을 List객체 생성
			List<Board> list = new ArrayList<Board>();
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//쿼리 시작
			rs = pstmt.executeQuery();
			//데이터를 한줄씩 읽는다.
			while(rs.next()) {
				//한줄당 board객체 하나에 데이터를 넣는다.
				Board board = new Board();
				board.setBoardid(rs.getInt("boardid"));
				board.setMembername(rs.getString("membername"));
				board.setTitle(rs.getString("title"));
				board.setCreateDate(StringToLocalDate.format(rs.getString("createDate")));
				board.setUpdateDate(StringToLocalDate.format(rs.getString("updateDate")));
				list.add(board);
			}
			//모든 데이터를 다 읽고 List에 담은 후 반환한다.
			return list;
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		} finally {
			//DB종료
			DBConnection.close(conn, pstmt,rs);
		}
		//DB가 실패하면 null를 반환
		return null;
	}
		
	/**
	 * methodName:boardWrite
	 * @param board
	 * @return result
	 * @return -1
	 * */
	public int boardWrite(Board board) {
		//쿼리 작성
		final String SQL = "INSERT INTO board(boardid,membername,title,content,createDate,updateDate)values(board_seq.nextval,?,?,?,?,?)";
		//DB연결
		Connection conn = DBConnection.getConnection();
		MemberDAO memberDAO = new MemberDAO();
		//작성자 검사 (잘못된 접근으로 등록 방지)
		int memberNameCheck = memberDAO.MemberNameCheck(board.getMembername());
		if(memberNameCheck != 0) {
			try {
				//쿼리 등록
				pstmt = conn.prepareStatement(SQL);
				//?에 파라메터를 대입한다.
				pstmt.setString(1, board.getMembername());
				pstmt.setString(2, board.getTitle());
				pstmt.setString(3, board.getContent());
				pstmt.setString(4, board.getCreateDate().toString());
				pstmt.setString(5, board.getUpdateDate().toString());
				
				//result = 0 fail, result = 1 success
				//트랜잭션 commit을한다.
				int result = pstmt.executeUpdate(); 
				//쿼리가 성공할 경우 1을 반환한다
				return result;
			} catch (Exception e) {
				//에러 내용
				e.printStackTrace();
			}finally {
				//DB종료
				DBConnection.close(conn, pstmt);
			}
		}else {
			//잘못된 접근으로 가입하여 글을 쓰려는 경우
			return 2;
		}
		//쿼리가 실패할 경우 -1을 반환한다
		return -1;
	}
	
	/**
	 * methodName:boardView
	 * @param boardid
	 * @return board
	 * @return null
	 * */
	public Board boardView(String boardid) {
		//쿼리 작성
		final String SQL = "SELECT boardid,membername,title,content,createDate,updateDate FROM board WHERE boardid = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		
		try {
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터를 대입
			pstmt.setString(1, boardid);
			//쿼리 시작
			rs = pstmt.executeQuery();
			//데이터가 있을 경우 
			if(rs.next()) {
				//객체 생성한 후 객체에 DB로부터 받은 데이터를 넣는다
				Board board = new Board();
				board.setBoardid(rs.getInt("boardid"));
				board.setMembername(rs.getString("membername"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				//createDate와 updateDate는 LocalDate타입이기 떄문에 String을 Local데이터로 변경하는 작업
				board.setCreateDate(StringToLocalDate.format(rs.getString("createDate")));
				board.setUpdateDate(StringToLocalDate.format(rs.getString("updateDate")));
				//객체 반환
				return board;
			}
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		} finally {
			//DB종료
			DBConnection.close(conn, pstmt);
		}
		//DB 실패할 경우 null를 반환
		return null;
	}
	
	/**
	 * methodName:boardDelete
	 * @param boardid
	 * @return result
	 * @return -1
	 * */
	public int boardDelete(String boardid) {
		//쿼리 작성
		final String SQL = "DELETE FROM board WHERE boardid = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		
		try {
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터 대입
			pstmt.setInt(1, Integer.parseInt(boardid));
			//result = 0 fail, result = 1 success
			int result = pstmt.executeUpdate(); //트랜잭션 commit가지고 있다.
			return result;
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		} finally {
			//DB종료
			DBConnection.close(conn, pstmt);
		}
		//실패할 경우 -1을 반환
		return -1;
	}
	
	/**
	 * methodName:boardObject
	 * @param boardid
	 * @return board
	 * @return null
	 * */
	public Board boardObject(String boardid) {
		//쿼리 작성
		final String SQL = "SELECT boardid, membername, title, content FROM board WHERE boardid = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		
		try {
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터 대입
			pstmt.setInt(1, Integer.parseInt(boardid));
			//쿼리 시작
			rs = pstmt.executeQuery();
			//데이터가 있을 경우 if문 실행
			if(rs.next()) {
				//board객체 생성한 후 객체에 데이터를 담는다.
				Board board = new Board();
				board.setBoardid(rs.getInt("boardid"));
				board.setMembername(rs.getString("membername"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				//데이터를 담은 객체board를 반환
				return board;
			}
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		} finally {
			//DB종료
			DBConnection.close(conn, pstmt, rs);
		}
		//실패할 경우 null를 반환한다
		return null;
	}
	
	/**
	 * methodName:boardUpdate
	 * @param Board
	 * @return result
	 * @return -1
	 * */
	public int boardUpdate(Board board) {
		//쿼리 작성
		final String SQL = "UPDATE board SET title= ?, content= ?, updateDate = ? WHERE boardid = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		
		try {
			//쿼리 작성
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터를 대입한다
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUpdateDate().toString());
			pstmt.setInt(4, board.getBoardid());
			//result = 0 fail, result = 1 success
			//트랜잭션 commit가지고 있다.
			int result = pstmt.executeUpdate(); 
			return result;
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		} finally {
			//DB종료
			DBConnection.close(conn, pstmt);
		}
		//실패할 경우 -1을 반환
		return -1;
	}

}
