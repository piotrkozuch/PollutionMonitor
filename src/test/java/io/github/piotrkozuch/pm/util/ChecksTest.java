package io.github.piotrkozuch.pm.util;

import org.junit.jupiter.api.Test;

import static io.github.piotrkozuch.pm.util.Checks.checkRequired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChecksTest {

    @Test
    void should_throw_exception_if_param_is_null() {
        // given
        var paramName = "foo";
        // expect
        assertThatThrownBy(() -> {
            checkRequired(paramName, null);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Param 'foo' can't be null!");
    }

    @Test
    void should_not_throw_exception_if_param_is_not_null() {
        // given
        var paramName = "foo";
        var object = "TestObject";

        // when
        var result = checkRequired(paramName, object);

        // then
        assertThat(result).isEqualTo(object);
    }
}