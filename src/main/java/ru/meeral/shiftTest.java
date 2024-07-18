package ru.meeral;

import lombok.Getter;
import org.apache.commons.cli.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class shiftTest{
    public enum OptionsValue {
        OUTPUT_PATH("o", true, "Output directory path"),
        OUTPUT_PREFIX("p", true, "Prefix for output files"),
        SHORT_STATISTICS("s", false, "View short statistics"),
        FULL_STATISTICS("f", false, "View full statistics"),
        APPEND("a", false, "Append to existing files");



        @Getter
        private final String name;
        private final boolean hasArg;
        private final String description;

        OptionsValue(String name, Boolean hasArg, String description) {
            this.name = name;
            this.hasArg = hasArg;
            this.description = description;
        }

        public Option createOption() {
            return new Option(name, hasArg, description);
        }


    }
    public static void main(String[] args) {
        Options options = new Options();

        for (OptionsValue value : OptionsValue.values()) {
            options.addOption(value.createOption());
        }

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);

            String outputDir = cmd.getOptionValue("o", "./");
            String prefix = cmd.getOptionValue("p", "");
            boolean append = cmd.hasOption("a");

            Set<String> integerData = new HashSet<>();
            Set<String> floatData = new HashSet<>();
            Set<String> stringData = new HashSet<>();

            long minInteger = Long.MAX_VALUE;
            long maxInteger = Long.MIN_VALUE;
            double minFloat = Double.MAX_VALUE;
            double maxFloat = Double.MIN_VALUE;

            for (String filePath : cmd.getArgs()) {
                File file = new File(filePath);
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        try {
                            if (line.matches("-?\\d+")) {
                                long num = Long.parseLong(line);
                                integerData.add(line);
                                minInteger = Math.min(minInteger, num);
                                maxInteger = Math.max(maxInteger, num);
                            } else {
                                try {
                                    double num = Double.parseDouble(line);
                                    floatData.add(line);
                                    minFloat = Math.min(minFloat, num);
                                    maxFloat = Math.max(maxFloat, num);
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

            try (PrintWriter intWriter = new PrintWriter(new FileWriter(outputDir + prefix + "integers.txt", append));
                 PrintWriter floatWriter = new PrintWriter(new FileWriter(outputDir + prefix + "floats.txt", append));
                 PrintWriter stringWriter = new PrintWriter(new FileWriter(outputDir + prefix + "strings.txt", append))) {

                for (String data : integerData) {
                    intWriter.println(data);
                }

                for (String data : floatData) {
                    floatWriter.println(data);
                }

                for (String data : stringData) {
                    stringWriter.println(data);
                }

                System.out.println("Data filtered and written to output files successfully.");

                int intCount = integerData.size();
                double intSum = integerData.stream().mapToLong(Long::parseLong).sum();
                int floatCount = floatData.size();
                double floatSum = floatData.stream().mapToDouble(Double::parseDouble).sum();
                int stringCount = stringData.size();
                int shortestString = stringData.stream().mapToInt(String::length).min().orElse(0);
                int longestString = stringData.stream().mapToInt(String::length).max().orElse(0);

                for (String data : stringData) {
                    int shortest = data.length();
                    int longest = data.length();
                    if (shortest < shortestString) {
                        shortestString = shortest;
                    }
                    if (longest > longestString) {
                        longestString = longest;
                    }
                }

                boolean shortStats = cmd.hasOption("s");
                boolean fullStats = cmd.hasOption("f");

                if (shortStats) {
                    System.out.println("Short statistics:");
                    System.out.println("Integers count: " + intCount);
                    System.out.println("Floats count: " + floatCount);
                    System.out.println("Strings count: " + stringCount);
                }

                if (fullStats) {
                    System.out.println("Full statistics:");
                    System.out.println("Integers - Count: " + intCount + ", Min: " + minInteger + ", Max: " + maxInteger +
                            ", Sum: " + intSum + ", Avg: " + intSum / intCount);
                    System.out.println("Floats - Count: " + floatCount + ", Min: " + minFloat + ", Max: " + maxFloat +
                            ", Sum: " + floatSum + ", Avg: " + floatSum / floatCount);
                    System.out.println("Strings - Count: " + stringCount + ", Shortest: " + shortestString +
                            ", Longest: " + longestString);
                }

            } catch (IOException e) {
                System.err.println("Error writing results to files: " + e.getMessage());
            }

        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
        }
    }
}