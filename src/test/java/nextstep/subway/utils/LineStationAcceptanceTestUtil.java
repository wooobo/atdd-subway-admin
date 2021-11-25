package nextstep.subway.utils;


import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.subway.line.dto.LineResponse;
import org.springframework.http.HttpStatus;

public class LineStationAcceptanceTestUtil {

    private LineStationAcceptanceTestUtil() {
    }

    public static void 지하철_구간_요청_응답_검증(ExtractableResponse<Response> response,
        HttpStatus expected) {
        assertThat(response.statusCode()).isEqualTo(expected.value());
    }

    public static void 지하철_노선에_지하척역_순서_정렬됨(ExtractableResponse<Response> response,
        List<Long> expectedStationIds) {
        LineResponse line = response.as(LineResponse.class);
        List<Long> stationIds = line.getStations().stream()
            .map(it -> it.getStation().getId())
            .collect(Collectors.toList());

        assertThat(stationIds).containsExactlyElementsOf(expectedStationIds);
    }

}
