package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.config.exceptions.UnsupportedMathOperationException;
import io.udemyapirestjava.application.ports.in.SomarInputPort;
import io.udemyapirestjava.application.ports.in.UtilInputPort;

public class SomarUseCase implements SomarInputPort {

    private final UtilInputPort utilInputPort;

    public SomarUseCase(UtilInputPort utilInputPort) {
        this.utilInputPort = utilInputPort;
    }

    @Override
    public Double calcular(String numberOne, String numberTwo) {

        if (!this.utilInputPort.isNumeric(numberOne) || !this.utilInputPort.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return this.utilInputPort.convertToDouble(numberOne) + this.utilInputPort.convertToDouble(numberTwo);
    }
}
