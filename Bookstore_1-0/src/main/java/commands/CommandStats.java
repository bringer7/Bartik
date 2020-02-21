package commands;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import beans.Book;

public class CommandStats extends Command {
	private int numBooks = 0;
	private double averagePrice = 0;
	@Override
	public String getOrderName() {
		return "statistics";
	}

	@Override
	public Action executeAction(HttpServletRequest req) {
		
		List<Book> books = null;
		books = dao.list();
		findNum(books);
		calcAverage(books);
		System.out.println(numBooks);
		System.out.println(averagePrice);
		req.setAttribute("num", numBooks);
		req.setAttribute("avg", averagePrice);
		
		return new Action("BookStats.jsp", false);
	}
	
	void findNum(List<Book> books) {
		this.numBooks = books.size();
	}
	
	void calcAverage(List<Book> books) {
		double total = 0;
		for(Book b : books) {
			total += b.getPrice();
		}
		this.averagePrice = total / books.size();
	}
}
