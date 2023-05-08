package com.github.wildtooth.easynotes.note;

public class Note {
  private final String note;
  private final long unix;
  private final int id;

  public Note(String note, int id) {
    this.note = note;
    this.unix = System.currentTimeMillis();
    this.id = id;
  }

  public String getNoteAsString() {
    return this.note;
  }

  public long getUnix() {
    return this.unix;
  }

  public int getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "Note{" +
        "note='" + this.note + '\'' +
        ", unix=" + this.unix +
        '}';
  }
}
