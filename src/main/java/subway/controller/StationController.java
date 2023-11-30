package subway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import subway.domain.Station;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.service.StationService;
import view.StationManagerView;

public class StationController implements Controller {
    public static final String BACK_TO_MAIN_OPTION = "B";
    private final Map<String, Runnable> functions = new HashMap<>();

    public StationController() {
        initFunctions();
    }

    private void initFunctions() {
        functions.put("1", this::stationRegistration);
        functions.put("2", this::stationDeletion);
        functions.put("3", this::printStations);
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

    public void stationRegistration() {
        ErrorHandler.retryUntilSuccess(() -> {
            String stationName = StationManagerView.scanStationNameToRegister();
            StationService.registerStation(stationName);
            StationManagerView.printInfo("지하철 역이 등록되었습니다.");
        });
    }

    public void stationDeletion() {
        ErrorHandler.retryUntilSuccess(() -> {
            String stationName = StationManagerView.scanStationNameToDelete();
            StationService.deleteStation(stationName);
            StationManagerView.printInfo("지하철 역이 삭제되었습니다.");
        });
    }

    public void printStations() {
        List<Station> stations = StationService.getStations();
        StationManagerView.printStations(stations);
    }

    private String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            StationManagerView.printOptions();
            String option = StationManagerView.requestOption();
            validateOption(option);
            return option;
        });
    }

    private void validateOption(String option) {
        if (!functions.containsKey(option)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("선택할 수 없는 기능입니다."));
        }
    }
}