package com.lahiru.ims.utils;

import com.lahiru.ims.common.enums.DataSeederEnum;

import java.util.Arrays;
import java.util.List;

public class EnumUtils {
    public static <E extends Enum<E> & DataSeederEnum> List<String> extractEnum(Class<E> enumClass) {
        if (enumClass.isEnum()) return Arrays.stream(enumClass.getEnumConstants()).map(E::getDisplayName).toList();
        else throw new IllegalArgumentException("Provided class is not a enum class");
    }
}
