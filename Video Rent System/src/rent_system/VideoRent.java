package rent_system;

public class VideoRent {
	//대여
	public static void rent(Member_ArrayList MemList, Video_ArrayList VodList) {
		//대여할 회원의 인덱스 받아오기
		Integer M_rent_data = MemList.rent_search();
		if(M_rent_data == null) {
			return;
		}
		//대여할 비디오의 인덱스 받아오기
		String Vod_index = VodList.rent_search();
		if(Vod_index == null) {
			return;
		}
		//대여할 비디오 책갯수 받아오기
		Integer Vod_num = VodList.rent_num(Vod_index);
		if(Vod_num == null) {
			return;
		}
		Member_RentData V_rent_data = new Member_RentData(Vod_index,Vod_num);
		//전부 충족되면 대여진행
		MemList.rent(V_rent_data, M_rent_data);
	}
	//대여목록 출력
	public static void rent_prn(Member_ArrayList MemList, Video_ArrayList VodList) {
		int j=1;
		for(int i=0; i< MemList.data_size();i++) {
			if(MemList.is_rent(i)) {
				System.out.println("--"+j+"--");
				MemList.rent_name_prn(i);
				VodList.rent_data_prn(MemList.rent_data_return_all(i));
				j++;
			}
		}
	}
	//반납
	public static void video_return(Member_ArrayList MemList, Video_ArrayList VodList) {
		//반납할 회원의 인덱스 받아오기
		Integer M_return_data = MemList.return_search();
		if(M_return_data == null) {
			return;
		}
		int index = M_return_data.intValue();
		//반납할 비디오 넘버 받아오기
		String Vod_index = VodList.return_search(index, MemList.rent_data_return_all(index));
		if(Vod_index == null) {
			return;
		}
		//반납할 갯수
		Integer Vod_count = VodList.return_count(MemList.rent_data_return(index,Vod_index));
		Member_RentData final_return_index = new Member_RentData(Vod_index,Vod_count.intValue());
		VodList.return_v(final_return_index);
		MemList.return_video(M_return_data.intValue(), final_return_index);
		
	}
}
