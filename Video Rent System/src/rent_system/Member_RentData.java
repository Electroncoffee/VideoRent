package rent_system;

public class Member_RentData {
	private String Unique_Num;
	private int Num;
	public Member_RentData() {}
	public Member_RentData(String Unique_Num, int Num) {
		this.Unique_Num = Unique_Num;
		this.Num = Num;
	}
	public void Unique_Num_get(String Unique_Num) {this.Unique_Num=Unique_Num;}
  	//이름 반환
  	public String Unique_Num_return(){return this.Unique_Num;}
    //전화번호 저장
    public void Num_get(int Num) {this.Num = Num;}
    //전화번호 반환
    public int Num_return() {return this.Num;}
}