package manageMusic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import manageMusic.model.service.ManageMusicService;
import manageMusic.model.vo.Album;

/**
 * Servlet implementation class ModifyAlbumServlet
 */
@WebServlet(name = "ModifyAlbum", urlPatterns = { "/modifyAlbum" })
public class ModifyAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyAlbumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 파일 저장 경로
		String root = request.getSession().getServletContext().getRealPath("/");
		String saveDirectory = root + "src/tempUpload";

		// 파일 최대 크기
		int maxSize = 10 * 1024 * 1024;

		// request -> MultipartRequest 파일 업로드 진행

		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		int result = 0;
		
		Album a = new Album();
		a.setAlbumNo(Integer.parseInt(mRequest.getParameter("albums")));
		a.setAlbumName(mRequest.getParameter("albumName"));
		
		if(mRequest.getFilesystemName("album_img_file") != null) {
			a.setAlbumPath(mRequest.getFilesystemName("album_img_file"));
			
			result = new ManageMusicService().updateAlbum(root, a);
		} else {
			result = new ManageMusicService().updateAlbum(a);
		}
		
		request.setAttribute("loc", "/manageAlbumFrm");
		if(result>0) {
			request.setAttribute("msg", "수정 완료");
		} else {
			request.setAttribute("msg", "수정 실패");
		}
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
