package subway.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import subway.error.ErrorMessage;
import subway.error.Validator;

public class Line {
    private String name;
    private List<Station> stations = new ArrayList<>();

    public Line(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        Validator.checkStringLength(name, 2, Integer.MAX_VALUE,
                ErrorMessage.getMessage("노선 이름은 2글자 이상이어야 합니다."));
    }

    public void addStation(Station station) {
        checkDuplicatedStation(station);
        stations.add(station);
    }

    public void addStation(int index, Station station) {
        checkDuplicatedStation(station);
        stations.add(index, station);
    }

    private void checkDuplicatedStation(Station station) {
        if (stations.contains(station)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("노선에 중복된 역이 존재합니다."));
        }
    }

    public boolean deleteStation(String stationName) {
        return stations.removeIf(station -> Objects.equals(station.getName(), stationName));
    }

    public int stationSize() {
        return stations.size();
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }
}