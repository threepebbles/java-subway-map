package subway.controller;

import java.util.HashMap;
import java.util.Map;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import subway.service.LineService;
import subway.service.SectionService;
import subway.service.StationService;
import view.SectionManagerView;

public class SectionController implements Controller {
    public static final String BACK_TO_MAIN_OPTION = "B";
    private final Map<String, Runnable> functions = new HashMap<>();

    public SectionController() {
        initFunctions();
    }

    private void initFunctions() {
        functions.put("1", this::sectionRegistration);
        functions.put("2", this::sectionDeletion);
        functions.put(BACK_TO_MAIN_OPTION, null);
    }

    @Override
    public void run() {
        String option = requestOption();
        if (option.equals(BACK_TO_MAIN_OPTION)) {
            return;
        }
        functions.get(option).run();
    }

    private void sectionRegistration() {
        ErrorHandler.retryUntilSuccess(() -> {
            String lineName = requestLineNameToRegister();
            String stationName = requestStationNameToRegister();
            int order = requestOrder();
            SectionService.registerSection(lineName, stationName, order);
            SectionManagerView.printInfo("구간이 등록되었습니다.");
        });
    }

    private void sectionDeletion() {
        ErrorHandler.retryUntilSuccess(() -> {
            String lineName = requestLineToDelete();
            String stationName = requestStationToDelete();
            SectionService.deleteSection(lineName, stationName);
            SectionManagerView.printInfo("구간이 삭제되었습니다.");
        });
    }

    private String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            SectionManagerView.printOptions();
            String option = SectionManagerView.requestOption();
            validateOption(option);
            return option;
        });
    }

    private void validateOption(String option) {
        if (!functions.containsKey(option)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("선택할 수 없는 기능입니다."));
        }
    }

    private String requestLineNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String lineName = SectionManagerView.requestLineName();
            LineService.findLineByName(lineName);
            return lineName;
        });
    }

    private String requestStationNameToRegister() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String stationName = SectionManagerView.requestStationName();
            StationService.findStationByName(stationName);
            return stationName;
        });
    }

    private Integer requestOrder() {
        return (Integer) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String order = SectionManagerView.requestOrder();
            return Integer.parseInt(order);
        });
    }

    private String requestLineToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String lineName = SectionManagerView.requestLineNameToDelete();
            LineService.findLineByName(lineName);
            return lineName;
        });
    }

    private String requestStationToDelete() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            String stationName = SectionManagerView.requestStationNameToDelete();
            StationService.findStationByName(stationName);
            return stationName;
        });
    }
}