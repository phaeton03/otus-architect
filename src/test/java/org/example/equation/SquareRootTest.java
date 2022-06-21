package org.example.equation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SquareRootTest {
    private final SquareRoot squareRoot = new SquareRoot();

    private static final Double ZERO_A = 0.0;

    @Test
    void shouldThrowIllegalStateExceptionWhenAIsZero() {
        assertThatThrownBy(() -> squareRoot.solve(ZERO_A, 5.0, 6.0)).isInstanceOf(IllegalStateException.class);
    }
}