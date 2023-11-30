package view;

import java.util.Scanner;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class MainView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printOptions() {
        System.out.println("## 메인 화면");
        System.out.println("1. 역 관리");
        System.out.println("2. 노선 관리");
        System.out.println("3. 구간 관리");
        System.out.println("4. 지하철 노선도 출력");
        System.out.println("Q. 종료");
    }

    public static String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 원하는 기능을 선택하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }
}