package io.udemyapirestjava.application.ports.in;

import org.springframework.web.bind.annotation.PathVariable;

public interface SubtrairInputPort {

    Double calcular(String numberOne, String numberTwo);
}

