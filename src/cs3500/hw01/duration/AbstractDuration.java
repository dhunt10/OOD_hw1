package cs3500.hw01.duration;

/**
 * Abstract base class for implementations of {@link Duration}.
 */
abstract class AbstractDuration implements Duration {
  /**
   * Constructs a {@link Duration} in a manner selected by concrete subclasses
   * of this class.
   *
   * @param inHours the length in hours
   * @return the new {@code Duration}
   */
  protected abstract Duration fromHours(long inHours);

  @Override
  public String toString() {
    return asWdh();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }

    if (!(that instanceof Duration)) {
      return false;
    }

    return ((Duration) that).inHours() == this.inHours();
  }

  @Override
  public int hashCode() {
    return Long.hashCode(inHours());
  }

  @Override
  public int compareTo(Duration that) {
    return Long.compare(this.inHours(), that.inHours());
  }

  @Override
  public Duration plus(Duration that) {
    return fromHours(this.inHours() + that.inHours());
  }

  /**
   * Converts an unpacked weeks-days-hours duration to its length in
   * hours.
   *
   * @param weeks the number of weeks
   * @param days  the number of days
   * @param hours the number of hours
   * @return the duration in hours
   */
  protected static long inHours(int weeks, int days, int hours) {
    return (24 * 7) * weeks + 24 * days + hours;
  }

  /**
   * Formats an unpacked weeks-days-hours duration in the same {@code
   * W:D:HH} format that {@link Duration#asWdh()} returns.
   *
   * @param weeks the number of weeks
   * @param days  the number of days
   * @param hours the number of hours
   * @return formatted duration
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static String asWdh(int weeks, int days, int hours) {
    return String.format("%d:%d:%02d", weeks, days, hours);
  }

  /**
   * Ensures that the weeks, days and hours are all non-negative. Is
   * factoring this out overkill? Or should we also factor out the {@code
   * inHours < 0} check in the two unary constructors? Discuss.
   *
   * @param weeks the number of weeks
   * @param days  the number of days
   * @param hours the number of hours
   * @throws IllegalArgumentException if any argument is negative
   */
  protected static void ensureWdh(int weeks, int days, int hours) {
    if (weeks < 0 || days < 0 || hours < 0) {
      throw new IllegalArgumentException("must be non-negative");
    }
  }

  /**
   * Returns the number of whole weeks in the given number of hours.
   *
   * @param inHours the total number of hours
   * @return the number of weeks
   * @throws ArithmeticException if the correct result cannot fit in an {@code
   *                             int}.
   */
  protected static int weeksOf(long inHours) {
    if (inHours / (24 * 7) > Integer.MAX_VALUE) {
      throw new ArithmeticException("result cannot fit in type");
    }

    return (int) (inHours / (24 * 7));
  }

  /**
   * Returns the number of whole hours in the given number of hours, less
   * the number of whole weeks.
   *
   * @param inHours the total number of hours
   * @return the number of remaining days
   */
  protected static int daysOf(long inHours) {
    return (int) ((inHours / 24) % 7);
  }

  /**
   * Returns the number of hours remaining after all full days are removed
   * from the given number of hours.
   *
   * @param inHours the total number of hours
   * @return the number of remaining hours
   */
  protected static int hoursOf(long inHours) {
    return (int) (inHours % 24);
  }

  /**
   * Iterates through duration template and checks for '%' code. When % is detected
   * it called the format decision method. When the formatted value is returned it replaces
   * the code in the original string.
   *
   * @param template to be formatted
   * @return formatted duration (String)
   */

  @Override
  public String format(String template) {
    long inHours = this.inHours();
    String newTemplate = "";

    for (int i = 0; i < template.length(); i++) {
      if (template.charAt(i) == '%') {
        if (i == template.length() - 1) {
          throw new IllegalArgumentException("end of file");
        }
        String newData = formatDecision(template.charAt(i + 1), inHours);
        newTemplate = newTemplate + newData;
        i++;
      } else {
        newTemplate = newTemplate + template.charAt(i);
      }
    }
    return newTemplate;
  }

  /**
   * Uses a switch statement to process the code passed in
   * and returns the formatted version of it in a string
   * if code is not valid then it throws and error.
   *
   * @param formatter code to be processed
   * @param inHours the entire duration in hours
   * @return formatted duration string
   */

  public String formatDecision(char formatter, long inHours) {
    String value;
    switch (formatter) {
      case 't': //the whole duration in hours
        return String.format("%d", inHours);

      case 'w': //the weeks component of the decomposed * duration
        return String.format("%d", weeksOf(inHours));

      case 'W': //the weeks component of the decomposed * duration,
        // padded to 2 digits with leading zeros (e.g., * {@code 05} or {@code 11})
        return String.format("%02d", weeksOf(inHours));

      case 'd': //the days component of the decomposed * duration
        return String.format("%d", daysOf(inHours));

      case 'D': //the days component of the decomposed * duration,
        // padded to 2 digits with leading zeros (e.g., * {@code 05} or {@code 56})
        return String.format("%02d", daysOf(inHours));

      case 'h': //the hours component of the decomposed * duration
        return String.format("%d", ((int) (inHours % 24)));

      case 'H': //the hours component of the decomposed * duration,
        // padded to 2 digits with leading zeros (e.g., * {@code 05} or {@code 56})
        return String.format("%02d", ((int) (inHours % 24)));

      case '%':
        return "%";

      default:
        //if number is wrong
        throw new IllegalArgumentException("invalid code");
    }

  }

}