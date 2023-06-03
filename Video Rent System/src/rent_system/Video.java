package rent_system;
public class Video {
	private String Name;
	private String Vodnum;
	private int Vodcount;
	private int Rentcount;

    public Video(String name, String Vodnum, int Vodcount) {
        this.Name = name;
        this.Vodnum = Vodnum;
        this.Vodcount = Vodcount;
        this.Rentcount = 0;
    }
    //이름 저장
  	public void Name_get(String name) {this.Name=name;}
  	//이름 반환
  	public String Name_return(){return this.Name;}
  	//이름 출력
  	public void Name_prn() {
  		System.out.println("비디오명: "+Name);
  	}
    //비디오번호 저장
    public void Vn_get(String Vodnum) {this.Vodnum = Vodnum;}
    //비디오번호 반환
    public String Vn_return() {return this.Vodnum;}
    //비디오번호 출력
    public void Vn_prn() {
    	System.out.println("비디오넘버: "+Vodnum);
    }
    //비디오수량 저장
    public void Vc_get(int Vodcount) {this.Vodcount = Vodcount;}
    //비디오수량 반환
    public int Vc_return() {return this.Vodcount;}
    //비디오수량 출력
    public void Vc_prn() {
    	System.out.print("총 수량: "+Vodcount);
    	System.out.println(", 잔여 수량: "+(Vodcount-Rentcount));
    }
    //대여된 수량 저장
    public void Rc_get(int Rentcount) {this.Rentcount = Rentcount;}
    //대여된 수량 반환
    public int Rc_return() {return this.Rentcount;}
    //대여 후 잔여 수량 반환
    public boolean Rmc_go(int num) {
    	if((Vodcount-Rentcount)>=num)
    		return true;
    	else
    		return false;
    	}
    public boolean Rmc_return() {
    	if(Rentcount==0)
    		return true;
    	else
    		return false;
    	}
    public void rreturn(int data) {
    	this.Rentcount-=data;
    }
    //간단 출력
    public void Vod_prn() {
    	Name_prn();
    	Vn_prn();
    	Vc_prn();
    }
}
