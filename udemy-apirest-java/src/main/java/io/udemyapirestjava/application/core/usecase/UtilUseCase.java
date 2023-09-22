package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.UtilInputPort;
import io.udemyapirestjava.config.exception.UnsupportedMathOperationException;

public class UtilUseCase implements UtilInputPort {

    @Override
    public Double convertToDouble(String strNumber) {
        this.isNumeric(strNumber);

        String number = strNumber.replaceAll(",", ".");
        return Double.parseDouble(number);
    }

    private void isNumeric(String strNumber) {

        if (strNumber == null) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        String number = strNumber.replaceAll(",", ".");

        if (!number.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }
    }
}
