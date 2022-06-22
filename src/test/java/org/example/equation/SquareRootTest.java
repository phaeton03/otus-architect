package org.example.equation;

import org.example.exception.NaNFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

@ExtendWith(value = {MockitoExtension.class})
class SquareRootTest {
    SquareRoot squareRoot = new SquareRoot();

    private static final Double ZERO_A = 0.0;

    private static final Double A = 5.0;

    private static final Double B = 7.0;

    private static final Double C = 10.0;

    private static final Double A_NaN = Double.NaN;

    private static final Double B_NaN = Double.NaN;

    private static final Double C_NaN = Double.NaN;

    private static final Double C_NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;

    private static final Double B_NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;

    private static final Double A_NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;

    private static final Double A_POSITIVE_INFINITY = Double.POSITIVE_INFINITY;

    private static final Double B_POSITIVE_INFINITY = Double.POSITIVE_INFINITY;

    private static final Double C_POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    private final Double EPS = 0.0005;

    @BeforeEach
    void printEps() {
        ReflectionTestUtils.setField(squareRoot, "eps", EPS);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenAIsZero() {

        assertThatThrownBy(() -> squareRoot.solve(ZERO_A, 5.0, 6.0)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("getNaNArgs")
    void shouldFoundNaNInParams(Double a, Double b, Double c) {
        assertThatThrownBy(() -> squareRoot.solve(a, b, c)).isInstanceOf(NaNFoundException.class);
    }

    static Stream<Arguments> getNaNArgs() {

        return Stream.of(
                arguments(A_NaN, B, C),
                arguments(A, B_NaN, C),
                arguments(A, B, C_NaN)
        );
    }
}