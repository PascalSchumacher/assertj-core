/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.api.localtime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.error.ShouldBeBeforeOrEqualTo.shouldBeBeforeOrEqualTo;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class LocalTimeAssert_isBeforeOrEqualTo_Test extends LocalTimeAssertBaseTest {

  @Test
  public void test_isBeforeOrEqual_assertion() {
    // WHEN
    assertThat(BEFORE).isBeforeOrEqualTo(REFERENCE);
    assertThat(REFERENCE).isBeforeOrEqualTo(REFERENCE);
    // THEN
    verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(AFTER, REFERENCE);
  }

  @Test
  public void test_isBeforeOrEqual_assertion_error_message() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> assertThat(REFERENCE).isBeforeOrEqualTo(BEFORE))
                                                   .withMessage(shouldBeBeforeOrEqualTo(REFERENCE, BEFORE).create());
  }

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> {
      LocalTime actual = null;
      assertThat(actual).isBeforeOrEqualTo(LocalTime.now());
    }).withMessage(actualIsNull());
  }

  @Test
  public void should_fail_if_timeTime_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalTime.now()).isBeforeOrEqualTo((LocalTime) null))
                                        .withMessage("The LocalTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_timeTime_as_string_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertThat(LocalTime.now()).isBeforeOrEqualTo((String) null))
                                        .withMessage("The String representing the LocalTime to compare actual with should not be null");
  }

  private static void verify_that_isBeforeOrEqual_assertion_fails_and_throws_AssertionError(LocalTime timeToCheck,
                                                                                            LocalTime reference) {
    assertThatThrownBy(() -> assertThat(timeToCheck).isBeforeOrEqualTo(reference)).isInstanceOf(AssertionError.class);
    assertThatThrownBy(() -> assertThat(timeToCheck).isBeforeOrEqualTo(reference.toString())).isInstanceOf(AssertionError.class);
  }

}
