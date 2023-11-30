package subway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.domain.Line;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.service.LineService;
import view.LineManagerView;

public class LineController implements Controller {
    public static final String BACK_TO_MAIN_OPTION = "B";
    private final Map<String, Runnable> functions = new HashMap<>();

    public LineController() {
        initFunction();
    }

    private void initFunction() {
        functions.put("1", this::lineRegistration);
        functions.put("2", this::lineDeletion);
        functions.put("3", this::printLines);
        functions.put("B", null);
    }

    @Override
    public void run() {
        String option = requestOption();
        if (option.equals(BACK_TO_MAIN_OPTION)) {
            return;
        }
        functions.get(option).run();
    }

    public void lineRegistration() {
        ErrorHandler.retryUntilSuccess(() -> {
            String lineName = LineManagerView.scanLineNameToRegister();
            String firstStationName = LineManagerView.scanFirstStationNameToRegister();
            String lastStationName = LineManagerView.scanLastStationNameToRegister();
            LineService.registerLine(lineName, firstStationName, lastStationName);
            LineManagerView.printInfo("지하철 노선이 등록되었습니다.");
        });
    }

    public void lineDeletion() {
        ErrorHandler.retryUntilSuccess(() -> {
            String stationName = LineManagerView.scanLineNameToDelete();
            LineService.deleteLineByName(stationName);
            LineManagerView.printInfo("지하철 노선이 삭제되었습니다.");
        });
    }

    public void printLines() {
        List<Line> lines = LineService.getLines();
        LineManagerView.printLines(lines);
    }

    private String requestOption() {
        LineManagerView.printOptions();
        String option = LineManagerView.requestOption();
        validateOption(option);
        return option;
    }

    private void validateOption(String option) {
        if (!functions.containsKey(option)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("선택할 수 없는 기능입니다."));
        }
    }
}