package io.udemyapirestjava.adapters.in.controllers;

import io.udemyapirestjava.application.ports.in.DividirInputPort;
import io.udemyapirestjava.application.ports.in.MultiplicarInputPort;
import io.udemyapirestjava.application.ports.in.SubtrairInputPort;
import io.udemyapirestjava.config.exceptions.UnsupportedMathOperationException;
import io.udemyapirestjava.application.ports.in.SomarInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @Autowired
    private SomarInputPort somarInputPort;

    @Autowired
    private SubtrairInputPort subtrairInputPort;

    @Autowired
    private MultiplicarInputPort multiplicarInputPort;

    @Autowired
    private DividirInputPort dividirInputPort;

    @RequestMapping(path = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(name = "numberOne") String numberOne,
                      @PathVariable(name = "numberTwo") String numberTwo) {

        return this.somarInputPort.calcular(numberOne, numberTwo);
    }

    @RequestMapping(path = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double subtraction(@PathVariable(name = "numberOne") String numberOne,
                              @PathVariable(name = "numberTwo") String numberTwo) {

        return this.subtrairInputPort.calcular(numberOne, numberTwo);
    }

    @RequestMapping(path = "/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double multiplication(@PathVariable(name = "numberOne") String numberOne,
                              @PathVariable(name = "numberTwo") String numberTwo) {

        return this.multiplicarInputPort.calcular(numberOne, numberTwo);
    }

    @RequestMapping(path = "/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double division(@PathVariable(name = "numberOne") String numberOne,
                           @PathVariable(name = "numberTwo") String numberTwo) {

        return this.dividirInputPort.calcular(numberOne, numberTwo);
    }
}

