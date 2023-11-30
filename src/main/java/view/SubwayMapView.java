package view;

import java.util.List;
import subway.domain.Line;
import subway.domain.Station;

public class SubwayMapView {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static void printSubwayMap(List<Line> lines) {
        System.out.println("## 지하철 노선도");
        for (Line line : lines) {
            printLine(line);
            System.out.print(LINE_SEPARATOR);
        }
    }

    private static void printLine(Line line) {
        printInfo(line.getName());
        printInfo("---");
        for (Station station : line.getStations()) {
            printInfo(station.getName());
        }
    }

    private static void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}