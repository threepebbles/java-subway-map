package subway.domain;

import subway.error.ErrorMessage;
import subway.error.Validator;

public class Station {
    private String name;

    public Station(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        Validator.checkStringLength(name, 2, Integer.MAX_VALUE,
                ErrorMessage.getMessage("역 이름은 2글자 이상이어야 합니다."));
    }

    public String getName() {
        return name;
    }

    // 추가 기능 구현
}
