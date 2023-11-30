package subway.config;

import java.util.List;
import subway.service.LineService;
import subway.service.SectionService;
import subway.service.StationService;

public class SubwayInitializer {
    private static final List<String> stationNames = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");

    public static void initStations() {
        for (String stationName : stationNames) {
            StationService.registerStation(stationName);
        }
    }

    public static void initLines() {
        LineService.registerLine("2호선", "교대역", "역삼역");
        LineService.registerLine("3호선", "교대역", "매봉역");
        LineService.registerLine("신분당선", "강남역", "양재시민의숲역");
        initSections();
    }

    private static void initSections() {
        SectionService.registerSection("2호선", "강남역", 2);

        SectionService.registerSection("3호선", "남부터미널역", 2);
        SectionService.registerSection("3호선", "양재역", 3);

        SectionService.registerSection("신분당선", "양재역", 2);
    }
}