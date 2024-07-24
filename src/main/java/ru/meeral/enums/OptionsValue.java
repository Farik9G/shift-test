package ru.meeral.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OptionsValue {

    OUTPUT_PATH("o", true, "Output directory path"),
    OUTPUT_PREFIX("p", true, "Prefix for output files"),
    SHORT_STATISTICS("s", false, "View short statistics"),
    FULL_STATISTICS("f", false, "View full statistics"),
    APPEND("a", false, "Append to existing files");

    private final String name;
    private final boolean hasArg;
    private final String description;
}