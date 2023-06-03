package rent_system;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class unique_number {
    private static final String FILE_NAME = "unique_numbers.txt";
    public static String Gen_Random_Number() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        boolean flag=true;
        while(flag) {
            int randomNumber = random.nextInt(900000) + 100000; // 6자리 숫자 생성
            String uniqueNumber = String.valueOf(randomNumber);
            sb.append(uniqueNumber);
            // 중복 여부 확인
            boolean isDuplicate = Check_Number(uniqueNumber);
            if (isDuplicate)
                continue;
            else
                flag=false;
        }
        //고유번호 기록
        Save_Num(sb.toString());
        return sb.toString();
    }

    public static void Save_Num(String uniqueNumber) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(uniqueNumber);
            writer.newLine();
            System.out.println("고유번호가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    public static void Load_Number(ArrayList<Video> Video_list) {
    	try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
            // 빈 파일로 생성하여 초기화
        } catch (IOException e) {
            System.out.println("파일 초기화 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
        	for (Video video : Video_list) {
        		writer.write(video.Vn_return());
        		writer.newLine();
    		}
        	System.out.println("고유번호가 파일에 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
    public static boolean Check_Number(String uniqueNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(uniqueNumber)) {
                    return true; // 중복된 고유번호 발견
                }
            }
        } catch (IOException e) {
            System.out.println("파일 열기 중 오류가 발생했습니다.");
        }
        return false; // 중복된 고유번호 없음
    }
    public static boolean Delete_Number(String uniqueNumber) {
        try {
            // 임시 파일 생성
            File tempFile = new File(FILE_NAME + ".tmp");
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;

            // 파일을 읽어 고유번호와 일치하는 줄이 있는지 확인하고 해당 줄을 건너뛰어 새로운 파일에 작성
            while ((line = reader.readLine()) != null) {
                if (line.equals(uniqueNumber)) {
                    found = true;
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }

            reader.close();
            writer.close();

            // 기존 파일 삭제 후 임시 파일 이름 변경
            File originalFile = new File(FILE_NAME);
            if (!originalFile.delete()) {
                System.out.println("기존 파일 삭제 중 오류가 발생했습니다.");
                return false;
            }
            if (!tempFile.renameTo(originalFile)) {
                System.out.println("임시 파일 이름 변경 중 오류가 발생했습니다.");
                return false;
            }

            if (found) {
                System.out.println("고유번호가 삭제되었습니다.");
                return true;
            } else {
                System.out.println("해당 고유번호가 파일에 존재하지 않습니다.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("파일 처리 중 오류가 발생했습니다.");
            e.printStackTrace();
            return false;
        }
    }
}