package cn.gzggzy.yyh.durid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(urlPatterns = { "/druid/*" }, initParams = { @WebInitParam(name = "loginUsername", value = "ctoedu"), @WebInitParam(name = "loginPassword", value = "ctoedu") })
public class DruidStatViewServlet extends StatViewServlet {

	private static final long serialVersionUID = 1L;
}