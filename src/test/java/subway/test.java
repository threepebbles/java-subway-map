package subway;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import subway.domain.Line;
import subway.domain.Station;

public class test {
    @Test
    void unmodifiableTest() {
        List<Line> lines = List.of(
                new Line("1호선"),
                new Line("2호선"),
                new Line("3호선")
        );
        List<Line> unmodifiedLines = Collections.unmodifiableList(lines);

        assertThatThrownBy(() -> {
//            unmodifiedLines.add(new Line("5호선"));
            unmodifiedLines.get(0).addStation(new Station("하하"));
        }).isInstanceOf(Exception.class);

    }

    @Test
    void addTest() {
        List<Integer> list = new ArrayList<>() {{
            IntStream.range(1, 10).forEach(this::add);
        }};
        list.add(4, -1);
        System.out.println(list);
        // 1, 2, 3, 4, -1, 5, 6, 7, 8, 9
    }
}
