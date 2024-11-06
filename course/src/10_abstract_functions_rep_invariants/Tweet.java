public class Tweet {

  private final String author;
  private final String text;
  private final Date timestamp;

  public Tweet(String author, String text, Date timestamp) {
    this.author = author;
    this.text = text;
    this.timestamp = timestam;
  }

  public String getAuthor() {
    return author;
  }

  public String getText() {
    return text;
  }

  public Date getTimestamp() {
    return timestamp;
  }

}
