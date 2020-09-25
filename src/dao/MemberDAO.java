package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Member;
import utils.DBConnection;

public class MemberDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;

	/**
	 * methodName:memberName
	 * @param boardid
	 * @return board
	 * @return null
	 * */
	public int MemberNameCheck(String membername) {
		//쿼리 작성
		final String SQL = "SELECT count(*) FROM member WHERE membername = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		try {
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터 대입
			pstmt.setString(1, membername);
			//쿼리 시작
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//데이터를 받은 후 제일 첫번째 줄
				int result = rs.getInt(1);
				//0일 경우 아이디가 없는것, 1이상일 경우 아이디가 있다는 것
				return result;
			}
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		}finally {
			//DB종료
			DBConnection.close(conn, pstmt, rs);
		}
		//실패한 경우 -1를 반환
		return -1;
	}
	
	/**
	 * methodName:join
	 * @param member
	 * @return result
	 * @return -1
	 * */
	public int join(Member member) {
		//쿼리 작성
		final String SQL = "INSERT INTO member(memberid,membername,password,name,gender,email,address,phone)values(member_seq.nextval,?,?,?,?,?,?,?)";
		//DB연결
		Connection conn = DBConnection.getConnection();
		//아이디 중복 검사 (잘못된 접근으로 등록 방지)
		int memberNameCheck = MemberNameCheck(member.getMembername());
		if(memberNameCheck == 0) {
			try {
				//쿼리 작성
				pstmt = conn.prepareStatement(SQL);
				//?에 파라메터를 대입
				pstmt.setString(1, member.getMembername());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getGender());
				pstmt.setString(5, member.getEmail());
				pstmt.setString(6, member.getAddress());
				pstmt.setString(7, member.getPhone());
				
				//result = 0 fail, result = 1 success
				//트랜잭션 commit가지고 있다.
				int result = pstmt.executeUpdate(); 
				return result;
			} catch (Exception e) {
				//에러 내용
				e.printStackTrace();
			}finally {
				//DB종료
				DBConnection.close(conn, pstmt);
			}
		}else {
			//잘못된 접근으로 가입하여 아이디 중복이 되는 경우
			return 2;
		}
		//실패한 경우 -1를 반환
		return -1;
	}
	
	/**
	 * methodName:login
	 * @param membername, password
	 * @return result
	 * @return -1
	 * */
	public int login(String membername, String password) {
		//쿼리 작성
		final String SQL = "SELECT count(*) FROM member WHERE membername = ? AND password = ?";
		//DB연결
		Connection conn = DBConnection.getConnection();
		try {
			//쿼리 등록
			pstmt = conn.prepareStatement(SQL);
			//?에 파라메터 대입
			pstmt.setString(1, membername);
			pstmt.setString(2, password);
			//쿼리 실햄
			rs = pstmt.executeQuery(); 
			
			//rs는 첫번째 커서를 가리킨다.
			if(rs.next()) { // true 인지 false인지
				//0일 경우 아이디가 없는것, 1이상일 경우 아이디가 있다는 것
				int result = rs.getInt(1);
				return result;
			}
		} catch (Exception e) {
			//에러 내용
			e.printStackTrace();
		}finally {
			//DB종료
			DBConnection.close(conn, pstmt, rs);
		}
		//실패할 경우 -1를 반환
		return -1;
	}
}
