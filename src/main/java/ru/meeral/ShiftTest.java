package ru.meeral;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import ru.meeral.entity.ReaderHelperResponse;
import ru.meeral.service.FileReaderHelperService;
import ru.meeral.service.FileWriterHelperService;
import ru.meeral.service.StatisticCollectorHelperService;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShiftTest {

    static FileReaderHelperService fileReaderHelperService = new FileReaderHelperService();
    public static void main(String[] args) {

            ReaderHelperResponse response = fileReaderHelperService.read(args);

            StatisticCollectorHelperService statisticResponse = new StatisticCollectorHelperService(response);
            statisticResponse.initializeStatisticsCollector();

            if (fileReaderHelperService.isShortStats()) {
                statisticResponse.DisplayShortStats();
            }

            else if (fileReaderHelperService.isFullStats()) {
                statisticResponse.DisplayFullStats();
            }

            FileWriterHelperService fileWriter = new FileWriterHelperService(response, fileReaderHelperService.getOutputDir(),
                    fileReaderHelperService.getPrefix(), fileReaderHelperService.isAppend());
            fileWriter.WriteDataToFiles();
    }
}