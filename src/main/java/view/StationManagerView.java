package view;

import java.util.List;
import java.util.Scanner;
import subway.domain.Station;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class StationManagerView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void printOptions() {
        System.out.println("## 역 관리 화면");
        System.out.println("1. 역 등록");
        System.out.println("2. 역 삭제");
        System.out.println("3. 역 조회");
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

    public static String scanStationNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 등록할 역 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static String scanStationNameToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            System.out.println(LINE_SEPARATOR + "## 삭제할 역 이름을 입력하세요.");
            String userInput = SCANNER.nextLine();
            Validator.checkBlank(userInput, ErrorMessage.getMessage("공백이 입력되었습니다."));
            return userInput;
        });
    }

    public static void printStations(List<Station> stations) {
        System.out.println("## 역 목록");
        for (Station station : stations) {
            printInfo(station.getName());
        }
    }

    public static void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}