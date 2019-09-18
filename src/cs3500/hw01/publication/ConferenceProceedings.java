package cs3500.hw01.publication;

/**
 * represents data necessary for conference proceedings citation.
 */

public class ConferenceProceedings implements Publication {
  private String editorFirstName;
  private String editorLastName;
  private String title;
  private String location;
  private int year;
  private String publisher;

  /**
   * Constructs a Conference Proceeding.
   *
   * @param editorLastName   last name of editor of Conference Proceeding
   * @param editorFirstName  first name of editor of Conference Proceeding
   * @param title            title of Conference Proceeding
   * @param location         location of Conference Proceeding
   * @param year             year in which Conference Proceeding took place
   * @param publisher        publisher of Conference Proceeding
   */

  public ConferenceProceedings(String editorLastName, String editorFirstName,
                               String title, String location,
                               int year, String publisher) {

    this.editorFirstName = editorFirstName;
    this.editorLastName = editorLastName;
    this.title = title;
    this.location = location;
    this.year = (int) year;
    this.publisher = publisher;
  }

  @Override
  public String citeMla() {
    return editorLastName + ", " + editorFirstName + ", editor. " + title + ", "
        + year + ", " + location + ", " + publisher + ".";
  }

  @Override
  public String citeApa() {
    return editorLastName + ", " + editorFirstName.charAt(0) + ". (Ed.). (" + year + "). " + title
        + ". " + location + ": " + publisher + ".";
  }

}