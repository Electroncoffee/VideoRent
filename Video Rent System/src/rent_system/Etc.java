package rent_system;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Etc {
	//숫자로만 문자열 예외처리
	public static String StringonlyNumber() {
		Scanner sc = new Scanner(System.in);
        String str = "";
        while (true) {
        	str = sc.nextLine();
        	if (str.matches("[0-9]+")) {
                break;
            }else {
            	System.out.println("--숫자만 입력해주세요.--");
            	System.out.print("재입력: ");
            	}
        }
        return str;
	}
	//int형 입력 예외처리
	public static int NumberException() {
		Scanner sc = new Scanner(System.in);
        int number = 0;
        while (true) {
            try {
                number = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("--정수를 입력해주세요.--");
                System.out.print("재입력: ");
                sc.nextLine(); // 버퍼를 비워줌
            }
        }
        return number;
	}
	//자연수 + 0 입력 예외처리
	public static int NationalException() {
		int integer;
		while(true) {
			integer= NumberException();
			if(integer>=0)
				break;
			else {
				System.out.println("--0을 포함한 양수를 입력해주세요.");
				System.out.print("재입력: ");
			}
		}
		return integer;
	}
	//자연수 입력 예외처리
	public static int IncloudZeroException() {
		int integer;
		while(true) {
			integer= NumberException();
			if(integer>0)
				break;
			else {
				System.out.println("--자연수를 입력해주세요.");
				System.out.print("재입력: ");
			}
		}
		return integer;
	}
	//6자리 자연수 입력 예외처리
    public static String SixNumber() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("6자리 숫자를 입력하세요:");
            String input = scanner.nextLine();
            if(input.matches("[0-9]+")) {
            	if(input.length()==6)
            		return input;
            	else
            		System.out.println("6자리 숫자를 입력해야 합니다.");
            } else
            	System.out.println("숫자만 입력하세요.");
        }
    }
}