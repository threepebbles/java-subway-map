package view;

import java.util.List;
import java.util.Scanner;
import subway.domain.Line;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class LineManagerView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String NOT_PROPER_INPUT = ErrorMessage.getMessage("올바르지 않은 입력입니다.");
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printOptions() {
        System.out.println("## 노선 관리 화면");
        System.out.println("1. 노선 등록");
        System.out.println("2. 노선 삭제");
        System.out.println("3. 노선 조회");
        System.out.println("B. 돌아가기");
    }

    public static String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 원하는 기능을 선택하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, NOT_PROPER_INPUT);
            return userInput;
        });
    }


    public static String scanLineNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 등록할 노선 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, NOT_PROPER_INPUT);
            return userInput;
        });
    }

    public static String scanFirstStationNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 등록할 노선의 상행 종점역 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, NOT_PROPER_INPUT);
            return userInput;
        });
    }

    public static String scanLastStationNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 등록할 노선의 하행 종점역 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, NOT_PROPER_INPUT);
            return userInput;
        });
    }

    public static String scanLineNameToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 등록할 노선 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, NOT_PROPER_INPUT);
            return userInput;
        });
    }

    public static void printLines(List<Line> lines) {
        System.out.println("## 노선 목록");
        for (Line line : lines) {
            printInfo(line.getName());
        }
    }

    public static void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}