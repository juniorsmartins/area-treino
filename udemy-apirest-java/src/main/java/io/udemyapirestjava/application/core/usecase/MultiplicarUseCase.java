package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.MultiplicarInputPort;
import io.udemyapirestjava.application.ports.in.UtilInputPort;

public class MultiplicarUseCase implements MultiplicarInputPort {

    private final UtilInputPort utilInputPort;

    public MultiplicarUseCase(UtilInputPort utilInputPort) {
        this.utilInputPort = utilInputPort;
    }

    @Override
    public Double calcular(String numberOne, String numberTwo) {

        return this.utilInputPort.convertToDouble(numberOne) * this.utilInputPort.convertToDouble(numberTwo);
    }
}
