package com.composite_iterator;

public interface CorporationIterator {
    boolean hasNext();
    CorporationComponent getNext();
    void reset();
}
