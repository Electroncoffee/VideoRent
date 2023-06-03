package rent_system;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Video_ArrayList {
	private ArrayList<Video> Video_list;
	//생성자 :: 메인함수에서 LinkedList <Video>객체를 참조받는다.
	public Video_ArrayList() {
		this.Video_list= Data_IO.loadVideos();
	}
	public ArrayList<Video> ReturnforIO(){
		return Video_list;
	}
	public void Reset() {
		Video_list = new ArrayList<Video>();
	}
	//출력
	public void prn_all() {
		int i=1;
		Video VodTemp;
		Iterator<Video> Vitr = Video_list.iterator();
		System.out.println("==비디오 출력==");
		while(Vitr.hasNext()) {
			VodTemp = Vitr.next();
			System.out.println("-"+i+"-");
			VodTemp.Vod_prn();
			System.out.println();
			i++;
		}
	}
	//검색
	public void prn_search() {
		Scanner sc = new Scanner(System.in);
		Iterator<Video> Vitr = Video_list.iterator();
		Video VodTemp;//Iterator 값 받아줄 변수
		System.out.println("==비디오 검색==");
		int i=1;//인덱스 카운트
		System.out.println("검색할 비디오의 이름 입력");
		String name = sc.next();
		while(Vitr.hasNext()) {//다음값이 있으면
			VodTemp = Vitr.next();//받아서
			if(VodTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("\n-"+i+"-");//인덱스 출력
				VodTemp.Vod_prn();//데이터 출력
				System.out.println();
			}
			i++;//카운트 세기
		}
	}
	//추가
	public void add() {
		Scanner sc = new Scanner(System.in);
		Video VodTemp;
		System.out.println("==비디오 추가==");
		System.out.print("이름 입력:");
		String name = sc.next();
		System.out.print("비디오수량 입력:");
		int count=Etc.NationalException();
		String num = unique_number.Gen_Random_Number();
		VodTemp = new Video(name,num,count);
		Video_list.add(VodTemp);
	}
	//삭제
	public void remove() {
		Scanner sc = new Scanner(System.in);
		Iterator<Video> Mitr = Video_list.iterator();
		long []remove_index= new long[Video_list.size()];
		int i=1;//인덱스 카운트
		int same=0;//같은놈 나오면 카운트
		Video VodTemp;//Iterator 값 받아줄 변수
		System.out.println("==비디오 삭제==");
		//remove_index 0으로 초기화
		for (int j = 0; j < remove_index.length; j++) {
		    remove_index[j] = 0;
		}
		System.out.println("삭제할 비디오의 이름 입력");
		String name = sc.next();
		while(Mitr.hasNext()) {//다음값이 있으면
			VodTemp = Mitr.next();//받아서
			if(VodTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("\n-"+i+"-");//인덱스 출력
				VodTemp.Vod_prn();//데이터 출력
				same++;
				remove_index[i-1]=1;
			}
			i++;//카운트 세기
		}
		//삭제할 데이터 없음
		if(same==0) {
			System.out.println("해당되는 비디오가 없습니다.");
			return;
		}
		//삭제할 데이터 유일
		if(same==1) {
			for(int j=0;j<Video_list.size();j++) {//리스트 전체 반복
				if(remove_index[j]==1){//1이들어있는인덱스가 삭제할 인덱스
					if(!Video_list.get(j).Rmc_return()) {
						System.out.println("현재 대여된 비디오가 남아있어 삭제가 불가합니다.");
						return;
					}
					unique_number.Delete_Number(Video_list.get(j).Vn_return());
					Video_list.remove(j);//삭제
				}
			}
			System.out.println("삭제되었습니다.");
			return;
		}
		//삭제할 데이터가 2개 이상일 때
		boolean flag = true;
		if(same>1) {
		    System.out.println("동일한 비디오가 " + same + "종류 있습니다.");
		    System.out.println("삭제할 비디오의 목차번호를 입력해주세요.");
			while (flag) {
			    String num = Etc.SixNumber();
			    for(int j=0; j<Video_list.size(); j++) {
			    	if(num.equals(Video_list.get(j).Vn_return())) {
						if(!Video_list.get(j).Rmc_return()) {
							System.out.println("현재 대여된 비디오가 남아있어 삭제가 불가합니다.");
							return;
						}
						unique_number.Delete_Number(Video_list.get(j).Vn_return());
						Video_list.remove(j);
						System.out.println("삭제완료");
			    		return;
			    	}
			    }
			    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
	//수정
	public void edit() {
		Scanner sc = new Scanner(System.in);
		Iterator<Video> Mitr = Video_list.iterator();
		int []edit_index= new int[Video_list.size()];
		int final_index = 0;//최종 수정할 인덱스
		int i=1;//인덱스 카운트
		int same=0;//같은놈 나오면 카운트
		Video VodTemp;//Iterator 값 받아줄 변수
		System.out.println("==비디오 수정==");
		//remove_index 0으로 초기화
		for (int j = 0; j < edit_index.length; j++) {
		    edit_index[j] = 0;
		}
		System.out.print("수정할 비디오의 이름입력:");
		String name = sc.next();
		while(Mitr.hasNext()) {//다음값이 있으면
			VodTemp = Mitr.next();//받아서
			if(VodTemp.Name_return().equals(name)) {//이름비교하고
				System.out.println("-"+i+"-");//인덱스 출력
				VodTemp.Vod_prn();//데이터 출력
				same++;
				edit_index[i-1]=1;
			}
			i++;//카운트 세기
		}
		//수정할 데이터가 없거나 하나 일 때
		if(same==0) {
			System.out.println("해당되는 비디오가 없습니다.");
			return;
		}
		//수정할 데이터 유일
		if(same==1) {
			for(int j=0;j<edit_index.length;j++) {//리스트 전체 반복
				if(edit_index[j]==1) {//1이들어있는인덱스가 삭제할 인덱스
					final_index=j;
				}
			}
		}
		//수정할 데이터가 2개 이상일 때
		String num;
		boolean flag=true;
		if(same>1) {
			System.out.println("동일한 비디오가 " + same + "종류 있습니다.");
			System.out.println("수정할 비디오의 비디오번호를 입력해주세요.\n입력:");
			while(flag) {
				num = Etc.SixNumber();
			    for(int j=0; j<Video_list.size(); j++) {
			    	if(num.equals(Video_list.get(j).Vn_return())) {
			    		final_index=j;
			    		flag=false;
			    		break;
			    	}
			    }
			    if(flag)
			    	System.out.println("--해당되는 비디오번호만 입력해주세요--");;
			}
		}
		if(!Video_list.get(final_index).Rmc_return()) {
			System.out.println("현재 대여된 비디오가 남아있어 수정이 불가합니다.");
			return;
		}
		
		//수정할 데이터 입력
		System.out.print("이름 입력: ");
		String edit_name = sc.next();
		System.out.print("비디오수량 입력: ");
		int count = Etc.NationalException();
		VodTemp = new Video(edit_name, Video_list.get(final_index).Vn_return(), count);
		Video_list.set(final_index, VodTemp);
		System.out.println("수정완료");
	}
	public ArrayList<Video> V_return(){
		return Video_list;
	}
	//대여검색
	public String rent_search() {
	    Scanner sc = new Scanner(System.in);
	    Iterator<Video> Mitr = Video_list.iterator();
	    long[] rent_index = new long[Video_list.size()];
	    int i = 1; // 인덱스 카운트
	    int same = 0; // 같은 놈 나오면 카운트
	    Video VodTemp; // Iterator 값 받아줄 변수

	    // rent_index 0으로 초기화
	    for (int j = 0; j < rent_index.length; j++) {
	        rent_index[j] = 0;
	    }

	    System.out.println("대여할 비디오의 이름 입력");
	    String name = sc.next();
	    while (Mitr.hasNext()) { // 다음 값이 있으면
	        VodTemp = Mitr.next(); // 받아서
	        if (VodTemp.Name_return().equals(name)) { // 이름 비교하고
	            System.out.println("\n-" + i + "-"); // 인덱스 출력
	            VodTemp.Vod_prn(); // 데이터 출력
	            same++;
	            rent_index[i - 1] = 1;
	        }
	        i++; // 카운트 세기
	    }

	    // 대여할 데이터 없음
	    if (same == 0) {
	        System.out.println("해당되는 비디오가 없습니다.");
	        return null;
	    }
	    // 대여할 데이터 유일
	    if (same == 1) {
	        for (int j = 0; j < Video_list.size(); j++) { // 리스트 전체 반복
	            if (rent_index[j] == 1) { // 1이 들어있는 인덱스가 대여할 인덱스
	                if (Video_list.get(j).Vc_return() > 0) {
	                    return Video_list.get(j).Vn_return();
	                } else {
	                    System.out.println("해당 비디오의 수량이 없습니다.");
	                    return null;
	                }
	            }
	        }
	        return null;
	    }
	    // 대여할 데이터가 2개 이상일 때
	    String num;
	    boolean flag=true;
	    if (same > 1) {
			System.out.println("동일한 비디오가 " + same + "종류 있습니다.");
			System.out.println("대여할 비디오의 비디오번호를 입력해주세요.\n입력:");
			while(flag) {
				num = Etc.SixNumber();
			    for(int j=0; j<Video_list.size(); j++) {
			    	if(num.equals(Video_list.get(j).Vn_return())) {
			    		if(Video_list.get(j).Vc_return()>0)
			    			return num;
			    		else
			    			System.out.println("해당 비디오는 현재 수량이 없습니다."); return null;
			    	}
			    }
			    if(flag)
			    	System.out.println("--해당되는 비디오번호만 입력해주세요--");;
			}
	    }
	    return null;
	}
	//대여할 책 갯수
	public Integer rent_num(String index) {
	    System.out.println("대여할 비디오 갯수 입력");
	    int num=Etc.IncloudZeroException();
	    int final_index;
	    final_index = Vntoindex(index);
	    if(final_index==-1)
	    	return null;
	    if(Video_list.get(final_index).Rmc_go(num)) {
	    	Video VodTemp = Video_list.get(final_index);
	    	VodTemp.Rc_get(VodTemp.Rc_return()+num);
	    	Video_list.set(final_index, VodTemp);
	    	return num;
	    }
	    System.out.println("해당 비디오가 수량만큼 없습니다.");
	    return null;
	}
	//대여출력(현재 수정)
	public void rent_data_prn(ArrayList<Member_RentData> data) {
		System.out.println("-대여목록-");
		for(int i=0; i<data.size();i++) {
			int index = Vntoindex(data.get(i).Unique_Num_return());
			int num = data.get(i).Num_return();
			System.out.println(Video_list.get(index).Name_return() + " " + num + "개");
		}
	}
	public String return_search(int index, ArrayList<Member_RentData> data) {
		Scanner sc = new Scanner(System.in);
	    Iterator<Member_RentData> Ditr = data.iterator();
	    int same = 0; // 같은 놈 나오면 카운트
	    Member_RentData data_index; // Iterator 값 받아줄 변수
	    String[] same_index = new String[data.size()];
	    for(int j=0;j<data.size();j++) same_index[j]= null;
	    rent_data_prn(data);
	    System.out.println("반납할 비디오의 이름 입력");
	    String name = sc.next();
	    
	    while (Ditr.hasNext()) { // 다음 값이 있으면
	        data_index = Ditr.next(); // 받아서
	        int D_index = Vntoindex(data_index.Unique_Num_return());
	        //인덱스의 비디오 이름과 입력한 이름이 일치하는가
	        if (Video_list.get(D_index).Name_return().equals(name)) { // 이름 비교하고
	            System.out.println("\n-" + (same+1) + "-"); // 인덱스 출력
	            Video_list.get(D_index).Vod_prn(); // 데이터 출력
	            same_index[same] = data_index.Unique_Num_return();
	            same++;
	        }
	    }
	    //일치 데이터 없음
	    if(same==0) {
	        System.out.println("해당되는 비디오가 없습니다.");
	        return null;
	    }
	    if(same==1) {
	    	return same_index[0];
	    }
	    String final_index;
	    if(same>1) {
	    	boolean flag = true;
        	System.out.println("동일한 비디오가 " + same + "개 있습니다.");
            System.out.println("대여할 비디오넘버를 입력해주세요.");
            //조건맞을때까지 입력받기
            do {
            	final_index = Etc.SixNumber();
            	for(int i=0;i<same_index.length;i++) {
            		if(final_index.equals(same_index[i]))
            			return final_index;
            	}
            	System.out.println("유효한 비디오넘버를 입력해주세요.");
			} while (true);
	    }
		return null;
	}
	public Integer return_count(Member_RentData data) {
		Scanner sc = new Scanner(System.in);
	    System.out.println("반납할 비디오 갯수 입력");
	    while(true) {
	    	int count = Etc.NationalException();
	    	if(count<=data.Num_return())
	    		return count;
	    	System.out.println("대여한 책보다 더 반납할 수 없습니다.\n 다시입력해주세요.");
	    }
	}
	public void return_v(Member_RentData data) {
		int index = Vntoindex(data.Unique_Num_return());
		Video VodTemp = Video_list.get(index);
		VodTemp.rreturn(data.Num_return());
		Video_list.set(index, VodTemp);
	}
	public int Vntoindex(String index) {
	    for(int i=0; i<Video_list.size(); i++) {
	    	if(index.equals(Video_list.get(i).Vn_return()))
	    		return i;
	    }
	    return -1;
	}
}
