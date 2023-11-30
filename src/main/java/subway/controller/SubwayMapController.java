package subway.controller;

import java.util.List;
import subway.domain.Line;
import subway.service.LineService;
import view.SubwayMapView;

public class SubwayMapController implements Controller {
    @Override
    public void run() {
        List<Line> lines = LineService.getLines();
        SubwayMapView.printSubwayMap(lines);
    }
}