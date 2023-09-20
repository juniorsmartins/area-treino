package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.UtilInputPort;

public class UtilUseCase implements UtilInputPort {

    @Override
    public Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0D;
    }

    @Override
    public boolean isNumeric(String strNumber) {
        if (strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
