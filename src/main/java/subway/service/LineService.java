package subway.service;

import java.util.List;
import subway.domain.Line;
import subway.error.ErrorMessage;
import subway.repository.LineRepository;

public class LineService {
    public static void registerLine(String lineName, String firstStationName, String lastStationName) {
        checkExistLine(lineName);
        checkSameStation(firstStationName, lastStationName);
        Line line = new Line(lineName);
        line.addStation(StationService.findStationByName(firstStationName));
        line.addStation(StationService.findStationByName(lastStationName));
        LineRepository.addLine(line);
    }

    private static void checkSameStation(String firstStationName, String lastStationName) {
        if (firstStationName.equals(lastStationName)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("상행 종점역과 하행 종점역이 같습니다."));
        }
    }

    private static void checkExistLine(String lineName) {
        if (LineRepository.exists(lineName)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("이미 존재하는 노선 이름입니다."));
        }
    }

    public static void deleteLineByName(String lineName) {
        if (!LineRepository.deleteLineByName(lineName)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("존재하지 않는 노선 이름입니다."));
        }
    }

    public static List<Line> getLines() {
        return LineRepository.lines();
    }

    public static Line findLineByName(String name) {
        Line line = LineRepository.findLineByName(name);
        if (line == null) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("존재하지 않는 노선 이름입니다."));
        }
        return line;
    }

    public static void addStation(String lineName, String stationName, int index) {
        Line line = LineService.findLineByName(lineName);
        line.addStation(index, StationService.findStationByName(stationName));
    }

    public static void deleteStation(String lineName, String stationName) {
        Line line = LineService.findLineByName(lineName);
        if (!line.deleteStation(stationName)) {
            throw new IllegalArgumentException(
                    ErrorMessage.getMessage(lineName + "에는 " + stationName + "(이)가 존재하지 않습니다."));
        }
    }
}