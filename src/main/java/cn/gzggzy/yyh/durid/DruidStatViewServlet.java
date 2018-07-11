package cn.gzggzy.yyh.durid;

import java.util.UUID;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = { "/druid/*" }, initParams = { @WebInitParam(name = "loginUsername", value = "root"), @WebInitParam(name = "loginPassword", value = "root") })
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 1L;
	
	UUID id = UUID.randomUUID();
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().toLowerCase();
		System.out.println(uuid);
	}
}