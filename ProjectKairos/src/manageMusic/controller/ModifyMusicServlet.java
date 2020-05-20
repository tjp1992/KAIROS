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
import song.vo.Song;

/**
 * Servlet implementation class ModifyMusicServlet
 */
@WebServlet(name = "ModifyMusic", urlPatterns = { "/modifyMusic" })
public class ModifyMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyMusicServlet() {
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

		Song s = new Song();
		s.setSongNo(Integer.parseInt(mRequest.getParameter("songNo")));
		s.setSongTitle(mRequest.getParameter("title"));
		s.setSongArtist(mRequest.getParameter("artist"));
		s.setAlbumNo(Integer.parseInt(mRequest.getParameter("albumNo")));
		s.setFilename(s.getSongArtist() + "-" + s.getSongTitle());
		s.setFilepath(mRequest.getFilesystemName("input_music"));
	
		if (s.getFilepath() != null) {
			result = new ManageMusicService().modifySong(root, s);
		} else {
			result = new ManageMusicService().modifySong(s);
		}

		request.setAttribute("loc", "/manageMusicFrm");
		if (result > 0) {
			request.setAttribute("msg", "음원 수정 완료");
		} else {
			request.setAttribute("msg", "음원 수정에 실패하였습니다.");
		}

		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

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
