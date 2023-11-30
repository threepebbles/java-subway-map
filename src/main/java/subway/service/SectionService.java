package subway.service;

import subway.domain.Line;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class SectionService {
    public static void registerSection(String lineName, String stationName, int order) {
        Line line = LineService.findLineByName(lineName);
        Validator.checkNumberInRange(order, 1, line.stationSize(), ErrorMessage.getMessage("올바르지 않은 순서입니다."));
        LineService.addStation(lineName, stationName, order - 1);
    }

    public static void deleteSection(String lineName, String stationName) {
        LineService.deleteStation(lineName, stationName);
    }
}