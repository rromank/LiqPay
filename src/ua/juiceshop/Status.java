package ua.juiceshop;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liqpay.LiqPay;

@WebServlet("/status")
public class Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		String id = "";
		System.out.println(url);
		if (!url.equals("/LiqPay/status") && !url.equals("/LiqPay/status/")) {
			id = url.replace("/LiqPay/status/", "");
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
	}

	private String status(String id) throws Exception {
		LiqPay liqpay = new LiqPay("i31984657685",
				"vOi6sr9jpH47sB23RUPzbEw5n3XDLrv2EveNNquQ");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("payment_id", id);
		HashMap<String, Object> result = liqpay.api("payment/status", params);
		return result.toString();
	}
}
