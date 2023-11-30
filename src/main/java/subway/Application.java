package subway;

import subway.config.SubwayInitializer;
import subway.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        SubwayInitializer.initStations();
        SubwayInitializer.initLines();
        mainController.run();
    }
}