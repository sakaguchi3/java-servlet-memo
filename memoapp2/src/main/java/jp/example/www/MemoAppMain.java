package jp.example.www;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.example.www.dao.DaoFactory;
import jp.example.www.dao.DaoFactory.DaoEm;
import jp.example.www.dao.DaoFactoryImpl;
import jp.example.www.dao.MemoappDao;

/**
 * Servlet implementation class MainServlet
 */
public class MemoAppMain extends HttpServlet {

	// --------------------------------------------------------
	// field
	// --------------------------------------------------------

	private static final long serialVersionUID = 1L;

	final MemoappDao dao;

	// --------------------------------------------------------
	// constructor
	// --------------------------------------------------------

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemoAppMain() {
		super();

		DaoFactory factory = new DaoFactoryImpl();
		this.dao = factory.create(DaoEm.MySql);
	}

	// --------------------------------------------------------
	// method
	// --------------------------------------------------------

	void debug() {
		System.out.println("---------------------------------------------");
		debug2();
	}

	void debug2() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		debug();

		// レコード取り出し
		request.setAttribute("memo_list", dao.getMemos());

		String view = "/WEB-INF/jsp/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		debug();

		request.setCharacterEncoding("UTF-8");

		MemoBean memo = new MemoBean();
		memo.setTitle(request.getParameter("title"));
		memo.setMemo(request.getParameter("memo"));
		System.out.println("post:title: " + memo.getTitle());
		System.out.println("post:memo: " + memo.getMemo());

		dao.save(memo);

		response.sendRedirect(".");
	}


}
