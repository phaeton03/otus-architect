package org.example.equation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(value = {MockitoExtension.class})
class SquareRootTest {
    SquareRoot squareRoot = new SquareRoot();

    private static final Double ZERO_A = 0.0;

    private final Double EPS = 0.0005;

    @BeforeEach
    void printEps() {
        ReflectionTestUtils.setField(squareRoot, "eps", EPS);
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenAIsZero() {

        assertThatThrownBy(() -> squareRoot.solve(ZERO_A, 5.0, 6.0)).isInstanceOf(IllegalArgumentException.class);
    }
}