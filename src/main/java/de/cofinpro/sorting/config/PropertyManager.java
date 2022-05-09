package de.cofinpro.sorting.config;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PropertyManager class handling all application properties using java.util.Properties. On class initializing,
 * first defaults are set and then the min program calls processProperties with the argument list to set given
 * arguments. The class provides static methods for retrieving a property by key, resp. all properties.
 */
@Slf4j
public class PropertyManager {
    private static final Properties APP_CONFIG = new Properties();
    public static final String DATA_OPTION = "-dataType";
    public static final String SORT_OPTION = "-sortingType";
    public static final String INPUT_OPTION = "-inputFile";
    public static final String OUTPUT_OPTION = "-outputFile";
    private static final Set<String> OPTIONS =Set.of(DATA_OPTION, SORT_OPTION, INPUT_OPTION, OUTPUT_OPTION);
    private static final Set<String> DATA_VALUES = Set.of("word", "line", "long");
    private static final Set<String> SORT_VALUES = Set.of("natural", "byCount");

    private PropertyManager() {
        // no instances
    }

    static {
        setPropertyDefaults();
    }

    /**
     * get all property settings as the Properties instance (hash map).
     * @return the properties.
     */
    public static Properties getProperties() {
        return APP_CONFIG;
    }

    /**
     * get a property value by a given key
     * @param key a property given
     * @return the value to the key (null if not existing)
     */
    public static String getProperty(String key) {
        return APP_CONFIG.getProperty(key);
    }

    /**
     * called from main program with the program arguments. Does argument parsing and warnings.
     */
    public static void processProperties(List<String> argList) {
        for (int i = 0; i < argList.size(); i++) {
            String option = argList.get(i);
            if (!OPTIONS.contains(option)) {
                log.warn("\"%s\" is not a valid parameter. It will be skipped.".formatted(argList.get(i)));
                continue;
            }
            if (i == argList.size() - 1 ||
                    option.equals(DATA_OPTION) && !DATA_VALUES.contains(argList.get(i  + 1)) ||
                    option.equals(SORT_OPTION) && !SORT_VALUES.contains(argList.get(i + 1))) {
                printOptionNotGivenMessage(option);
            } else {
                APP_CONFIG.setProperty(option, argList.get(i + 1));
            }
            i++; // skip option's value we have just set
        }
    }

    private static void printOptionNotGivenMessage(String option) {
        Matcher matcher = Pattern.compile("-([a-z]+)([A-Z][a-z]+)").matcher(option);
        matcher.matches(); // it does match here
        log.warn("No %s %s defined!".formatted(matcher.group(1), matcher.group(2).toLowerCase()));
    }

    /**
     * setting all default property values
     */
    static void setPropertyDefaults() {
        APP_CONFIG.setProperty(DATA_OPTION, "word");
        APP_CONFIG.setProperty(SORT_OPTION, "natural");
        APP_CONFIG.setProperty(INPUT_OPTION, "");
        APP_CONFIG.setProperty(OUTPUT_OPTION, "");
    }
}
