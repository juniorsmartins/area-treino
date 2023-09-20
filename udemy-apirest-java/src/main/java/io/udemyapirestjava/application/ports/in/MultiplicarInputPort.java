package io.udemyapirestjava.application.ports.in;

import org.springframework.web.bind.annotation.PathVariable;

public interface MultiplicarInputPort {

    Double calcular(String numberOne, String numberTwo);
}

