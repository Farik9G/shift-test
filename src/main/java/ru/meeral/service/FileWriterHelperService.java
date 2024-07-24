package ru.meeral.service;

import ru.meeral.entity.ReaderHelperResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileWriterHelperService {

    ReaderHelperResponse response;

    String outputDir;
    String prefix;

    boolean append;

    public void WriteDataToFiles() {
        File outputDirectory = new File(outputDir);
        if (!outputDirectory.exists()) {
            if (outputDirectory.mkdirs()) {
                System.out.println("Directory created: " + outputDir);
            } else {
                System.err.println("Failed to create directory: " + outputDir);
                return;
            }
        }
        try {
            if (!response.getIntegerData().isEmpty()) {
                try (PrintWriter intWriter = new PrintWriter(new FileWriter(outputDir + prefix + "integers.txt", append))) {
                    for (String data : response.getIntegerData()) {
                        intWriter.println(data);
                    }
                }
            }

            if (!response.getFloatData().isEmpty()) {
                try (PrintWriter floatWriter = new PrintWriter(new FileWriter(outputDir + prefix + "floats.txt", append))) {
                    for (String data : response.getFloatData()) {
                        floatWriter.println(data);
                    }
                }
            }

            if (!response.getStringData().isEmpty()) {
                try (PrintWriter stringWriter = new PrintWriter(new FileWriter(outputDir + prefix + "strings.txt", append))) {
                    for (String data : response.getStringData()) {
                        stringWriter.println(data);
                    }
                }
            }

            System.out.println("Data filtered and written to output files successfully.");
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }

    }

}
