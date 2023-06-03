package rent_system;
import java.util.ArrayList;
import java.util.Iterator;
//회원정보 DB
public class Member {
	private String Name;
	private String Phnum;
	private ArrayList<Member_RentData> Rent_data;

    public Member(String name, String Phnum) {
        this.Name = name;
        this.Phnum = Phnum;
        Rent_data = new ArrayList<Member_RentData>();
    }
    //이름 저장
  	public void Name_get(String name) {this.Name=name;}
  	//이름 반환
  	public String Name_return(){return this.Name;}
  	//이름 출력
  	public void Name_prn() {
  		System.out.println("회원이름: "+Name);
  	}
    //전화번호 저장
    public void Pn_get(String Phnum) {this.Phnum = Phnum;}
    //전화번호 반환
    public String Pn_return() {return this.Phnum;}
    //전화번호 출력
    public void Pn_prn() {
    	System.out.println("전화번호: "+Phnum);
    }
    public void Mem_prn() {
    	Name_prn();
    	Pn_prn();
    }
    public void Rent_video(Member_RentData new_data) {
    	//대여한 인덱스가 기존 대여한 인덱스와 일치하는게 있는가
		for(int i=0; i<Rent_data.size();i++) {
			//있다면
			if(Rent_data.get(i).Unique_Num_return()==new_data.Unique_Num_return()) {
				//해당 인덱스에 추가하기
				new_data.Num_get(Rent_data.get(i).Num_return() + new_data.Num_return());
				Rent_data.set(i, new_data);
				return;
			}
		}
    	Rent_data.add(new_data);
    }
    public void return_video(Member_RentData new_data) {
    	//대여한 인덱스가 기존 대여한 인덱스와 일치하는게 있는가
		for(int i=0; i<Rent_data.size();i++) {
			//있다면
			if(Rent_data.get(i).Unique_Num_return().equals(new_data.Unique_Num_return())) {
				//수량이 크다면
				if(Rent_data.get(i).Num_return()>new_data.Num_return()) {
					//해당 인덱스에서 제거하기
					new_data.Num_get(Rent_data.get(i).Num_return() - new_data.Num_return());
					Rent_data.set(i, new_data);
					return;
				}
				//수량이 일치
				if(Rent_data.get(i).Num_return()==new_data.Num_return()) {
					Rent_data.remove(i);
					return;
				}
			}
		}
    }
    public boolean search_rent_video(Member_RentData new_data) {
    	//대여한 인덱스가 기존 대여한 인덱스와 일치하는게 있는가
		for(int i=0; i<Rent_data.size();i++) {
			if(Rent_data.get(i).Unique_Num_return().equals(new_data.Unique_Num_return()))
				if(Rent_data.get(i).Num_return()>=new_data.Num_return()) return true;
		}
		return false;
    }
    public Member_RentData rent_return (String Vn) {
    	for(int i=0; i<Rent_data.size();i++) {
    		if(Vn.equals(Rent_data.get(i).Unique_Num_return())) {
    			return Rent_data.get(i);
    		}
    	}
    	return null;
    }
    public ArrayList<Member_RentData> rent_return_all(){
    	return this.Rent_data;
    }
}