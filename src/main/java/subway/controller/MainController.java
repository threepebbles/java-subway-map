package subway.controller;

import java.util.HashMap;
import java.util.Map;
import subway.error.ErrorHandler;
import subway.error.ErrorMessage;
import view.MainView;

public class MainController implements Controller {
    public static final String END_OPTION = "Q";
    private final Map<String, Controller> controllers = new HashMap<>();

    public MainController() {
        initControllers();
    }

    private void initControllers() {
        controllers.put("1", new StationController());
        controllers.put("2", new LineController());
        controllers.put("3", new SectionController());
        controllers.put("4", new SubwayMapController());
        controllers.put("Q", null);
    }

    @Override
    public void run() {
        while (true) {
            String option = requestOption();
            if (option.equals(END_OPTION)) {
                break;
            }
            controllers.get(option).run();
        }
    }

    private String requestOption() {
        return (String) ErrorHandler.retryUntilSuccessWithReturn(() -> {
            MainView.printOptions();
            String option = MainView.requestOption();
            validateOption(option);
            return option;
        });
    }

    private void validateOption(String option) {
        if (!controllers.containsKey(option)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("선택할 수 없는 기능입니다."));
        }
    }
}