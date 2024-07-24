package ru.meeral.service;
import ru.meeral.entity.ReaderHelperResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatisticCollectorHelperService {
    @NonNull
    ReaderHelperResponse response;

    List<String> integerData;
    List<String> floatData;
    List<String> stringData;

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
        setIntegerData(response.getIntegerData());
        setFloatData(response.getFloatData());
        setStringData(response.getStringData());

        setIntCount(integerData.size());
        setFloatCount(floatData.size());
        setStringCount(stringData.size());

        setIntSum(integerData.stream().mapToLong(Long::parseLong).sum());
        setFloatSum(floatData.stream().mapToDouble(Double::parseDouble).sum());

        if (!stringData.isEmpty()) {
            setLongestString(Collections.max(stringData, Comparator.comparingInt(String::length)).length());
            setShortestString(Collections.min(stringData, Comparator.comparingInt(String::length)).length());
        }
        if (!integerData.isEmpty()) {
            minInteger = Collections.min(integerData.stream().map(Long::parseLong).toList());
            maxInteger = Collections.max(integerData.stream().map(Long::parseLong).toList());
        }
        if (!floatData.isEmpty()) {
            minFloat = Collections.min(floatData.stream().map(Double::parseDouble).toList());
            maxFloat = Collections.max(floatData.stream().map(Double::parseDouble).toList());
        }
    }

    public void DisplayShortStats (){
        System.out.println("Short statistics:");
        System.out.println("Integers count: " + intCount);
        System.out.println("Floats count: " + floatCount);
        System.out.println("Strings count: " + stringCount);
    }

    public void DisplayFullStats (){
        System.out.println("Full statistics:");
        System.out.println("Integers - Count: " + intCount + ", Min: " + minInteger + ", Max: " + maxInteger +
                ", Sum: " + intSum + ", Avg: " + intSum / intCount);
        System.out.println("Floats - Count: " + floatCount + ", Min: " + minFloat + ", Max: " + maxFloat +
                ", Sum: " + floatSum + ", Avg: " + floatSum / floatCount);
        System.out.println("Strings - Count: " + stringCount + ", Shortest: " + shortestString +
                ", Longest: " + longestString);
    }
}
