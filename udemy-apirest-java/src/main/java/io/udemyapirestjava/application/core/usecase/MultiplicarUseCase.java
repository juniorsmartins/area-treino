package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.MultiplicarInputPort;
import io.udemyapirestjava.application.ports.in.UtilInputPort;
import io.udemyapirestjava.config.exceptions.UnsupportedMathOperationException;

public class MultiplicarUseCase implements MultiplicarInputPort {

    private final UtilInputPort utilInputPort;

    public MultiplicarUseCase(UtilInputPort utilInputPort) {
        this.utilInputPort = utilInputPort;
    }

    @Override
    public Double calcular(String numberOne, String numberTwo) {

        if (!this.utilInputPort.isNumeric(numberOne) || !this.utilInputPort.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        return this.utilInputPort.convertToDouble(numberOne) * this.utilInputPort.convertToDouble(numberTwo);
    }
}
