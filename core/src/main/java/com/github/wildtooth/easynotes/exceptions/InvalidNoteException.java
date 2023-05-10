package com.github.wildtooth.easynotes.exceptions;

public class InvalidNoteException extends IllegalArgumentException {
  public InvalidNoteException () {
    super();
  }

  /**
   * Constructs a {@link InvalidNoteException} with the
   * specified detail message.
   *
   * @param   s   the detail message.
   */
  public InvalidNoteException (String s) {
    super (s);
  }
}
