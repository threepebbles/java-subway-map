package subway.service;

import java.util.List;
import subway.domain.Station;
import subway.error.ErrorMessage;
import subway.repository.StationRepository;

public class StationService {
    public static void registerStation(String name) {
        if (StationRepository.exists(name)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("이미 존재하는 역 이름 입니다."));
        }
        StationRepository.addStation(new Station(name));
    }

    public static void deleteStation(String name) {
        if (!StationRepository.deleteStation(name)) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("존재하지 않는 역이므로 삭제할 수 없습니다."));
        }
    }

    public static List<Station> getStations() {
        return StationRepository.stations();
    }

    public static Station findStationByName(String name) {
        Station station = StationRepository.findStationByName(name);
        if (station == null) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("존재하지 않는 역 이름입니다."));
        }
        return station;
    }
}