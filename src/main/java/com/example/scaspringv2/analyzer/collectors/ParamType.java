package com.example.scaspringv2.analyzer.collectors;

import com.example.scaspringv2.analyzer.Config;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParamType {
    MAX_CLASS_LENGTH(1, "1. Максимальное количество строк в классе", Config.MAX_CLASS_LENGTH),
    MAX_BODY_LENGTH(2, "2. Максимальное количество строк в методе", Config.MAX_BODY_LENGTH),
    MAX_METHOD_NAME_LENGTH(3, "3. Максимальная длина имени метода", Config.MAX_METHOD_NAME_LENGTH),
    MAX_PARAM_COUNT(4, "4. Максимальное количество параметров в методе", Config.MAX_PARAM_COUNT),
    MAX_VARIABLE_LENGTH(5, "5. Максимальная длина наименования параметров метода, полей класса", Config.MAX_VARIABLE_LENGTH),
    MAX_VARIABLE_COUNT(6, "6. Максимальное количество полей в классе", Config.MAX_VARIABLE_COUNT),
    MAX_METHODS_COUNT(7, "7. Максимальное количество методов в классе", Config.MAX_METHODS_COUNT),
    MIN_VARIABLE_LENGTH(8, "8. Минимальная длина наименования полей в классе, параметров в методе", Config.MIN_VARIABLE_LENGTH),
    BOOLEAN_STARTS_WITH_IS_HAS(9, "9. Названия логических методов начинаются с приставки is, has", Config.BOOLEAN_STARTS_WITH_IS_HAS ? "Да" : "Нет"),
    NAMES_IN_CAMEL_CASE(10, "10. Наименования классов, методов, параметров, полей указано в CamelCase стиле, коснтанты в UPPER_CASE", "Да"),
    MAX_SYMBOLS_COUNT_PER_LINE(11, "11. Максимальное количество символов в строке кода", Config.MAX_SYMBOLS_COUNT_PER_LINE),
    MAX_NUMBER_OF_ACTIONS_PER_LINE(12, "12. Максимальное количество действий в одной строке", Config.MAX_NUMBER_OF_ACTIONS_PER_LINE),
    MAX_EMPTY_LINES_COUNT_PER_METHOD(13, "13. Максимальное количество пустых строк для акцента внимания внутри классов", Config.MAX_EMPTY_LINES_COUNT_PER_METHOD);

    private final Integer index;
    private final String name;
    private final Object thresholdValue;
}

