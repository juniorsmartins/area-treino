package io.udemyapirestjava.application.ports.in;

public interface UtilInputPort {

    Double convertToDouble(String strNumber);

    boolean isNumeric(String strNumber);
}

