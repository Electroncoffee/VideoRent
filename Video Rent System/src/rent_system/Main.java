package rent_system;
public class Main {
	public static void main(String[] args) {
		Member_ArrayList MemList = new Member_ArrayList();
		Video_ArrayList VodList = new Video_ArrayList();
		if(!Data_IO.File_Check(MemList.ReturnforIO(), VodList.ReturnforIO())) {
			MemList.Reset();
			VodList.Reset();
		}
		unique_number.Load_Number(VodList.ReturnforIO());
		int n = 0;
		Boolean flag = true;
		while (flag) {
			System.out.println("------------------------------------------------------------------");
			System.out.println("1. 회원 추가     | 2. 회원 수정  | 3. 회원 삭제   | 4. 회원 검색  | 5. 회원 출력");
			System.out.println("------------------------------------------------------------------");
			System.out.println("6. 비디오 추가    | 7. 비디오 수정 | 8. 비디오 삭제 | 9. 비디오 검색 | 10. 비디오 출력");
			System.out.println("------------------------------------------------------------------");
			System.out.println("11. 대여        | 12. 대여 출력 | 13. 반납	 | 14. 종료");
			System.out.println("------------------------------------------------------------------");
			System.out.print("실행할 번호를 입력하세요:");
			n = Etc.NumberException();
			switch (n) {
			case 1: // 회원추가
				MemList.add();
				break;
			case 2: // 회원수정
				MemList.edit();
				break;
			case 3: // 회원삭제
				MemList.remove();
				break;
			case 4: // 회원검색
				MemList.prn_search();
				break;
			case 5: // 회원출력
				MemList.prn_all();
				break;
			case 6: // 비디오추가
				VodList.add();
				break;
			case 7: // 비디오수정
				VodList.edit();
				break;
			case 8: // 비디오삭제
				VodList.remove();
				break;
			case 9: // 비디오검색
				VodList.prn_search();
				break;
			case 10: //비디오출력
				VodList.prn_all();
				break;
			case 11: //비디오대여
				VideoRent.rent(MemList, VodList);
				break;
			case 12: //대여목록 출력
				VideoRent.rent_prn(MemList, VodList);
				break;
			case 13: //전화번호
				VideoRent.video_return(MemList, VodList);
				break;
			case 14: //종료
				flag=false;
				System.out.println("--프로그램을 종료합니다.--");
				break;
			default:
				System.out.println("--1~13사이 정수를 입력해주세요.--");
			}
		}
		Data_IO.Json_in_M(MemList.ReturnforIO());
		Data_IO.Json_in_V(VodList.ReturnforIO());
	}
}