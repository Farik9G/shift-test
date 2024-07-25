package ru.meeral.service;

import ru.meeral.entity.ReaderHelperResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticCollectorHelperService {
    @NonNull
    ReaderHelperResponse response;
    static final Logger log = Logger.getLogger(StatisticCollectorHelperService.class.getName());

    int intCount;
    int floatCount;
    int stringCount;

    double intSum;
    double floatSum;

    int longestString;
    int shortestString;
    long minInteger;
    long maxInteger;
    double minFloat;
    double maxFloat;

    public void initializeStatisticsCollector() {

        intCount = response.getIntegerData().size();
        floatCount = response.getFloatData().size();
        stringCount = response.getStringData().size();

        intSum = response.getIntegerData().stream().mapToLong(Long::parseLong).sum();
        floatSum = response.getFloatData().stream().mapToDouble(Double::parseDouble).sum();

        if (!response.getStringData().isEmpty()) {
            longestString = Collections.max(response.getStringData(), Comparator.comparingInt(String::length)).length();
            shortestString = Collections.min(response.getStringData(), Comparator.comparingInt(String::length)).length();
        }
        if (!response.getIntegerData().isEmpty()) {
            minInteger = Collections.min(response.getIntegerData().stream().map(Long::parseLong).toList());
            maxInteger = Collections.max(response.getIntegerData().stream().map(Long::parseLong).toList());
        }
        if (!response.getFloatData().isEmpty()) {
            minFloat = Collections.min(response.getFloatData().stream().map(Double::parseDouble).toList());
            maxFloat = Collections.max(response.getFloatData().stream().map(Double::parseDouble).toList());
        }
    }

    public void displayShortStats() {
        log.info("Short statistics:");
        log.info(String.format("Integers count: %d", intCount));
        log.info(String.format("Floats count: %d", floatCount));
        log.info(String.format("Strings count: %d", stringCount));
    }

    public void displayFullStats() {
        log.info("Full statistics:");
        log.info(String.format("Integers - Count: %d, Min: %d, Max: %d, Sum: %f, Avg: %f",
                intCount, minInteger, maxInteger, intSum, intSum / intCount));
        log.info(String.format("Floats - Count: %d, Min: %f, Max: %f, Sum: %f, Avg: %f",
                floatCount, minFloat, maxFloat, floatSum, floatSum / floatCount));
        log.info(String.format("Strings - Count: %d, Shortest: %d, Longest: %d",
                stringCount, shortestString, longestString));
    }
}
