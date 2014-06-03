package ua.juiceshop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.juiceshop.model.Juice;
import ua.juiceshop.repository.Repository;

import com.liqpay.LiqPay;

@WebServlet("/api/pay/*")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PUBLIC_KEY = "i31984657685";

	public Shop() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String id = "";
		System.out.println(url);
		if (!url.equals("/LiqPay/api/pay") && !url.equals("/LiqPay/api/pay/")) {
			id = url.replace("/LiqPay/api/pay/", "");
			try {
				String status = status(id);
				request.setAttribute("status", status);
				request.getRequestDispatcher("/status.jsp").forward(request,
						response);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String html = generateHTML();
		request.setAttribute("table", html);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String html = generateHTML();
		request.setAttribute("table", html);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private String status(String id) throws Exception {
		LiqPay liqpay = new LiqPay("i31984657685",
				"vOi6sr9jpH47sB23RUPzbEw5n3XDLrv2EveNNquQ");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("payment_id", id);
		HashMap<String, Object> result = liqpay.api("payment/status", params);
		return result.toString();
	}

	private String generateHTML() {
		LiqPay liqpay = new LiqPay("i31984657685",
				"vOi6sr9jpH47sB23RUPzbEw5n3XDLrv2EveNNquQ");

		StringBuilder sb = new StringBuilder();
		Repository repository = new Repository();
		List<Juice> juices = repository.getGoogs();
		sb.append("<table><tr>");
		for (Juice juice : juices) {
			sb.append("<td align='center'>");
			sb.append("<b>");
			sb.append(juice.getDescription());
			sb.append("</b> ");
			sb.append(juice.getAmount() + " UAH");
			sb.append("</br>");
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("amount", juice.getAmount());
			params.put("currency", "UAH");
			params.put("description", juice.getDescription());
			params.put("currency", "UAH");
			params.put("server_url", "http://159.224.55.90/LiqPay/status");
			params.put("sandbox", "1");
			sb.append("<img src='../");
			sb.append(juice.getImage());
			sb.append("' />");
			sb.append(liqpay.cnb_form(params));
			sb.append("</td>");
		}
		sb.append("</tr></table>");
		return sb.toString();
	}
}
