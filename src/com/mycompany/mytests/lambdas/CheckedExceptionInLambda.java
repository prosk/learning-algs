package com.mycompany.mytests.lambdas;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckedExceptionInLambda {

    // compile error
    // java: unreported exception java.io.UnsupportedEncodingException; must be caught or declared to be thrown
    /*public List<String> encodeValues(String... values) throws UnsupportedEncodingException {
        return Arrays.stream(values)
                .map(s -> URLEncoder.encode(s, "UTF-8"))
                .collect(Collectors.toList());
    }*/

    private String encodeString(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<String> encodeValuesUsingMethod(String... values) {
        return Arrays.stream(values)
                .map(this::encodeString)
                .collect(Collectors.toList());
    }
}
