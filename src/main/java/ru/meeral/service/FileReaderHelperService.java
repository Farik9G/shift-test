package ru.meeral.service;
import org.apache.commons.cli.*;
import ru.meeral.entity.ReaderHelperResponse;
import ru.meeral.enums.OptionsValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileReaderHelperService {
    String outputDir;
    String prefix;
    boolean append;
    boolean shortStats;
    boolean fullStats;
    public ReaderHelperResponse read(String[] cmdArgs) {
        List<String> integerData = new ArrayList<>();
        List<String> floatData = new ArrayList<>();
        List<String> stringData = new ArrayList<>();

        Options options = new Options();

        for (OptionsValue value : OptionsValue.values()) {
            options.addOption(new Option(value.getName(), value.isHasArg(), value.getDescription()));
        }

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, cmdArgs);

            outputDir = cmd.getOptionValue("o", "./");
            prefix = cmd.getOptionValue("p", "");
            append = cmd.hasOption("a");
            shortStats = cmd.hasOption("s");
            fullStats = cmd.hasOption("f");

            for (String filePath : cmd.getArgs()) {
                File file = new File(filePath);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            if (line.matches("-?\\d+")) {
                                long num = Long.parseLong(line);
                                integerData.add(line);
                            } else {
                                try {
                                    double num = Double.parseDouble(line);
                                    floatData.add(line);

                                } catch (NumberFormatException e) {
                                    stringData.add(line);
                                }
                            }
                        } catch (NumberFormatException e) {
                            stringData.add(line);
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + filePath + "\n" + e.getMessage());
                }
            }
        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
        }

        return new ReaderHelperResponse(integerData, floatData, stringData);
    }
}