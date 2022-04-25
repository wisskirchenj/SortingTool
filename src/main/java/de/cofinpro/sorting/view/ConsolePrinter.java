package de.cofinpro.sorting.view;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsolePrinter {

    public void printObject(Object object) {
        log.info(object.toString());
    }
}
