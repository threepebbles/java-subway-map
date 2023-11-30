package view;

import java.util.Scanner;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class SectionManagerView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printOptions() {
        System.out.println("## 구간 관리 화면");
        System.out.println("1. 구간 등록");
        System.out.println("2. 구간 삭제");
        System.out.println("B. 돌아가기");
    }

    public static String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 원하는 기능을 선택하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static String requestLineName() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 노선을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static String requestStationName() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 역이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static String requestOrder() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 순서를 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            Validator.checkIsInteger(userInput, ErrorMessage.getMessage("정수가 아닙니다."));
            Validator.checkIsPositiveInteger(Integer.parseInt(userInput), ErrorMessage.getMessage("양수가 아닙니다."));
            return userInput;
        });
    }

    public static String requestLineNameToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 삭제할 구간의 노선을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static String requestStationNameToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 삭제할 구간의 역을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}