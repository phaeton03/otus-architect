package org.example.equation;

import org.springframework.beans.factory.annotation.Value;

public class SquareRoot implements Math {
    @Value("${math.precision.eps}")
    private Double eps;

    @Override
    public Double[] solve(Double a, Double b, Double c) {
        return new Double[0];
    }
}
