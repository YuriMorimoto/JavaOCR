package Controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sourceforge.tess4j.ITessAPI.TessPageIteratorLevel;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.Word;

/**
 * Servlet implementation class TextChangeServlet
 */
@WebServlet("/TextChange")
@MultipartConfig(location = "C:\\Users\\work", maxFileSize = 500*500)
public class TextChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TextChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/textChange.jsp").forward(request,
				response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		File uploadDirectory = new File(request.getServletContext().getRealPath("/WEB-INF/upload"));
		Part part = request.getPart("file");
		String filename = part.getSubmittedFileName();
		part.write(getServletContext().getRealPath("/WEB-INF/upload" + "/" + filename));

		// 検証用
		System.out.println(uploadDirectory);


		ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\work\\tessdata"); //言語ファイル格納先

        // 日本語と英語で言語を変更
        String lang = request.getParameter("lang");
        switch(lang) {

        case "jpn":
        	tesseract.setLanguage("jpn"); //言語を選択
        	break;

        case "eng":
        	tesseract.setLanguage("eng"); //言語を選択
        	break;

        default:
        	System.out.println("error");
        }


        File file = new File(uploadDirectory + "/" + filename);
        BufferedImage img = ImageIO.read(file);

		try {

			List<Word> wordList = tesseract.getWords(img, TessPageIteratorLevel.RIL_BLOCK);
			String str = tesseract.doOCR(img);
			System.out.println(wordList);
			System.out.println(str);
			request.setAttribute("str", str);
			request.getRequestDispatcher("/WEB-INF/views/textChange.jsp").forward(request, response);

		} catch (TesseractException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			img = null;
		}
	}

}
