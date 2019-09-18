package cs3500.hw01.publication;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the book class constructor.
 */

public class ConferenceProceedingsTest {
  Publication lerner = new ConferenceProceedings(
      "Lerner", "Benjamin",
      "The 3rd Summit on Advances In Programming Languages",
      "Providence, RI", 2019, "LIPIcs");

  @Test
  public void testCiteMla() {
    assertEquals("Lerner, Benjamin. " +
        "'The 3rd Summit on Advances In Programming Languages.'2019, "
        + "Providence, RI, LIPIcs." ,lerner.citeMla());
  }

  @Test
  public void testCiteApa() {
    assertEquals( "Lerner, Benjamin. (2019). "
        + "The 3rd Summit on Advances In Programming Languages. "
        + "Providence, RI: LIPIcs." ,lerner.citeApa());
  }
}


