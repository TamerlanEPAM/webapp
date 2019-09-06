package kz.zhabassov.webapp.util;

import static kz.zhabassov.webapp.util.NullUtil.isNull;

public class StringUtil {

    public StringUtil() {
    }

    public static boolean isNullOrEmpty(String string) {
        return (isNull(string)) || (string.trim().isEmpty());
    }

}
