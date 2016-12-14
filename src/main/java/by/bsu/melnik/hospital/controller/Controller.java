package by.bsu.melnik.hospital.controller;

import by.bsu.melnik.hospital.command.ActionCommand;
import by.bsu.melnik.hospital.factory.ActionFactory;
import by.bsu.melnik.hospital.ConfigurationManager;
import by.bsu.melnik.hospital.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = null;

		// Определение команды, пришедшей из JSP
		ActionFactory client = new ActionFactory();
		ActionCommand command = client.defineCommand(request);

		// Вызов реализованного метода execute()
		// и передача параметров классу-обработчику конкретной команды
		page = command.execute(request);

		// Метод возвращает страницу ответа
		// TODO: поэксперементировать с page = null
		if (page != null){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
			// Вызов страницы ответа на запрос
			dispatcher.forward(request, response);
		}
		else {

			// Установка страницы с сообщение об ошибке
			page = ConfigurationManager.getProperty("path.page.index");
			request.getSession().setAttribute("nullPage",
					MessageManager.getProperty("message.nullpage"));
			response.sendRedirect(request.getContextPath() + page);
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
