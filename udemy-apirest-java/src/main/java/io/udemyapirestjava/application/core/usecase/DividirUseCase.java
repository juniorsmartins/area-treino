package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.DividirInputPort;
import io.udemyapirestjava.application.ports.in.UtilInputPort;
import io.udemyapirestjava.config.exceptions.UnsupportedMathOperationException;

public class DividirUseCase implements DividirInputPort {

    private final UtilInputPort utilInputPort;

    public DividirUseCase(UtilInputPort utilInputPort) {
        this.utilInputPort = utilInputPort;
    }

    @Override
    public Double calcular(String numberOne, String numberTwo) {

        if (!this.utilInputPort.isNumeric(numberOne) || !this.utilInputPort.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return this.utilInputPort.convertToDouble(numberOne) / this.utilInputPort.convertToDouble(numberTwo);
    }
}
