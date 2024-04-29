package mytask;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/normal-buy", initParams = @WebInitParam(name = "discount", value = "15"))
public class NormalBuy extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double watchPrice = Double.parseDouble(getServletContext().getInitParameter("watch"));
		double mobilePrice = Double.parseDouble(getServletContext().getInitParameter("mobile"));
		double laptopPrice = Double.parseDouble(getServletContext().getInitParameter("laptop"));
		double discount = Double.parseDouble(getServletConfig().getInitParameter("discount"));

		String product = req.getParameter("product").toLowerCase();

		switch (product) {
		case "watch": {
			printLogic(resp, watchPrice, discount, product);
		}
			break;

		case "mobile": {
			printLogic(resp, mobilePrice, discount, product);
		}
			break;

		case "laptop": {
			printLogic(resp, laptopPrice, discount, product);
		}
			break;

		default: {
			resp.getWriter().print("<h1>Sorry!!! Product Not Available</h1>");
		}
			break;
		}
	}

	private void printLogic(HttpServletResponse resp, double price, double discount, String product)
			throws IOException {
		resp.getWriter().print("<h1>Product is " + product.toUpperCase() + "</h1>");
		resp.getWriter().print("<h2>Cost Price is : " + price + "&#8377</h2>");
		resp.getWriter().print("<h2>Discount is " + discount + "% ie, " + (price * discount) / 100 + "&#8377</h2>");
		resp.getWriter().print("<h2>Selling Price is " + (price - (price * discount) / 100) + "</h2>");
	}
}
