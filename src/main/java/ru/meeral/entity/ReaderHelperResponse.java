package ru.meeral.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReaderHelperResponse {
    List<String> integerData;
    List<String> floatData;
    List<String> stringData;
}
