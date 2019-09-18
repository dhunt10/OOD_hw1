package cs3500.hw01.duration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/** Tests for the format method of {@link Duration}s.
 Add your tests to this class to assure that your format
 method works properly
 */

public abstract class AbstractDurationFormatTest {
  @Test
  public void formatExample1() {
    assertEquals("4 days, 0 weeks, and 9 hours",
        wdh(0, 4, 9)
            .format("%d days, %w weeks, and %h hours"));
  }

  @Test
  public void formatExample2() {
    assertEquals("5:05:17",
        wdh(5, 5, 17).format("%w:%D:%H"));
  }

  @Test
  public void formatExample3() {
    assertEquals("5:5:5",
        wdh(5,5,5).format("%w:%d:%h"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample4() {
    hours(5).format("%T hours");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample5() {
    wdh(5, 5, 17).format("%w:%D:%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample6() {
    wdh(5, 5, 17).format("%w:%D:%R");
  }

  @Test
  public void formatExample7() {
    assertEquals("%w",
        wdh(5, 5, 17).format("%%w"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample8() {
    wdh(5, 5, 17).format("%%%%%%%");
  }

  @Test
  public void formatExample9() {
    assertEquals("%%%",
        wdh(5, 5, 17).format("%%%%%%"));
  }

  @Test
  public void formatExample11() {
    assertEquals("",
        wdh(5, 5, 17).format(""));
  }

  @Test
  public void formatExample12() {
    assertEquals("testnopercents",
        wdh(5, 5, 17).format("testnopercents"));
  }

  @Test
  public void formatExample13() {
    assertEquals("05:05:05",
        wdh(5,5,5).format("%W:%D:%H"));
  }

  @Test
  public void formatExample14() {
    assertEquals("5 hours",
        hours(5).format("%t hours"));
  }

  @Test
  public void formatExample20() {
    assertEquals(" ",
        hours(5).format(" "));
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample21() {
    hours(5).format("endingin%");
  }

  @Test(expected = IllegalArgumentException.class)
  public void formatExample22() {
    hours(5).format("%k");
  }

  @Test
  public void formatExample23() {
    assertEquals("nopercent",
        hours(5).format("nopercent"));
  }

  @Test
  public void formatExample24() {
    assertEquals("%w",
        hours(5).format("%%w"));
  }





  // ADD MORE TESTS HERE
  // Your tests must only use wdh(...) and hours(...) to construct new Durations
  // and must *not* directly say "new CompactDuration(...)" or
  // "new WdhDuration(...)"






  /*
    Leave this section alone: It contains two abstract methods to
    create Durations, and concrete implementations of this testing class
    will supply particular implementations of Duration to be used within
    your tests.
   */
  /**
   * Constructs an instance of the class under test representing the duration
   * given in weeks, days and hours.
   *
   * @param weeks the weeks in the duration
   * @param days the days in the duration
   * @param hours the hours in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration wdh(int weeks, int days, int hours);

  /**
   * Constructs an instance of the class under test representing the duration
   * given in hours.
   *
   * @param inHours the total hours in the duration
   * @return an instance of the class under test
   */
  protected abstract Duration hours(long inHours);

  /**
   * A nested testing factory class, that uses {@link WdhDuration} for
   * all of its test cases.
   */
  public static final class WdhDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration wdh(int weeks, int days, int hours) {
      return new WdhDuration(weeks, days, hours);
    }

    @Override
    protected Duration hours(long inHours) {
      return new WdhDuration(inHours);
    }
  }

  /**
   * A nested testing factory class, that uses {@link CompactDuration} for
   * all of its test cases.
   */
  public static final class CompactDurationTest extends AbstractDurationFormatTest {
    @Override
    protected Duration wdh(int weeks, int days, int hours) {
      return new CompactDuration(weeks, days, hours);
    }

    @Override
    protected Duration hours(long inHours) {
      return new CompactDuration(inHours);
    }
  }
}
