package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	/**
	 * methodName:Script
	 * @param comment, response
	 * */
	public void myScript(String comment,HttpServletResponse response) {
		try {
			//PrinWriter객체 생성 HTML에 직접 코드 작성한다
			PrintWriter script = response.getWriter();
			script.println("<script>");
			//작성한 comment를 대입해 alert 생성
			script.println("alert('"+comment+"')");
			//실패한 경우이기 때문에 이전 페이지로 간다
			script.println("history.back()");
			script.println("</script>");
		} catch (IOException e) {
			//에러 내용
			e.printStackTrace();
		}
	}
}
