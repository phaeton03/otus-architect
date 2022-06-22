package org.example.equation;

import org.example.exception.InfinityFoundException;
import org.example.exception.NaNFoundException;
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
        if (a.isNaN() || b.isNaN() || c.isNaN()) {
            throw new NaNFoundException();
        }
        if (a.isInfinite() || b.isInfinite() || c.isInfinite()) {
            throw new InfinityFoundException();
        }

        return new Double[0];
    }
}
