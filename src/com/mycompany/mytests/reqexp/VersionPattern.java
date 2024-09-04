package com.mycompany.mytests.reqexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionPattern {
    private static final Pattern FULL_VERSION_PATTERN = Pattern.compile("^AtonApp/((\\d+)\\.(\\d+)\\.(\\d+)).*$");

    private static final Pattern MIN_VERSION_PATTERN = Pattern.compile("^((\\d+)\\.(\\d+)\\.(\\d+))$");

    public static void main(String[] args) {
        String[] testValues = {"AtonApp/3.0.0_60 (iOS 18.0)", "AtonApp/3.0.1_40836 (Android 12)",
                "AtonApp/3.1.0_1 (iOS 17.2)", "AtonApp/4.0.0_1 (iOS 15.4)"};

        String androidMinVersion = "3.0.1";
        String iosMinVersion = "3.0.1";

        for(String testValue: testValues) {
            String platform = getPlatform(testValue);
            Boolean isSupportedVersion = isSupportedVersion(testValue, platform, androidMinVersion,
                    iosMinVersion);
            System.out.println("For testValue = " + testValue + " the result is " + isSupportedVersion);
        }
    }

    public static String getPlatform(String userAgentHeader) {
        if (userAgentHeader.contains("Android")) {
            return "Android";
        } else if (userAgentHeader.contains("iOS")) {
            return "iOS";
        } else {
            return "";
        }
    }

    public static Boolean isSupportedVersion(String userAgentHeader, String platform, String androidMinVersion,
                                             String iosMinVersion) {
        try {
            Matcher fullVersionMatcher = FULL_VERSION_PATTERN.matcher(userAgentHeader);
            if (fullVersionMatcher.find()) {
                String appVersion = fullVersionMatcher.group(1);
                int verPart1 = Integer.parseInt(fullVersionMatcher.group(2));
                int verPart2 = Integer.parseInt(fullVersionMatcher.group(3));
                int verPart3 = Integer.parseInt(fullVersionMatcher.group(4));

                String minVersion = "Android".equals(platform) ? androidMinVersion : iosMinVersion;

                //log.info("Try to compare appVersion={} with minVersion={}", appVersion, minVersion);

                Matcher minVersionMatcher = MIN_VERSION_PATTERN.matcher(minVersion);
                if (minVersionMatcher.find()) {
                    int minVerPart1 = Integer.parseInt(minVersionMatcher.group(2));
                    int minVerPart2 = Integer.parseInt(minVersionMatcher.group(3));
                    int minVerPart3 = Integer.parseInt(minVersionMatcher.group(4));

                    return (minVerPart1 < verPart1)
                            ||
                            (minVerPart1 == verPart1
                                    && minVerPart2 < verPart2)
                            ||
                            (minVerPart1 == verPart1
                                    && minVerPart2 == verPart2
                                    && minVerPart3 < verPart3)
                            ||
                            (minVerPart1 == verPart1
                                    && minVerPart2 == verPart2
                                    && minVerPart3 == verPart3);
                }
                System.out.println("Incorrect minVersion format. Can not parse minVersion value=" + minVersion);
            } else {
                System.out.println("Incorrect userAgentHeader format. Can not parse userAgentHeader value=" + userAgentHeader);
            }
        } catch (Exception e) {
            System.out.println("Can not check version" + e);
        }

        return null;
    }
}
