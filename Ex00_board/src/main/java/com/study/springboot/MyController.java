package com.study.springboot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.dto.FileDto;
import com.study.springboot.dto.LikeDto;
import com.study.springboot.dto.UserDto;
import com.study.springboot.service.IBoardService;
import com.study.springboot.service.IFileService;
import com.study.springboot.service.ILikeService;
import com.study.springboot.service.IReviewService;
import com.study.springboot.service.IUserService;

@Controller
public class MyController {
	Date today = new Date();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private int log = 0;
	private int rewriteflag = 0;  // 수정 페이지 제목 or 내용 빈문자열 체크 변수
	private String curId;
	private String curName;
	private String curNum;
	private int curPage = 1;
	private int flag = 0;
	private int lastPage;
	private String referer;
	// 제목 작성일 내용 등 최대 글자 수 초과 상황을 핸들링할 코드 추가 필요
	
	@Autowired
	IFileService fs;
	
	@Autowired
	ILikeService like;
	
	@Autowired
	IReviewService review;
	
	@Autowired
	IBoardService service;
	
	@Autowired
	IUserService user;
	
	@RequestMapping("/")
	public String root(Model model) throws Exception {
		if(log == 0)
			return "first/loginForm";
		else
		{
			model.addAttribute("cid", curId);
			model.addAttribute("cname", curName);
			return "redirect:list";
		}
	}
	
	@RequestMapping("logout")
	public String logoutForm() {
		log = 0;
		curPage=1;
		curId = "";
		curName = "";
		return "/first/loginForm";
	}
	
	
	
	@RequestMapping("loginForm")
	public String loginForm() {
		return "/first/loginForm";
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, Model model) {
		String nID = request.getParameter("id");
		String nPW = request.getParameter("pw");
		
		if(user.userLogin(nID, nPW) == null)
		{
			model.addAttribute("msg", "ID와 PW를 확인해주세요.");
			return "first/loginForm";
		}
		else
		{
			UserDto udto = user.userLogin(nID, nPW);
			curId = nID;
			curName = udto.getName();
			log = 1;
			return "redirect:list";
		}
	}
	
	@RequestMapping("gaipForm")
	public String gaipForm() {
		return "first/gaipForm";
	}
	
	@RequestMapping("gaip")
	public String gaip(HttpServletRequest request, Model model) {
		int rst = user.gaip(request.getParameter("name"), request.getParameter("id"), request.getParameter("pw"), request.getParameter("chk"));
	
		if(rst == 1)
		{
			model.addAttribute("msg", "가입완료! 로그인 해 주세요");
			return "first/loginForm";
		}
		else if(rst == 0)
			model.addAttribute("msg1", "공백없이 모두 입력해주세요!");
		else if(rst == -1)
			model.addAttribute("msg1", "동일한 ID가 존재합니다!");
		else
			model.addAttribute("msg1", "PW를 다시 확인해 주세요!");
		return "first/gaipForm";
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request, Model model) {
		if(log == 0)
			return "first/loginForm";
		
		//flag = 1;
		curPage = 1;
		model.addAttribute("flag", 1);
		model.addAttribute("list", service.searchName(request.getParameter("sch"),request.getParameter("what"), curId));
		model.addAttribute("cid", curId);
		model.addAttribute("cname", curName);
		return "list";
	}
	
	
	@RequestMapping("/list")
	public String listPage(Model model, HttpServletRequest request) {
		if(log == 0)
			return "first/loginForm";
		//int x = Integer.parseInt(request.getParameter("curNum"));
		//System.out.println(model.getAttribute("why"));
		// x : 페이지 번호, pageNum = 시작페이지번호
		int x = ((curPage-1) * 15);
		lastPage = (service.total(curId)) / 15;
		if(service.total(curId)%15 != 0)
			lastPage++;
		
		//System.out.println(curPage + " / " + lastPage);
		model.addAttribute("curPage", curPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("list", service.list(curId, x));
		model.addAttribute("cid", curId);
		model.addAttribute("cname", curName);
		
		return "list";
	}
	
	@RequestMapping("/selectPage")
	public String selectPage(HttpServletRequest request) {
		curPage = Integer.parseInt(request.getParameter("pageNum"));
		//System.out.println(curPage);
		return "redirect:list";
	}
	
	@RequestMapping("/next")
	public String next(Model model, HttpServletRequest request) {
		if(lastPage > curPage)
			curPage++;

		return "redirect:list";
	}
	
	@RequestMapping("/prev")
	public String prev(Model model, HttpServletRequest request) {
		if(curPage != 1)
			curPage--;
		
		return "redirect:list";
	}
	
	@RequestMapping("/lbutton")
	public String lbutton(Model model) {
		
		like.lwrite(curId, curNum);
		
		return "redirect:view?num="+curNum;
	}
	
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		if(log == 0)
			return "first/loginForm";
		model.addAttribute("cid", curId);
		String sNum = request.getParameter("num");
		model.addAttribute("dto", service.view(sNum));
		// 조회수 증가를 list페이지에서 view페이지로 이동해야만 증가하도록 설정
		String referer1 = request.getHeader("referer").substring(22);
		if(referer1.equals("list") || referer1.equals("search")) {
			service.plusview(sNum);
		}
		
		model.addAttribute("look", review.review(sNum));
		curNum = sNum;
		model.addAttribute("cnum", curNum);
		model.addAttribute("cname", curName);

		LikeDto yeong = like.lchk(curId, curNum);
		int lcnt = like.lcnt(curNum).size();
		model.addAttribute("lcnt", lcnt);
		if(yeong == null) {
			like.lcreate(curId, curNum, curName);
			model.addAttribute("lchk", "unlike");
			//System.out.println("unlike");
		}
		else {
			model.addAttribute("lchk", yeong.getChk());
			//System.out.println(yeong.getChk());
		}
		BoardDto dto = service.view(curNum); // 게시물 작성자의 이름을 빼내기 위해
		model.addAttribute("filelist", fs.filelist(curNum, dto.getId()));
		return "view";
	}
	
	@RequestMapping("/llist")
	public String llist(Model model) {
		model.addAttribute("llist", like.llist(curNum));
		return "llist";
	}
	
	@RequestMapping("/reviewWrite")
	public String reviewWrite(HttpServletRequest request, Model model) {
		if(log == 0)
			return "first/loginForm";
		//(String num, String id, String writer, String coment, String dd)
		int rst = review.reviewWrite(curNum, curId, curName, request.getParameter("coment"), request.getParameter("dd"));
		
		if(rst == 1)  // 댓글 달기 성공
			service.plusreview(curNum);
		
		return "redirect:view?num="+curNum;
		
	}
	
	@RequestMapping("/home")
	public String home() {
		curPage = 1;
		return "redirect:list";
	}
	
	@RequestMapping("/rewriteForm")
	public String rewriteForm(HttpServletRequest request, Model model) {
		model.addAttribute("cur", service.view(request.getParameter("num")));
		model.addAttribute("ID", curId);
		model.addAttribute("filelist", fs.filelist(curNum, curId));
		if(rewriteflag == 1) {
			model.addAttribute("msg", "제목과 내용 모두 한글자 이상 입력해주세요.");
			rewriteflag = 0;
		}
		return "/rewriteForm";
	}
	
	@RequestMapping("/rewrite")
	public String rewrite(HttpServletRequest request, Model model) {
		
		int rst = service.rewrite(request.getParameter("title"), request.getParameter("content"), request.getParameter("bimil"), request.getParameter("dd"), request.getParameter("num"));	
		
		if(rst == 0)
		{
			rewriteflag = 1;
			return "redirect:rewriteForm?num="+curNum;
		}
		
		return "redirect:list";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(Model model) {
		if(log == 0)
			return "first/loginForm";
		model.addAttribute("cid", curId);
		model.addAttribute("cname", curName);
		return "/writeForm";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model, @RequestParam List<MultipartFile> files) throws Exception{
		if(log == 0)
			return "first/loginForm";
		curPage = 1;
		String fflag;
		if(files.get(0).getOriginalFilename().equals("")) {
			fflag = "empty";
		}
		else {
			fflag = "exist";
		}
		
		
		int rst = service.write(request.getParameter("id"),
				  request.getParameter("writer"),
				  request.getParameter("title"),
				  request.getParameter("content"),
				  request.getParameter("bimil"),
				  request.getParameter("dd"),
				  fflag);
		String ok = service.num();  // 게시물의 idx(num)값 받아오기
		if(rst == 0)
		{
			model.addAttribute("msg", "제목과 내용 모두 한글자 이상 입력해주세요.");
			return "/writeForm";
		}
		
		// 업로드 파일을 날짜별 디렉터리 생성후 개별 보관
		String day = df.format(today);
		String path = "C:/Users/82102/Desktop/boot_workspace/Ex00_board/bin/main/static/guest/"+day;
		File Folder = new File(path);
		if(files.size() > 0 && !Folder.exists() && !files.get(0).getOriginalFilename().equals("")) {
			Folder.mkdir();
		}
		
		if(!files.get(0).getOriginalFilename().equals("")) {
			for(int i=0; i<files.size(); i++) {
				files.get(i).transferTo(new File("C:/Users/82102/Desktop/boot_workspace/Ex00_board/bin/main/static/guest/"+day+"/"+curId+"_"+i+"_"+ok+"_"+files.get(i).getOriginalFilename()));
				fs.addfile(ok, curId, curId+"_"+i+"_"+ok+"_"+files.get(i).getOriginalFilename(), files.get(i).getOriginalFilename());
			}
		}
		
		return "redirect:list";
	}
	
	@RequestMapping("/deletereview")
	public String deletereview(HttpServletRequest request, Model model) {
		if(log == 0)
			return "first/loginForm";
		
		review.reviewdelete(request.getParameter("idx"));
		service.minusreview(curNum);
		return "redirect:view?num="+curNum;
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		if(log == 0)
			return "first/loginForm";
		String tmp = request.getParameter("num");
		//System.out.println(tmp);
		String day = service.view(tmp).getDd();
		List<FileDto> fname = fs.deletename(tmp);
		for(int i=0; i<fname.size(); i++) {
			File file = new File("C:/Users/82102/Desktop/boot_workspace/Ex00_board/bin/main/static/guest/"+day+"/"+fname.get(i).getName());
			file.delete();
		}
			
		service.delete(tmp); // 게시물 제거
		fs.deleteall(tmp); // 첨부파일 일괄제거
		like.deleteall(tmp); // 좋아요 일괄제거
		review.deleteall(tmp); // 댓글 일괄제거
		return "redirect:list";
		
	}
	
	@RequestMapping("/rewritefile")
	public String rewritefile(HttpServletRequest request, Model model, @RequestParam List<MultipartFile> files) throws Exception{
		if(log == 0)
		{
			System.out.println("파일 수정 막힘");
			return "first/loginForm";
		}
		
		String day = df.format(today);
		
		if(!files.get(0).getOriginalFilename().equals("")) {
			for(int i=0; i<files.size(); i++) {
				files.get(i).transferTo(new File("C:/Users/82102/Desktop/boot_workspace/Ex00_board/bin/main/static/guest/"+day+"/"+curId+"_"+i+"_"+curNum+"_"+files.get(i).getOriginalFilename()));
				fs.addfile(curNum, curId, curId+"_"+i+"_"+curNum+"_"+files.get(i).getOriginalFilename(), files.get(i).getOriginalFilename());
			}
			service.updatefflag(curNum);
		}
		return "redirect:rewriteForm?num="+curNum;
	}
	
	@RequestMapping("/deletefile")
	public String deletefile(HttpServletRequest request) {
		if(log == 0)
			return "first/loginForm";
		String day = df.format(today);
		FileDto dto = fs.fileview(request.getParameter("idx"));
		String file_name = dto.getName();
		File file = new File("C:/Users/82102/Desktop/boot_workspace/Ex00_board/bin/main/static/guest/"+day+"/"+file_name);
		file.delete();
		fs.deletefile(request.getParameter("idx"));
		int fcnt = fs.countfile(curNum);
		if (fcnt == 0) {
			service.emptyfile(curNum);
		}
		return "redirect:rewriteForm?num="+curNum;
	}
}
