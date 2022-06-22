package org.example.equation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static java.lang.Math.*;

@Component
public class SquareRoot implements Math {
    @Value("${math.precision.eps}")
    private Double eps;

    @Override
    public Double[] solve(Double a, Double b, Double c) {
        if (abs(a) < eps) {
            throw new IllegalArgumentException();

        }
        return new Double[0];
    }
}
