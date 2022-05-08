package de.cofinpro.sorting.domain;

/**
 * simple interface for a generic count entry (key plus frequency of occurrence) as used in the
 * Console Printer.
 */
public interface CountEntry {

    Object getKey();

    Long getCount();
}
