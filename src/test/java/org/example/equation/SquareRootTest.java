package org.example.equation;

import org.example.exception.InfinityFoundException;
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

    private static final Double A_NEG_DISCRIMINANT = 5.0;

    private static final Double B_NEG_DISCRIMINANT = 7.0;

    private static final Double C_NEG_DISCRIMINANT = 10.0;

    private static final Double A_ZERO_DISCRIMINANT = 1.0;

    private static final Double B_ZERO_DISCRIMINANT = 2.0;

    private static final Double C_ZERO_DISCRIMINANT = 1.0;

    private static final Double SQUARE_ROOT_ZERO_DISCRIMINANT = -1.0;

    private static final Double A_POSITIVE_DISCRIMINANT = 1.0;

    private static final Double B_POSITIVE_DISCRIMINANT = 5.0;

    private static final Double C_POSITIVE_DISCRIMINANT = 6.0;

    private static final Double SQUARE_ROOT_ONE_POSITIVE_DISCRIMINANT = -2.0;

    private static final Double SQUARE_ROOT_TWO_ZERO_POSITIVE_DISCRIMINANT = -3.0;

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

    @ParameterizedTest
    @MethodSource("getInfinityArgs")
    void shouldFoundInfinityInParams(Double a, Double b, Double c) {
        assertThatThrownBy(() -> squareRoot.solve(a, b, c)).isInstanceOf(InfinityFoundException.class);
    }

    @Test
    void shouldSquareRootsBeEmptyIfDiscriminantIsNegative() {
        assertThat(squareRoot.solve(A_NEG_DISCRIMINANT, B_NEG_DISCRIMINANT, C_NEG_DISCRIMINANT)).isEmpty();
    }

    @Test
    void shouldSquareRootsBeTheSameIfDiscriminantIsZero() {
        assertThat(squareRoot.solve(A_ZERO_DISCRIMINANT, B_ZERO_DISCRIMINANT, C_ZERO_DISCRIMINANT))
                .hasSize(2).allMatch(arg -> arg.equals(SQUARE_ROOT_ZERO_DISCRIMINANT));
    }

    @Test
    void shouldSquareRootsBeDifferentIfDiscriminantIsPositive() {
        assertThat(squareRoot.solve(A_POSITIVE_DISCRIMINANT, B_POSITIVE_DISCRIMINANT, C_POSITIVE_DISCRIMINANT))
                .hasSize(2)
                .doesNotHaveDuplicates()
                .contains(SQUARE_ROOT_ONE_POSITIVE_DISCRIMINANT, SQUARE_ROOT_TWO_ZERO_POSITIVE_DISCRIMINANT);
    }

    static Stream<Arguments> getNaNArgs() {

        return Stream.of(
                arguments(A_NaN, B, C),
                arguments(A, B_NaN, C),
                arguments(A, B, C_NaN)
        );
    }

    static Stream<Arguments> getInfinityArgs() {

        return Stream.of(
                arguments(A_NEGATIVE_INFINITY, B, C),
                arguments(A, B_NEGATIVE_INFINITY, C),
                arguments(A, B, C_NEGATIVE_INFINITY),
                arguments(A_POSITIVE_INFINITY, B, C),
                arguments(A, B_POSITIVE_INFINITY, C),
                arguments(A, B, C_POSITIVE_INFINITY)
        );
    }
}