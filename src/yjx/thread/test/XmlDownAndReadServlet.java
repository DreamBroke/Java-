package yjx.thread.test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlDownAndReadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7106694266826358514L;

	/**
	 * Constructor of the object.
	 */
	public XmlDownAndReadServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uuid = UUID.randomUUID().toString();
		String id = request.getParameter("id");
		String result = null;
		String rootPath = request.getSession().getServletContext().getRealPath("");
		File file = DownloadTest.downLoadFromUrl(
				"http://192.168.0.105:8081/threadTest/source/source.xml",
				"resource-" + uuid + ".xml",
				rootPath + "/resource/");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			System.out.println(rootPath + "/resource/resource-" + uuid + ".xml");
			Document document = db.parse(rootPath + "/resource/resource-" + uuid + ".xml");
			NodeList testList = document.getElementsByTagName("test");
			for (int i = 0; i < testList.getLength(); i++) {
				Element test = (Element) testList.item(i);
				String test_id = test.getAttribute("id");
				if (id != null && id.equals(test_id)) {
					NodeList childNodes = test.getChildNodes();
					for (int k = 0; k < childNodes.getLength(); k++) {
						if ("t".equals(childNodes.item(k).getNodeName())) {
							result = childNodes.item(k).getTextContent();
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
