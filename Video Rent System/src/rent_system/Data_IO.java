package rent_system;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class Data_IO {
	public static final String FILE_MEM = "Member.txt";
	public static final String FILE_VOD = "Video.txt";
	public static final String FILE_UN = "unique_numbers.txt";
    public static void Json_in_M(ArrayList<Member> member_list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String memberJson = gson.toJson(member_list);
        saveJsonToFile(memberJson, FILE_MEM);
    }
    public static void Json_in_V(ArrayList<Video> video_list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String videoJson = gson.toJson(video_list);
        saveJsonToFile(videoJson, FILE_VOD);
    }
    private static void saveJsonToFile(String json, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
            System.out.println(fileName + "에 데이터가 저장되었습니다.");
        } catch (IOException e) {
            System.out.println(fileName + "에 데이터 저장 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    public static ArrayList<Member> loadMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_MEM))) {
            Gson gson = new Gson();
            Type memberListType = new TypeToken<ArrayList<Member>>() {}.getType();
            return gson.fromJson(reader, memberListType);
        } catch (Exception e) {
            System.out.println("Error loading Members: " + e.getMessage());
            return new ArrayList<Member>();
        }
    }
    public static ArrayList<Video> loadVideos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_VOD))) {
            Gson gson = new Gson();
            Type VideoListType = new TypeToken<ArrayList<Video>>() {}.getType();
            return gson.fromJson(reader, VideoListType);
        } catch (Exception e) {
            System.out.println("Error loading Videos: " + e.getMessage());
            return new ArrayList<Video>();
        }
    }
    //--최초 데이터 로드 후 해당 데이터가 유효한지 확인하기--//
    public static boolean File_Check(ArrayList<Member> member_list, ArrayList<Video> video_list) {
    	if(File_Check_1(member_list))
    		if(File_Check_2(member_list))
    			if(File_Check_3(video_list))
    				if(File_Check_4(video_list))
    					if(File_Check_5(member_list))
    						if(File_Check_6(member_list, video_list))
    							if(File_Check_7(member_list, video_list))
    								return true;
    	return false;
    }
    //1.회원의 전화번호는 모두 숫자인가
    private static boolean File_Check_1(ArrayList<Member> member_list) {
    	for (Member member : member_list) {//회원의
    		String PhNum = member.Pn_return();
    	    for (int i = 0; i < PhNum.length(); i++) {//전화번호에
    	        if (!Character.isDigit(PhNum.charAt(i))) {//숫자가 아닌것이 있다면
    	        	System.out.println("File_Check_1 failed");
    	            return false;
    	        }
    	    }
    	}
    	System.out.println("File_Check_1 complete");
    	return true;
    }
    //2.회원의 대여정보 고유넘버는 모두 숫자인가
    private static boolean File_Check_2(ArrayList<Member> member_list) {
    	for (Member member : member_list) {//회원의
    		for (Member_RentData RData : member.rent_return_all()) {//대여정보의 고유넘버가
        		String RNum = RData.Unique_Num_return();
        	    for (int i = 0; i < RNum.length(); i++) {
        	        if (!Character.isDigit(RNum.charAt(i))) {//숫자가 아닌것이 있다면
        	        	System.out.println("File_Check_2 failed");
        	            return false;
        	        }
        	    }
			}
    	}
    	System.out.println("File_Check_2 complete");
    	return true;
    }
    //3.비디오의 수량과 대여수량이 유효한 숫자인가
    private static boolean File_Check_3(ArrayList<Video> video_list) {
    	for (Video video : video_list) {//비디오의
    		if(video.Vc_return()<0) {//수량이 음수인가
    			System.out.println("File_Check_3 failed");
    			return false;
    		}
    			
    			
    		if(video.Rc_return()<0) {//대여수량이 음수인가
    			System.out.println("File_Check_3 failed");
    			return false;
    		}
    	}
    	System.out.println("File_Check_3 complete");
    	return true;
    }
    //4.모든 비디오 고유넘버는 모두 숫자인가
    private static boolean File_Check_4(ArrayList<Video> video_list) {
    	for (Video video : video_list) {//비디오의
    		String UN = video.Vn_return();
    	    for (int i = 0; i < UN.length(); i++) {//전화번호에
    	        if (!Character.isDigit(UN.charAt(i))) {//숫자가 아닌것이 있다면
    	        	System.out.println("File_Check_4 failed");
    	            return false;
    	        }
    	    }
    	}
    	System.out.println("File_Check_4 complete");
    	return true;
    }
    //5.한명의 회원의 대여정보에 중복된 고유넘버가 있는가
    private static boolean File_Check_5(ArrayList<Member> member_list) {
    	for (Member member : member_list) {//회원의
    		ArrayList<Member_RentData> MRData = member.rent_return_all();//모든대여정보
    		int i=1;
    		for (Member_RentData RData : member.rent_return_all()) {//해당대여정보와
    			String UN = RData.Unique_Num_return();
    			for (int j=i; j<MRData.size();j++) {
					if(UN.equals(MRData.get(j).Unique_Num_return())) {//다른대여정보가 같으면
						System.out.println("File_Check_5 failed");
						return false;
					}
				}
    			i++;
			}
    	}
    	System.out.println("File_Check_5 complete");
    	return true;
    }
    //6.회원이 대여한 모든 비디오가 존재하는가
    private static boolean File_Check_6(ArrayList<Member> member_list, ArrayList<Video> video_list) {
    	int MList_size = member_list.size();
    	int i=0;
    	for (Member member : member_list) {//회원의
    		int MRList_size = member.rent_return_all().size();
    		int j=0;
    		for(Member_RentData RData : member.rent_return_all()) {//대여정보 하나뽑기
    			for (Video video : video_list) {//모든 비디오와 비교하기
					String UN = RData.Unique_Num_return();
					String VUN = video.Vn_return();
					if(UN.equals(VUN)) {//고유번호가 같으면 체크하고 break
						j++;
						break;
					}
				}
    		}
    		if(MRList_size!=j) {//모든 대여 데이터가 확인되 않으면
    			System.out.println("File_Check_6 failed");
    			return false;
    		}
    		i++;
		}
    	if(MList_size!=i)
    		return false;
    	System.out.println("File_Check_6 complete");
    	return true;
    }
    //7.회원이 대여한 비디오와 잔여 비디오개수를 전부 합치면 총수량과 같은가
    private static boolean File_Check_7(ArrayList<Member> member_list, ArrayList<Video> video_list) {
    	for (Video video : video_list) {//비디오의
			int RVNum = video.Rc_return();//대여된 갯수가져오기
			int j=0;
			for (Member member : member_list) {//회원의
				for (Member_RentData RData : member.rent_return_all()) {//대여정보의
					String UN = RData.Unique_Num_return();
					String VUN = video.Vn_return();
					if(UN.equals(VUN)) {//고유번호가 같으면 더하기
						j+=RData.Num_return();
					}
				}
			}
			if(RVNum != j) {//해당 비디오의 대여량이 동일하지 않으면
				System.out.println("File_Check_7 failed");
				return false;
			}
		}
    	System.out.println("File_Check_7 complete");
    	return true;
    }
}