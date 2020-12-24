package com.sist.controller;

import java.util.ArrayList;
import java.util.Scanner;


import com.sist.dao.BookDAO;
import com.sist.dao.AttendanceDAO;
import com.sist.dao.CourseDAO;
import com.sist.dao.DataStatisticsDAO;
import com.sist.dao.JobActivitiesDAO;
import com.sist.dao.LinkCompanyDAO;
import com.sist.dao.RecommendDAO;
import com.sist.dao.ScCourseSubjectDAO;
import com.sist.dao.ScCourseSubjectScoreDAO;
import com.sist.dao.ScoreListCourseDAO;
import com.sist.dao.ScoreListStudentDAO;
import com.sist.dao.StudentConsultListDAO;
import com.sist.dao.StudentDAO;
import com.sist.dao.StudentlistDAO;
import com.sist.dao.SubjectDAO;
import com.sist.dao.ScCourseSubjectDAO;
import com.sist.dao.TalentedStudentDAO;
import com.sist.dto.AbleTStudentScoreListDTO;
import com.sist.dto.AttendanceInfoDTO;
import com.sist.dto.AttendanceStatisticsDTO;
import com.sist.dto.BookDTO;
import com.sist.dto.CompletStudentListDTO;
import com.sist.dto.CourseDTO;
import com.sist.dto.EmploymentRateDTO;
import com.sist.dto.EndCourseListDTO;
import com.sist.dto.JobInfoDTO;
import com.sist.dto.LinkCompanyDTO;
import com.sist.dto.MasterDTO;
import com.sist.dto.QualificationDTO;
import com.sist.dto.ScCourseSubjectDTO;
import com.sist.dto.ScCourseSubjectScoreDTO;
import com.sist.dto.ScoreListCourseDTO;
import com.sist.dto.ScoreListStudentDTO;
import com.sist.dto.StudentConsultListDTO;
import com.sist.dto.StudentDTO;
import com.sist.dto.StudentInfoListDTO;
import com.sist.dto.StudentlistDTO;
import com.sist.dto.SubjectListDTO;
import com.sist.dto.SubjectDTO;
import com.sist.dto.TalentedStudentListDTO;
import com.sist.view.AdminView;

public class AdminController {

	private String num = ""; // 사용자가 입력하는 번호
	private static Scanner scan = new Scanner(System.in);;
	private MasterDTO mdto; // 로그인한 계정의 정보를 담을 객체
	private AdminView aview;

	private StudentlistDAO sldao;
	private StudentDAO sdao;
	private LinkCompanyDAO lcdao;
	private TalentedStudentDAO tsdao;
	private StudentConsultListDAO scldao;
	private RecommendDAO rdao;
	private CourseDAO csdao;
	private JobActivitiesDAO jadao;
	private ScoreListCourseDAO slcdao;
	private ScoreListStudentDAO slsdao;
	private ScCourseSubjectDAO scsdao;
	private DataStatisticsDAO dsdao;
	private ScCourseSubjectScoreDAO scssdao;
	private AttendanceDAO adao;
	private SubjectDAO sbdao;
	private BookDAO bodao;


	public AdminController(MasterDTO mdto) {
		
		this.mdto = mdto; // 로그인한 관리자의 계정 정보를 담는다
		this.aview = new AdminView();
		this.sdao = new StudentDAO();
		this.lcdao = new LinkCompanyDAO();
		this.tsdao = new TalentedStudentDAO();
		this.csdao = new CourseDAO();
		this.sldao = new StudentlistDAO();
		this.rdao = new RecommendDAO();
		this.csdao = new CourseDAO();
		this.jadao = new JobActivitiesDAO();	
		this.slcdao = new ScoreListCourseDAO();
		this.slsdao = new ScoreListStudentDAO();
		this.scsdao = new ScCourseSubjectDAO();
		this.scldao = new StudentConsultListDAO();
		this.dsdao = new DataStatisticsDAO();
		this.scssdao = new ScCourseSubjectScoreDAO();
		this.adao = new AttendanceDAO();
		this.sbdao = new SubjectDAO();
		this.bodao = new BookDAO();
	}

	public void start() {

		boolean check = true;

		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.printf("관리자 %s님 접속을 환영합니다\n", this.mdto.getId());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 기초정보 관리");
			System.out.println("2. 개설과정 관리");
			System.out.println("3. 개설과목 관리");
			System.out.println("4. 출결 관리");
			System.out.println("5. 성적 관리");
			System.out.println("6. 취업현황 조회");
			System.out.println("7. 취업지원 관리");
			System.out.println("8. 데이터 통계 관리");
			System.out.println("9. 상담일지 관리");
			System.out.println("0. 로그아웃");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				basicinfoManagement(); // 기초정보 관리
			} else if (num.equals("2")) {
				makecourseManagement(); // 개설 과정 관리
			} else if (num.equals("3")) {
				makesubjectManagement(); // 개설 과목 관리
			} else if (num.equals("4")) {
				attendanceManagement(); // 출결 관리 - 박영수
			} else if (num.equals("5")) { 
				scoreManagement();	// 성적 관리 - 윤지현
			} else if (num.equals("6")) { 
				jobactivitiesManagement(); // 취업현황 조회 - 박영수
			} else if (num.equals("7")) {
				jobSupportManagement(); // 취업지원 관리 - 박영수
			} else if (num.equals("8")) {
				dataStatisticsManagement(); // 데이터 통계 관리 - 박영수
			} else if (num.equals("9")) { 
				CourseconsultationManagement();	// 상담 관리 - 윤지현
			} else if (num.equals("0")) {
				// 로그아웃
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	private void CourseconsultationManagement() {
		// 상담관리
		boolean check = true;
		while (check) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[상담 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 상담 조회");
			System.out.println("2. 상담 추가");
			System.out.println("3. 상담 수정");
			System.out.println("4. 상담 삭제");
			System.out.println("5. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();


				if(num.equals("1")) {
					CourseconsultationList();
				}else if(num.equals("2")) {
					CourseconsultationAdd();
				}else if(num.equals("3")) {
					CourseconsultationEdit();
				}else if(num.equals("4")) {
					CourseconsultationDelete();	
				} else if (num.equals("5")) {
					break;
				}else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}
		}	
	}

	private void CourseconsultationList() {
		// 관리자 - 상담 관리 - 상담 조회 
		//교육생 리스트 출력 (교육생이름, 주민번호뒷자리, 수강상태)
		
		boolean check = true;
		while (check) {
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t교육생이름\t주민번호뒷자리\t수강상태");

		ArrayList<StudentlistDTO> list = sldao.Studentlist(null);
		
		for(StudentlistDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\n"
								, dto.getSeq()
								, dto.getSname()
								, dto.getJumin()
								, dto.getRegiState());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		System.out.println("상담조회를 원하는 교육생의 번호를 입력해주세요.");
		System.out.print("번호입력 : ");
		num = scan.nextLine(); //seq
		
		StudentConsultList(num);						
		

		}
	}

//수정중***********************************************************************  

  	private void StudentConsultList(String num) {
  		// 관리자 - 상담 관리 - 상담 조회 
  		// 교육생 번호 선택
  		// 전 과목 상담 일지 리스트 (상담번호, 작성날짜, 교육생이름, 과목번호, 과목명, 과정기간, 상담사유, 상담내용 )
  		
  		boolean check = true;
		while (check) {
  		
  		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		
		ArrayList<StudentConsultListDTO> list = scldao.StudentConsultList();
		
		for(StudentConsultListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\n"
								//, dto.getSeq()
								, dto.getConsultDate()
								, dto.getSname()
								, dto.getSubjectSeq()
								, dto.getSubjectName()
								, dto.getCourseDate()
								, dto.getConsultReason()
								, dto.getConsultContent());
			}
			pause();
			break;
		
			

		}
	}

	private void CourseconsultationAdd() {
		// 관리자 - 상담 관리 - 상담 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 추가]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

//		System.out.println("상담번호 : ");
//		String consultseq = scan.nextLine();

		System.out.println("상담날짜 : ");
		String consultDate = scan.nextLine();

		System.out.println("교육생이름 : ");
		String sname = scan.nextLine();

		System.out.println("과목번호 : ");
		String subjectSeq = scan.nextLine();

		System.out.println("과목명 : ");
		String subjectName = scan.nextLine();

		System.out.println("과정기간 (ex)2020/01/01-2020/12/31): ");
		String courseDate = scan.nextLine();

		System.out.println("상담사유 : ");
		String consultReason = scan.nextLine();

		System.out.println("상담내용 : ");
		String consultContent = scan.nextLine();

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		StudentConsultListDTO dto = new StudentConsultListDTO();

		dto.setConsultDate(consultDate);
		dto.setSname(sname);
		dto.setSubjectSeq(subjectSeq);
		dto.setSubjectName(subjectName);
		dto.setCourseDate(courseDate);
		dto.setConsultReason(consultReason);
		dto.setConsultContent(consultContent);

		int result = scldao.add(dto);

		if (result == 1) {
			System.out.println("상담 추가 성공입니다.");
		} else {
			System.out.println("상담 추가 실패입니다.");
		}

		pause();
	}

	private void CourseconsultationEdit() {
		// 관리자 - 상담 관리 - 상담 수정
		ArrayList<StudentConsultListDTO> list = scldao.StudentConsultList();
		
		for(StudentConsultListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t\t%s\n"
								//, dto.getSeq()
								, dto.getConsultDate()
								, dto.getSname()
								, dto.getSubjectSeq()
								, dto.getSubjectName()
								, dto.getCourseDate()
								, dto.getConsultReason()
								, dto.getConsultContent());
		}
		System.out.println();
		System.out.print("수정할 상담 번호 : ");
		String seq = scan.nextLine();
		
		StudentConsultListDTO dto = new StudentConsultListDTO();
		
		System.out.println();
		//System.out.println("번호 : " + dto.getSeq());
		System.out.println("교육생이름 : " + dto.getSname());
		System.out.println("상담날짜 : " + dto.getConsultDate());
		System.out.println("상담사유 : " + dto.getConsultReason());
		System.out.println("상담내용 : " + dto.getConsultContent());
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("수정을 하지 않는 컬럼은 그냥 엔터를 입력하세오.");
		
		
		System.out.print("수정할 교육생이름 : ");
		String sname = scan.nextLine();
		
		if(sname.equals("")) {
			sname = dto.getSname();
		}
				
		
		System.out.print("수정할 상담날짜 : ");
		String ConsultDate = scan.nextLine();
		
		if(ConsultDate.equals("")) {
			ConsultDate = dto.getConsultDate();
		}
		
		
		System.out.print("수정할 상담사유: ");
		String ConsultReason = scan.nextLine();
		
		if(ConsultReason.equals("")) {
			ConsultReason = dto.getConsultDate();
		}
		
		
		System.out.print("수정할 상담내용: ");
		String ConsultContent = scan.nextLine();
		
		if(ConsultContent.equals("")) {
			ConsultContent = dto.getConsultContent();
		}
		
		
		StudentConsultListDTO dto2 = new StudentConsultListDTO();
		
		dto2.setSname(sname);
		dto2.setConsultDate(ConsultDate);
		dto2.setConsultReason(ConsultReason);
		dto2.setConsultContent(ConsultContent);
		
		int result = scldao.edit(dto2);
		
		if(result>0) {
			System.out.println("주소록 수정 성공");
		}else {
			System.out.println("주소록 수정 실패");
		}
		
		
		pause();
		


	}

	private void CourseconsultationDelete() {
		// 관리자 - 상담 관리 - 상담 삭제

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[상담 삭제]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		ArrayList<StudentConsultListDTO> list = scldao.StudentConsultList(); 
		
		for (StudentConsultListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getSname(), dto.getSubjectName(),
					dto.getConsultContent());
		}
		System.out.println();

		System.out.print("삭제할 상담 번호 : ");
		String seq = scan.nextLine();

		int result = scldao.delete(seq);

		if (result > 0) {
			System.out.println("상담 삭제 성공입니다.");
		} else {
			System.out.println("상담 삭제 실패입니다.");
		}
				
		pause();
				
	}

	private void scoreManagement() {
		// 성적 관리
		boolean check = true;
		while (check) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[성적 관리]");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 성적 조회");
			System.out.println("2. 성적 추가");
			System.out.println("3. 성적 수정");
			System.out.println("4. 성적 삭제");
			System.out.println("5. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();
					
				if(num.equals("1")) {
					ScoreList();
				}else if(num.equals("2")) {
					ScoreAdd();
				}else if(num.equals("3")) {
					ScoreEdit();	
				}else if(num.equals("4")) {					
					ScoreDelete();	
				} else if (num.equals("5")) {
					break;	
				}else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}
		}
	}		


 
	private void ScoreList() {
		// 성적 관리 - 성적 조회
		
		boolean check = true;
		while (check) {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 조회]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("1. 과정별");
		System.out.println("2. 교육생별");
		System.out.println("3. 뒤로가기");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("번호를 입력하세요 :");
		num = scan.nextLine();
		
		if(num.equals("1")) {
			ScoreListCourse();
		}else if(num.equals("2")) {
			ScoreListStudent();
		}else if(num.equals("3")) {
			break;
		}else {
			System.out.println("잘못된 입력입니다");
			pause();
			break;
		}
		
		
		}
	}

	private void ScoreListCourse() {
		// 성적 관리 - 성적 조회 - 과정별
		

		boolean check = true;
		while (check) {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 조회 - 과정별]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t과정명\t과정시작일\t과정종료일");
		
		System.out.println(slcdao == null);
		ArrayList<ScoreListCourseDTO> list = slcdao.list();
		
		for(ScoreListCourseDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getCname()
								, dto.getStartDate()
								, dto.getEndDate());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		System.out.println("성적조회를 원하는 과정의 번호를 입력해주세요.");
		System.out.print("번호입력 : ");
		num = scan.nextLine();
		
		ScCourseSubject(num);
		
			pause();
			break;
		}
			
	}

	private void ScCourseSubject(String num) {
		// 성적 관리 - 성적 조회 - 과정별- 과정선택
		// 과목리스트(과목명, 개설 과목기간, 강의실명, 개설 과목명, 교사명, 교재명 등)을 출력
		
		boolean check = true;
		while (check) {
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 조회 - 과정별]");
		System.out.println("과목리스트");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t과목명\t과목기간\t강사명\t교재명");
		
		ArrayList<ScCourseSubjectDTO> list = scsdao.list();
		
		for(ScCourseSubjectDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getSjname()
								, dto.getDuration()
								, dto.getTname()
								, dto.getBook());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		System.out.println("성적조회를 원하는 과목의 번호를 입력해주세요.");
		System.out.print("번호입력 : ");
		num = scan.nextLine();
		
		//과목번호를 받아서 성적출력하는 메서드
		ScCourseSubjectScore(num);
		
			pause();
			break;
		
		}
	}

	//과목번호를 받아서 성적출력하는 메서드
	private void ScCourseSubjectScore(String num2) {
		// 성적 관리 - 성적 조회 - 과정별- 과정선택- 과목선택 
		// 교육생 성적 정보(교육생 이름, 주민번호 뒷자리, 필기, 실기) 출력
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 조회 - 과정별 - 과목]");
		System.out.println("교육생 성적 리스트");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("교육생이름\t주민번호\t필기점수\t실기점수");
		
		ArrayList<ScCourseSubjectScoreDTO> list = scssdao.list();
		
		for(ScCourseSubjectScoreDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
								, dto.getSname()
								, dto.getJumin()
								, dto.getWrite()
								, dto.getPratice());
			pause();
			break;
		
		}
		
	}

	private void ScoreListStudent() {
		// 성적 관리 - 성적 조회 - 교육생별
		
		boolean check = true;
		while (check) {
			
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 조회 - 교육생별]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("번호\t교육생이름\t주민번호\t과정명\t\t\t\t\t과정기간");
		
		ArrayList<ScoreListStudentDTO> list = slsdao.list();
		
		for(ScoreListStudentDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n"
								, dto.getSeq()
								, dto.getSname()
								, dto.getJumin()
								, dto.getCname()
								, dto.getCourseterm());
		}
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");	
		System.out.println("성적조회를 원하는 교육생의 번호를 입력해주세요.");
		System.out.print("번호입력 : ");
		num = scan.nextLine();
			
			pause();
			break;
		}
	}

	private void ScoreAdd() {
		// 성적 관리 - 성적 추가
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 추가]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		System.out.print("교육생이름 : ");
		String sname = scan.nextLine();
		
		System.out.print("과목번호 : ");
		String sjnum = scan.nextLine();
		
		System.out.print("필기점수 : ");
		String write = scan.nextLine();
		
		System.out.print("실기점수 : ");
		String practice = scan.nextLine();
		
//		AddressDTO dto = new AddressDTO();
//		dto.setName(name);
//		dto.setAge(age);
//		dto.setGender(gender);
//		dto.setTel(tel);
//		dto.setAddress(address); //포장
//		
//		int result = dao.add(dto);
//		
//		if(result == 1) {
//			System.out.println("주소록 추가 성공");
//		}else {
//			System.out.println("주소록 추가 실패");
//		}
//		
//		pause();
		
		
	}

	private void ScoreEdit() {
		// 성적 관리 - 성적 수정
		
	}

	private void ScoreDelete() {
		// 성적 관리 - 성적 삭제
		
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("[성적 삭제]");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		
		ArrayList<StudentConsultListDTO> list = scldao.StudentConsultList(); 
		
		for (StudentConsultListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getSname(), dto.getSubjectName(), dto.getConsultContent());
		}
		System.out.println();
		
		System.out.print("삭제할 상담 번호 : ");
		String seq = scan.nextLine();
		
		int result = scldao.delete(seq);
		
		if(result>0) {
			System.out.println("상담 삭제 성공입니다.");
		}else {
			System.out.println("상담 삭제 실패입니다.");
		}
				
		pause();
	}



	/**
	 * 취업지원 관리 메뉴
	 */
	private void jobSupportManagement() {
		// 취업지원 관리
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("취업지원 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 연계기업 관리");
			System.out.println("2. 추천인재 관리");
			System.out.println("3. 기업에 인재추천");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				enterpriseManagement();
			} else if (num.equals("2")) {
				talentedStudentManagement();
			} else if (num.equals("3")) {
				recommendManagement();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 연계 기업 관리 메뉴
	 */
	private void enterpriseManagement() {
		// 취업지원 관리 - 연계기업 관리
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계기업 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 연계기업 조회");
			System.out.println("2. 연계기업 검색");
			System.out.println("3. 연계기업 추가");
			System.out.println("4. 연계기업 수정");
			System.out.println("5. 연계기업 삭제");
			System.out.println("6. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				enterpriseList();
			} else if (num.equals("2")) {
				enterpriseSearch();
			} else if (num.equals("3")) {
				enterpriseAdd();
			} else if (num.equals("4")) {
				enterpriseEdit();
			} else if (num.equals("5")) {
				enterpriseDelete();
			} else if (num.equals("6")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 연계 기업 조회
	 */
	private void enterpriseList() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 조회
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;

		while (true) {
			System.out.println(
					"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("                                                     연계 기업 목록");
			System.out.println(
					"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");

			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
//			System.out.println("가져온 레코드의 개수" + list.size());
			if (list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}

//			System.out.println("현재 페이지 : " + nowPage);
			for (int i = startNum; i < endNum; i++) {

				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n", list.get(i).getSeq(), list.get(i).getName(),
						list.get(i).getAddress(), list.get(i).getDepartment(), list.get(i).getSalary());
			}

//			for(LinkCompanyDTO dto : list) {
//			System.out.printf("%s\t%s\t%s\t%s\t\n"
//					,dto.getSeq() 
//					,dto.getName()
//					,dto.getAddress()
//					,dto.getDepartment()
//					,dto.getSalary());

			System.out.println(
					"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				if (nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if (nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}

			} else if (num.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 연계 기업 검색
	 */
	private void enterpriseSearch() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 검색
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계 기업명으로 검색");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("검색어를 입력하세요 :");
			num = scan.nextLine();

			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(num);
			if (list.size() != 0) {
				System.out.println("번호\t회사이름\t\t\t주소\t\t\t\t\t\t부서\t\t\t제시연봉");
				for (LinkCompanyDTO dto : list) {
					System.out.printf("%2s\t%-15s\t\t%-30s\t%-15s\t%7s\n", dto.getSeq(), dto.getName(),
							dto.getAddress(), dto.getDepartment(), dto.getSalary());
				}

			} else {
				System.out.println("해당 검색어로 검색된 결과가 없습니다");
			}

			System.out.println("1. 다시 검색하기 2. 뒤로가기");
			num = scan.nextLine();

			if (num.equals("1")) {

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 연계 기업 추가
	 */
	private void enterpriseAdd() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 추가
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("연계기업 추가");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("기업이름 : ");
		String name = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("주소 : ");
		String address = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("부서 : ");
		String departmemt = scan.nextLine();
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("제시연봉 : ");
		String salary = scan.nextLine();

		// 추가할 정보를 LinkCompanyDTO 객체에 담아 전달
		LinkCompanyDTO dto = new LinkCompanyDTO();
		dto.setName(name);
		dto.setAddress(address);
		dto.setDepartment(departmemt);
		dto.setSalary(salary);

		int result = lcdao.linkCompanyAdd(dto);

		if (result == 1) {
			System.out.println("주소록 추가 성공");
		} else {
			System.out.println("주소록 추가 실패");
		}
		pause();

	}

	/**
	 * 연계 기업 수정
	 */
	private void enterpriseEdit() {
		// 취업지원 관리 - 연계기업 관리 - 연계기업 수정
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;

		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("연계기업 수정");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		while (true) {
//			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//			System.out.println("연계 기업 목록");
//			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");

			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
			if (list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}

			for (int i = startNum; i < endNum; i++) {

				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n", list.get(i).getSeq(), list.get(i).getName(),
						list.get(i).getAddress(), list.get(i).getDepartment(), list.get(i).getSalary());
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 수정하기 4. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				if (nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if (nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}

			} else if (num.equals("3")) {
				// 수정 진행
				System.out.print("수정할 기업의 번호를 입력하세요 :");
				num = scan.nextLine();

				// seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
				LinkCompanyDTO dto = lcdao.getLinkCompany(num);

				// 수정할 기업의 정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 기업의 정보");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("이름 : " + dto.getName());
				System.out.println("주소 : " + dto.getAddress());
				System.out.println("부서 : " + dto.getDepartment());
				System.out.println("연봉 : " + dto.getSalary());
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 이름 : ");
				String name = scan.nextLine();

				if (name.equals("")) {
					name = dto.getName();
				}

				System.out.println("수정할 주소 : ");
				String address = scan.nextLine();

				if (address.equals("")) {
					address = dto.getAddress();
				}

				System.out.println("수정할 부서 : ");
				String department = scan.nextLine();

				if (department.equals("")) {
					department = dto.getDepartment();
				}

				System.out.println("수정할 연봉 : ");
				String salary = scan.nextLine();

				if (salary.equals("")) {
					salary = dto.getSalary();
				}

				// editdto객체에는 수정된 레코드 값을 넣는다
				LinkCompanyDTO editdto = new LinkCompanyDTO();
				editdto.setSeq(dto.getSeq());
				editdto.setName(name);
				editdto.setAddress(address);
				editdto.setDepartment(department);
				editdto.setSalary(salary);

				int result = lcdao.linkCompanyEdit(editdto);

				if (result > 0) {
					System.out.println("기업 수정 성공");
				} else {
					System.out.println("기업 수정 실패");
				}

				pause();
				break;

			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 연계 기업 삭제
	 */
	private void enterpriseDelete() {

		// 취업지원 관리 - 연계기업 관리 - 연계기업 삭제
		int page = 10; // 한 페이지에 보여질 페이지의 개수
		int endPage; // 끝 페이지
		int nowPage = 1; // 현재 페이지
		int startNum = 0;
		int endNum = page;

//		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
//		System.out.println("연계기업 삭제");
//		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("연계 기업 목록");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]\t  [회사이름]\t\t\t\t  [주소]\t\t\t\t  [부서]\t\t[제시연봉]");

			ArrayList<LinkCompanyDTO> list = lcdao.linkCompanyList(null);
			if (list.size() % page == 0) {
				endPage = list.size() / page;
			} else {
				endPage = list.size() / page + 1;
			}

			for (int i = startNum; i < endNum; i++) {

				System.out.printf("%4s    %-13s\t\t%-33s\t%-15s\t%7s\n", list.get(i).getSeq(), list.get(i).getName(),
						list.get(i).getAddress(), list.get(i).getDepartment(), list.get(i).getSalary());
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			System.out.println("1. 이전 페이지로 2. 다음 페이지로 3. 삭제하기 4. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				if (nowPage == 1) {
					// 첫 페이지일때 - 이전 페이지로 갈 수 없음
					System.out.println("현재 페이지가 첫 페이지 입니다");
				} else {
					nowPage--; // 현재페이지 1 감소
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}
			} else if (num.equals("2")) {
				if (nowPage == endPage) {
					// 끝 페이지 일때 - 다음 페이지로 갈 수 없음
					System.err.println("현재 페이지가 끝 페이지 입니다");
				} else if (nowPage == endPage - 1) {
					nowPage++;
					startNum = (nowPage - 1) * page;
					int temp = (list.size() % page == 0) ? page : list.size() % page;
					endNum = startNum + temp;
				} else {
					nowPage++; // 현재페이지 1 증가
					startNum = (nowPage - 1) * page;
					endNum = startNum + page;
				}

			} else if (num.equals("3")) {
				// 삭제 진행
				System.out.print("삭제할 기업의 번호를 입력하세요 :");
				num = scan.nextLine();

				// 기업을 삭제하는 메서드
				int result = lcdao.linkCompanyDelete(num);

				if (result > 0) {
					System.out.println("기업 삭제 성공");
				} else {
					System.out.println("기업 삭제 실패");
				}
				pause();
				break;

			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 추천 인재 관리 메뉴
	 */
	private void talentedStudentManagement() {
		// 취업지원 관리 - 추천인재 관리
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천인재 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추천인재 조회");
			System.out.println("2. 추천인재 검색");
			System.out.println("3. 추천인재 추가");
			System.out.println("4. 추천인재 수정");
			System.out.println("5. 추천인재 삭제");
			System.out.println("6. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				talentedStudenList();
			} else if (num.equals("2")) {
				talentedStudenearch();
			} else if (num.equals("3")) {
				talentedStudenAdd();
			} else if (num.equals("4")) {
				talentedStudenEdit();
			} else if (num.equals("5")) {
				talentedStudenDelete();
			} else if (num.equals("6")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}

	}

	/**
	 * 추천 인재 조회
	 */
	private void talentedStudenList() {
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		ArrayList<TalentedStudentListDTO> list = tsdao.talentedStudenList(null);

		for (TalentedStudentListDTO dto : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getSeq(), dto.getName(), dto.getTel(), dto.getPortfolio(),
					dto.getReason());
		}

		pause();

	}

	/**
	 * 추천 인재 검색
	 */
	private void talentedStudenearch() {
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 검색
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 검색");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.print("검색할 번호를 입력하세요 :");
		num = scan.nextLine();

		aview.TalentedStudentListView();

		pause();

	}

	/**
	 * 추천 인재 추가
	 */
	private void talentedStudenAdd() {
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 추가

		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천 인재 추가");
			System.out.println("수료생중 성적이 높은 10명");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("[번호]  [이름]  [필기]  [실기]  [출결]");
			ArrayList<AbleTStudentScoreListDTO> list = tsdao.ableTStudentScoreList();

			for (AbleTStudentScoreListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getWriter(),
						dto.getPractice(), dto.getAttendance());
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추가하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 추가하기 진행
				System.out.print("추가할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();
				System.out.print("포트폴리오 주소 입력 : ");
				String portfolio = scan.nextLine();
				System.out.print("인재 선정 이유 입력 : ");
				String reason = scan.nextLine();

				if (num.equals("") || portfolio.equals("") || reason.equals("")) {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}

				int result = tsdao.talentedStudenAdd(num, portfolio, reason);

				if (result > 0) {
					System.out.println("인재 추가 성공");
				} else {
					System.out.println("인재 추가 실패");
				}

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 추천 인재 수정
	 */
	private void talentedStudenEdit() {
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 수정
		while (true) {
			aview.TalentedStudentListView();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 수정하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 수정하기 진행
				System.out.print("수정할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();

				// seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
				TalentedStudentListDTO dto = tsdao.getTalentedStudent(num);

				// 수정할 기업의 정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("수정할 인재 정보");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("이름       : " + dto.getName());
				System.out.println("전화번호   : " + dto.getTel());
				System.out.println("포트폴리오 : " + dto.getPortfolio());
				System.out.println("추천이유   : " + dto.getReason());
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("━━━━━━━━━━━━━━ 수정을 하지 않는 컬럼은 엔터를 입력하시오 ━━━━━━━━━━━━━━━━━━━");
				System.out.print("수정할 포트폴리오 : ");
				String portfolio = scan.nextLine();

				if (portfolio.equals("")) {
					portfolio = dto.getPortfolio();
				} else {
					dto.setPortfolio(portfolio);
				}

				System.out.print("수정할 추천이유 : ");
				String reason = scan.nextLine();

				if (reason.equals("")) {
					reason = dto.getReason();
				} else {
					dto.setReason(reason);
				}

				TalentedStudentListDTO dto2 = new TalentedStudentListDTO();

				dto2.setSeq(dto.getSeq());
				dto2.setName(dto.getName());
				dto2.setTel(dto.getTel());
				dto2.setPortfolio(dto.getPortfolio());
				dto2.setReason(dto.getReason());

				int result = tsdao.talentedStudenEdit(dto2);

				if (result > 0) {
					System.out.println("인재 수정 성공");
				} else {
					System.out.println("인재 수정 실패");
				}

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}

	}

	/**
	 * 추천 인재 삭제
	 */
	private void talentedStudenDelete() {
		// 취업지원 관리 - 추천인재 관리 - 추천 인재 삭제

		while (true) {
			aview.TalentedStudentListView();
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 삭제하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 삭제하기 진행
				System.out.print("삭제할 학생의 번호를 입력하세요 :");
				num = scan.nextLine();

				int result = tsdao.talentedStudenDelete(num);

				if (result > 0) {
					System.out.println("인재 삭제 성공");
				} else {
					System.out.println("인재 삭제 실패");
				}

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 기업에 인재 추천 메뉴
	 */
	private void recommendManagement() {
		// 취업지원 관리 - 기업에 인재 추천
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("추천인재 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 추천현황 조회");
			System.out.println("2. 기업인재추천 등록");
			System.out.println("3. 기업인재추천 취소");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				recommendList();
			} else if (num.equals("2")) {
				recommendAdd();
			} else if (num.equals("3")) {
				recommendDelete();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}

	}

	/**
	 * 추천 현황 조회
	 */
	private void recommendList() {
		// 취업지원 관리 - 기업에 인재 추천 - 추천 현황 조회
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println("추천 인재 조회");
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

		aview.recommendStudentListView();

		pause();
	}

	/**
	 * 기업인재추천 등록
	 */
	private void recommendAdd() {
		// 취업지원 관리 - 기업에 인재 추천 - 기업인재추천 등록

		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("인재 추천 등록");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			// 추천 할 인재 선택
			aview.TalentedStudentListView();

			System.out.println("1. 인재 추천하기 2. 뒤로가기");
			System.out.print("번호를 선택하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 인재 추천하기 진행
				System.out.print("추천할 인재의 번호를 선택하세요 : ");
				num = scan.nextLine();

				// 추천할 인재의 객체
				TalentedStudentListDTO studentdto = tsdao.getTalentedStudent(num);

				aview.enterpriseListView();
				System.out.print("추천할 기업 번호를 선택하세요 : ");
				num = scan.nextLine();

				// 추천할 연계기업의 객체
				LinkCompanyDTO companydto = lcdao.getLinkCompany(num);

				// 두개의 객체를 전달해서 등록시킨다
				int result = rdao.recommendStudentADD(studentdto, companydto);

				if (result > 0) {
					System.out.println("추천 등록 성공");
				} else {
					System.out.println("추천 등록 실패");
				}

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력");
				pause();
				break;
			}

		}

	}

	/**
	 * 기업인재 추천 취소
	 */
	private void recommendDelete() {
		// 취업지원 관리 - 기업에 인재 추천 - 기업인재 추천 취소
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("기업 인재 추천 취소");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			aview.recommendStudentListView();
			// 인재 추천 취소 하기 진행
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 인재 추천 취소 2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("삭제할 기업인재 추천 번호를 선택하세요 : ");
				num = scan.nextLine();

				int result = rdao.recommendStudentDelete(num);

				if (result > 0) {
					System.out.println("인재 추천 취소 성공");
				} else {
					System.out.println("인재 추천 취소 실패");
				}

				pause();
				break;
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}
	}

	/**
	 * 취업 현황 조회 메뉴
	 */
	private void jobactivitiesManagement() {
		// 취업현황 조회
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("취업현황 조회");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정별");
			System.out.println("2. 수료생 검색");
			System.out.println("3. 수료생 목록");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				jobactivitiesCourse();
			} else if (num.equals("2")) {
				jobactivitiesIndividual();
			} else if (num.equals("3")) {
				completStudentList();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 과정별
	 */
	private void jobactivitiesCourse() {
		// 취업지원 관리 - 취업현황 조회 - 과정별

		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("과정별 조회");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			ArrayList<EndCourseListDTO> list = jadao.EndCourseList();

			for (EndCourseListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t\n", dto.getSeq(), dto.getCourseName(), dto.getStartDate(),
						dto.getEndDate(), dto.getTeacherName(), dto.getRoom());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정 선택하기 2. 뒤로가기");
			System.out.print("번호를 입력해 주세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 과정 번호 선택
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정번호를 입력하세요 :");
				num = scan.nextLine();

				// 과정 번호에 따른 수료생 목록 출력
				courseNumcompletStudentList(num);

				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 과정별 - 과정선택
	 */
	private void courseNumcompletStudentList(String num) {
		// 취업지원 관리 - 취업현황 조회 - 과정별 - 과정선택

		while (true) {
			ArrayList<CompletStudentListDTO> list = jadao.courseCompletStudentList(num);

			for (CompletStudentListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getJumin(), dto.getTel(),
						dto.getRegdate());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("수료생 수강번호를 입력하세요 :");
			num = scan.nextLine(); // reginum

			jobactivitiesMenu(num);

			pause();
			break;
		}

	}

	/**
	 * 과정별 - 과정선택 - 구직활동 / 취업정보
	 */
	private void jobactivitiesMenu(String reginum) {
		// 취업지원 관리 - 취업현황 조회 - 과정별 - 과정선택 - 구직활동/취업정보
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 구직활동 보기 2. 취업정보 보기 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 구직활동 내역 출력 메서드
				jobActivitesInfo(reginum);

			} else if (num.equals("2")) {
				// 취업정보 출력 메서드
				jobInfo(reginum);

			} else if (num.equals("3")) {
				break;

			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 수강번호를 받아서 구직활동 내역을 출력하는 메서드
	 * 
	 * @param reginum 수강번호
	 */
	private void jobActivitesInfo(String reginum) {

		QualificationDTO dto = jadao.getJobActivitesInfo(reginum);

		if (dto != null) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("보유 자격증 : " + dto.getLicense());
			System.out.println("이력서 주소 : " + dto.getResume());
			System.out.println("희망 직군 : " + dto.getJob());
			System.out.println("github 주소 : " + dto.getGithub());
			System.out.println("희망 연봉 : " + dto.getSalary());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} else {
			System.out.println("구직활동 정보가 존재하지 않습니다");
		}

		pause();
	}

	/**
	 * 수강번호를 받아서 취업정보를 출력하는 메서드
	 * 
	 * @param reginum 수강번호
	 */
	private void jobInfo(String reginum) {

		JobInfoDTO dto = jadao.getJobInfo(reginum);

		if (dto != null) {
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("취업 일 : " + dto.getStartDate());
			System.out.println("고용보험 여부 : " + dto.getInsurance());
			System.out.println("계약 형태 : " + dto.getForm());
			System.out.println("직군 : " + dto.getCareer());
			System.out.println("연봉 : " + dto.getIncome());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		} else {
			System.out.println("취업 정보가 존재하지 않습니다");

		}

		pause();
	}

	/**
	 * 수료생 검색
	 */
	private void jobactivitiesIndividual() {
		// 취업지원 관리 - 취업현황 조회 - 수료생 검색
		while (true) {
			System.out.println("━━━━━━━━━━━━━━━이름으로 검색어 입력━━━━━━━━━━━━━━━━━━");
			System.out.print("검색어를 입력하세요 : ");
			num = scan.nextLine(); // reginum

			ArrayList<CompletStudentListDTO> list = jadao.CompletStudentList(num);

			for (CompletStudentListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getJumin(), dto.getTel(),
						dto.getRegdate());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("수료생 수강번호를 입력하세요 :");
			num = scan.nextLine(); // reginum

			jobactivitiesMenu(num);

			break;
		}

	}

	/**
	 * 수료생 목록
	 */
	private void completStudentList() {
		// 취업지원 관리 - 취업현황 조회 - 수료생 목록
		while (true) {
			ArrayList<CompletStudentListDTO> list = jadao.CompletStudentList(null);

			for (CompletStudentListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getJumin(), dto.getTel(),
						dto.getRegdate());
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("수료생 수강번호를 입력하세요 :");
			num = scan.nextLine(); // reginum

			jobactivitiesMenu(num);

			break;
		}
	}

	/**
	 * 데이터 통계 관리 메뉴
	 */
	private void dataStatisticsManagement() {
		// 데이터 통계 관리
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("데이터 통계 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정별 출석률");
			System.out.println("2. 과정별 수료율");
			System.out.println("3. 과정별 취업률");
			System.out.println("4. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				attendanceRate();
			} else if (num.equals("2")) {
				completionRate();
			} else if (num.equals("3")) {
				employmentRate();
			} else if (num.equals("4")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 과정별 출석률
	 */
	private void attendanceRate() {
		// 데이터 통계 관리 - 과정별 출석률
		while (true) {

			aview.endCourseListView();
			num = scan.nextLine(); // 과정번호 입력

			if (num.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정 번호를 입력하세요 :");
				num = scan.nextLine(); // 과정번호 입력

				// 과정별 출석률 출력

				// 해당 과정의 수료생 리스트
				ArrayList<CompletStudentListDTO> list = jadao.courseCompletStudentList(num);

				// 수료생의 출결 데이터
				ArrayList<AttendanceStatisticsDTO> slist = dsdao.attendanceStatisticsList(list);

				// 선택한 과정의 상세정보
				System.out.println(
						"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━선택한 과정정보━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				aview.endCourseInfoView(num);

				System.out.println(
						"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━과정의 출결정보━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("[수강번호]\t[이름]\t[출석횟수]\t[결석횟수]\t[지각횟수]\t[조퇴횟수]\t[출석률]");

				for (AttendanceStatisticsDTO dto : slist) {
					System.out.printf("%s\t\t%s\t\t%d\t\t%d\t\t%d\t\t%d\t%f\n", dto.getReginum(), dto.getName(),
							dto.getAttendance(), dto.getAbsent(), dto.getLate(), dto.getLeave(),
							dto.getAttendanceRate());

				}
				System.out.println(
						"━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 과정별 수료율
	 */
	private void completionRate() {
		// 데이터 통계 관리 - 과정별 수료율
		while (true) {

			aview.endCourseListView();
			num = scan.nextLine();

			if (num.equals("1")) {
				// 과정별 수료율 출력
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정 번호를 입력하세요 :");
				num = scan.nextLine(); // 과정번호 입력

				// 선택한 과정의 상세정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━선택한 과정정보━━━━━━━━━━━━━━━━━━━━━━━━━");
				aview.endCourseInfoView(num);

				ArrayList<String> list = dsdao.completionRateInfo(num);
				System.out.println("━━━━━━━━━━━━━━━━━━━━━과정 수료율 정보━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("총 수강 인원 : " + (Integer.parseInt(list.get(0)) + Integer.parseInt(list.get(1))));
				System.out.println("수료한 인원 : " + list.get(0));
				System.out.println("중도 탈락 인원 : " + list.get(1));
				System.out.println("과정 수료율 : " + list.get(2) + "%");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━수료자 명단━━━━━━━━━━━━━━━━━━━━━━━━━━");

				ArrayList<CompletStudentListDTO> slist = jadao.courseCompletStudentList(num);

				for (CompletStudentListDTO dto : slist) {
					System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getJumin(),
							dto.getTel(), dto.getRegdate());
				}

				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				pause();

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 과정별 취업률
	 */
	private void employmentRate() {
		// 데이터 통계 관리 - 과정별 취업률
		while (true) {

			aview.endCourseListView();
			num = scan.nextLine();

			if (num.equals("1")) {
				// 과정별 취업률 출력
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정 번호를 입력하세요 :");
				num = scan.nextLine(); // 과정번호 입력

				// 선택한 과정의 상세정보
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━선택한 과정정보━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				aview.endCourseInfoView(num);

				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━취업률 정보━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				ArrayList<EmploymentRateDTO> list = dsdao.employmentRateInfo(num);

				for (EmploymentRateDTO dto : list) {
					System.out.printf("%s : %s\n", dto.getColumn(), dto.getValue());
				}

				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━취업한 수료생━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				ArrayList<CompletStudentListDTO> list2 = jadao.gotJobCompletStudentList(num);

				for (CompletStudentListDTO dto : list2) {
					System.out.printf("%s\t%s\t%s\t%s\t%s\n", dto.getReginum(), dto.getName(), dto.getJumin(),
							dto.getTel(), dto.getRegdate());
				}

				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

				pause();
				break;

			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}
	}

	/**
	 * 출결관리 메뉴
	 */
	private void attendanceManagement() {

		// 출결관리
		while (true) {

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("출결 관리");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 교육생 번호로 검색");
			System.out.println("2. 과정별");
			System.out.println("3. 뒤로가기");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("교육생 번호 입력 :");
				String stnum = scan.nextLine();
				searchStudentNum(stnum);
				
			} else if (num.equals("2")) {
				courseAttendance();
			} else if (num.equals("3")) {
				break;

			} else {
				System.out.println("잘못된 입력입니다");
				pause();
			}
		}
	}

	/**
	 * 교육생 번호로 검색
	 */
	private void searchStudentNum(String stnum) {
		// 출결관리 - 교육생 번호로 검색
		// 교육생 목록 출력
		while (true) {
			
			StudentDTO dto = sdao.getStudent(stnum);
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("이름 : " + dto.getName());
			System.out.println("주민번호 : " + dto.getJumin());
			System.out.println("전화번호 : " + dto.getTel());
			System.out.println("등록일 : " + dto.getRegdate());
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정출결 보기 , 2. 과목별 출결 보기 3. 뒤로가기");
			System.out.print("번호를 입력하세요 :");
			num = scan.nextLine();

			if (num.equals("1")) {
				courseAttList(stnum);

			} else if (num.equals("2")) {
				subjectAttList(stnum);

			} else if (num.equals("3")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;

			}

		}
	}

	/**
	 * 과정 출결 보기 학생 번호를 매개변수로 받아 과정의 출결정보를 모두 출력한다
	 * 
	 * @param stnum 학생번호
	 */
	private void courseAttList(String stnum) {

		ArrayList<AttendanceInfoDTO> list = adao.courseAttList(stnum);

		if (list != null) {
			System.out.println("[날짜]\t[입실시간]\t[퇴실시간]\t[출결상태]");
			for (AttendanceInfoDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\n", dto.getAlldate(), dto.getIntime(), dto.getOuttime(),
						dto.getAttstate());

			}
		} else {
			System.out.println("출결정보가 없습니다");
		}

		pause();

	}

	/**
	 * 과목별 출결 보기
	 * 
	 * @param stnum 학생번호
	 */
	private void subjectAttList(String stnum) {

		while (true) {
			ArrayList<SubjectListDTO> list = adao.subjectList(stnum);

			if (list != null) { // 과목 정보 출결 내용이 있을떄
				System.out.println("[과목번호]\t[과목명]\t[시작일]\t[종료일]\t[교재명]\t[강사명]");
				for (SubjectListDTO dto : list) {
					System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", dto.getSubjectseq(), dto.getSubjectname(),
							dto.getStartdate(), dto.getEnddate(), dto.getBookname(), dto.getTeachername());

				}

				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("1. 과목 번호 입력  2. 뒤로가기");
				System.out.print("번호를 입력하세요 :");
				num = scan.nextLine();

				if (num.equals("1")) {
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.print("과목 번호를 입력하세요 :");
					num = scan.nextLine();

					SubjectListDTO dto = adao.getsubjectInfo(num);
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
					System.out.println("과목 번호 : " + dto.getSubjectseq());
					System.out.println("과목명 : " + dto.getBookname());
					System.out.println("시작일 : " + dto.getStartdate());
					System.out.println("종료일 : " + dto.getEnddate());
					System.out.println("책이름 : " + dto.getBookname());
					System.out.println("강사명 : " + dto.getTeachername());
					System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

					subjectAttList(stnum, num);

					pause();

				} else if (num.equals("2")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다");
					pause();
					break;
				}

			} else { // 과목 정보 출결 내용이 없을때
				System.out.println("출결정보가 없습니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 학생번호, 과목번호를 매개변수로 과목의 출결정보를 출력하는 메서드
	 * 
	 * @param stnum      학생번호
	 * @param subjectnum 과목번호
	 */
	private void subjectAttList(String stnum, String subjectnum) {

		ArrayList<AttendanceInfoDTO> list = adao.subjectAttList(stnum, subjectnum);

		if (list != null) {
			System.out.println("[날짜]\t[입실시간]\t[퇴실시간]\t[출결상태]");
			for (AttendanceInfoDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\n", dto.getAlldate(), dto.getIntime(), dto.getOuttime(),
						dto.getAttstate());

			}
		} else {
			System.out.println("출결정보가 없습니다");

		}

	}

	/**
	 * 과정별
	 */
	private void courseAttendance() {
		// 출결관리 - 과정별
		while (true) {

			ArrayList<EndCourseListDTO> list = adao.allCourseList();

			for (EndCourseListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\t\n", dto.getSeq(), dto.getCourseName(), dto.getStartDate(),
						dto.getEndDate(), dto.getTeacherName(), dto.getRoom());
			}

			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 과정번호 입력  2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			num = scan.nextLine();

			if (num.equals("1")) {
				// 과정번호를 받아서 과정듣는 수강생 목록을 출력
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("과정번호를 입력하세요 : ");
				num = scan.nextLine();
				
				courseStudentList(num);
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}

		}

	}

	/**
	 * 과정번호를 받아서 과정듣는 수강생 목록을 출력
	 * @param courseNum 과정번호
	 */
	private void courseStudentList(String courseNum) {
		
		while (true) {
			
			ArrayList<StudentInfoListDTO> list = adao.courseStudentList(courseNum);
			
			for(StudentInfoListDTO dto : list) {
				System.out.printf("%s\t%s\t%s\t%s\t%s\n"
						,dto.getSeq()
						,dto.getName()
						,dto.getJumin()
						,dto.getTel()
						,dto.getRegdate());
			}
			
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println("1. 교육생 번호 선택  2. 뒤로가기");
			System.out.print("번호를 입력하세요 : ");
			num = scan.nextLine();
			
			if (num.equals("1")) {
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.print("교육생 번호를 입력하세요 : ");
				num = scan.nextLine();
				
				searchStudentNum(num);
				
			} else if (num.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
			
		}
		
		
		
	}

	private void pause() {
		System.out.print("엔터를 누르면 이전화면으로 돌아갑니다");
		String num = scan.nextLine();
	}

	private void basicinfoManagement() {
		boolean check = true;

		while(check) {
			aview.BasicInfoMenu();
			num = scan.nextLine();

			if (num.equals("1")) { // 과정 관리
				courseManagement();
			} else if (num.equals("2")) { // 과목 관리
				subjectManagement();
			} else if (num.equals("3")) { // 교재 관리
				bookManagement();
			} else if (num.equals("4")) { // 강의실 관리
				roomManagement();
			} else {
				System.out.println("잘못된 입력입니다");
				pause();
				break;
			}
		}

	}//basicinfoManagement()
		boolean loop = true;
		private void courseManagement() {
			ArrayList<CourseDTO> list = csdao.list(null);
			
			//관리자 - 기초정보 관리 - 과정 관리
			aview.HeadCourse();			//과정관리 리스트 머리
			aview.CourseList(list);		//과정관리 리스트 몸통
			aview.MenuCourse();			//과정관리 리스트 메뉴
			num = scan.nextLine();
			
			switch(num) {
			
			case "1" : // 과정 등록
				aview.Course();
				String course =scan.nextLine();
				aview.Purpose();
				String purpose = scan.nextLine();
				int resultAdd = csdao.addCourse(course, purpose);
				
				if(resultAdd > 0) {
					System.out.println("추가 완료");
				} else {
					System.out.println("추가X");
				}
				break;
			case "2" : // 과정 수정
				aview.Number();
				String number = scan.nextLine();
				aview.Course();
				course =scan.nextLine();
				aview.Purpose();
				purpose = scan.nextLine();
				
				int resultUpdate = csdao.UpdateCourse(number, course, purpose);
				
				if(resultUpdate > 0) {
					System.out.println("수정 완료!");
				} else {
					System.out.println("수정 실패!");
				}
				break;
					
			case "3" : // 과정 삭제
				aview.DeleteNumber();
				number = scan.nextLine();
				
				int resultDelete = csdao.DeleteCourse(number);
				
				if(resultDelete > 0) {
					System.out.println("삭제 완료!");
				} else {
					System.out.println("삭제 실패!");
				}
				break;
			
			default:
				loop = !loop;
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}


		private void bookManagement() {	//교재관리
		    
			
			ArrayList<BookDTO> list = bodao.list(null);
			
			aview.HeadBook();
			aview.BookList(list);
			aview.menuBook();
			num = scan.nextLine();
		
		switch(num) {
		case "1" : // 교재 등록
			aview.Book();
			String book =scan.nextLine();
			aview.Writer();
			String writer = scan.nextLine();
			aview.Publisher();
			String publisher = scan.nextLine();
			aview.Price();
			String price = scan.nextLine();
			aview.Count();
			String count = scan.nextLine();
						
			
			int resultAdd = bodao.addBook(book, writer, publisher, price, count);
			
			if(resultAdd > 0) {
				System.out.println("추가 완료");
			} else {
				System.out.println("추가X");
			}
			break;
		case "2" : // 과목 수정
			aview.Number();
			String seq = scan.nextLine();
			
			//seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
			BookDTO dto = bodao.getBook(seq);
			
			// 수정할 과정의 정보
			aview.InfoBook(dto);
			
			aview.Book();
			String name =scan.nextLine();
			
			if (name.equals("")) { //입력 내용이 없을때
				name = dto.getName();
			}
			
			aview.Writer();
			writer = scan.nextLine();
			
			if (writer.equals("")) { //입력 내용이 없을때
			writer = dto.getWriter();
			}
			
			aview.Publisher();
			publisher = scan.nextLine();
			
			if (publisher.equals("")) { //입력 내용이 없을때
			publisher = dto.getPublisher();
			}
			
			aview.Price();
			price = scan.nextLine();
			
			if (price.equals("")) { //입력 내용이 없을때
			price = dto.getPrice();
			}
			
			aview.Count();
			count = scan.nextLine();
			
			if (count.equals("")) { //입력 내용이 없을때
			count = dto.getCount();
			}
			
			BookDTO dto2 = new BookDTO();
			
			
			dto2.setSeq(seq);
			dto2.setName(name);
			dto2.setWriter(writer);
			dto2.setPublisher(publisher);
			dto2.setPrice(price);
			dto2.setCount(count);
			
			int resultUpdate = bodao.UpdateBook(dto2);
														
			if(resultUpdate > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패!");
			}
			
			break;
			
				
		case "3" : // 과목 삭제
			aview.DeleteNumber();
			seq = scan.nextLine();
			
			int resultDelete = bodao.DeleteBook(seq);
			
			if(resultDelete > 0) {
				System.out.println("삭제 완료!");
			} else {
				System.out.println("삭제 실패!");
			}
			break;
		
		default:
			loop = !loop;
			System.out.println("잘못된 입력입니다.");
			break;
		}
		}//bookManagement() 교재관리

			private void subjectManagement() { //과목 관리


				ArrayList<SubjectDTO> list = sbdao.list(null);

				aview.HeadSubject();
				aview.SubjectList(list);
				aview.menuSubject();
				num = scan.nextLine();

				switch (num) {
				case "1": // 과목 등록
					aview.Subject();
					String subject = scan.nextLine();
					aview.Duration();
					String duration = scan.nextLine();
					int resultAdd = sbdao.addSubject(subject, duration);

					if (resultAdd > 0) {
						System.out.println("추가 완료");
					} else {
						System.out.println("추가X");
					}
					break;
				case "2": // 과목 수정
					aview.Number();
					String seq = scan.nextLine();

					// seq정보를 주면 그기업의 정보를 반환시켜주는 메서드
					SubjectDTO dto = sbdao.getSubject(seq);

					// 수정할 과정의 정보
					aview.InfoSubject(dto);

					aview.Subject();
					String name = scan.nextLine();

					if (name.equals("")) { // 입력 내용이 없을때
						name = dto.getName();
					}

					aview.Duration();
					duration = scan.nextLine();

					if (duration.equals("")) { // 입력 내용이 없을때
						duration = dto.getDuration();
					}

					SubjectDTO dto2 = new SubjectDTO();

					dto2.setSeq(seq);
					dto2.setName(name);
					dto2.setDuration(duration);

					int resultUpdate = sbdao.UpdateSubject(dto2);

					if (resultUpdate > 0) {
						System.out.println("수정 완료!");
					} else {
						System.out.println("수정 실패!");
					}

					break;

				case "3": // 과목 삭제
					aview.DeleteNumber();
					seq = scan.nextLine();

					int resultDelete = sbdao.DeleteSubject(seq);

					if (resultDelete > 0) {
						System.out.println("삭제 완료!");
					} else {
						System.out.println("삭제 실패!");
					}
					break;

				default:
					loop = !loop;
					System.out.println("잘못된 입력입니다.");
					break;
				}

			}// 과목관리


			private void roomManagement() {
				// 관리자 - 기초정보 관리 - 강의실 관리
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("[강의실 관리]");
				System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
				System.out.println("1.");
				System.out.println();
				System.out.println();
			}// roomManagement() 강의실관리

			private void makecourseManagement() {
			}// makecourseManagement() 개설 과정 관리

			private void makesubjectManagement() {
			}// makesubjectManagement() 개설 과목 관리;

	}
