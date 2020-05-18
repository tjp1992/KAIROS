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
 * Servlet implementation class InsertMusicServlet
 */
@WebServlet(name = "InsertMusic", urlPatterns = { "/insertMusic" })
public class InsertMusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertMusicServlet() {
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
		
		s.setSongTitle(mRequest.getParameter("title"));
		s.setSongArtist(mRequest.getParameter("artist"));
		s.setSongGenre("kpop");
		s.setAlbumNo(Integer.parseInt(mRequest.getParameter("albumNo")));
		s.setFilename(s.getSongArtist() + "-" + s.getSongTitle());
		s.setFilepath(mRequest.getFilesystemName("input_music"));
		s.setLicensed(Integer.parseInt(mRequest.getParameter("licensed")));
		
		if (mRequest.getFilesystemName("album_img_file") != null) {
			Album a = new Album();
			a.setAlbumNo(Integer.parseInt(mRequest.getParameter("albumNo")));
			a.setAlbumPath(mRequest.getFilesystemName("album_img_file"));
			
			result = new ManageMusicService().insertSong(root, s, a);
			
		} else {
			
			result = new ManageMusicService().insertSong(root, s);			
		}

		request.setAttribute("loc", "/manageMusicFrm");
		if (result > 0) {
			request.setAttribute("msg", "음원 등록 완료");
		} else {
			request.setAttribute("msg", "음원 등록에 실패하였습니다.");
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
