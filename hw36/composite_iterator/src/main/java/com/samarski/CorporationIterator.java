package com.samarski;

public interface CorporationIterator {
    boolean hasNext();
    CorporationComponent getNext();
    void reset();
}
