package br.rafael.users.api.utils;

import java.util.List;

public abstract class Utils {
    
    public static boolean isNullOrEmpty(List<?> collection) {
        return collection.isEmpty() || collection == null;
    }

}
