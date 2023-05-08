package com.github.wildtooth.easynotes.managers;

import com.github.wildtooth.easynotes.note.Note;
import java.util.HashMap;
import java.util.Map;

public class NoteManager {

  private final Map<Integer, Note> notes = new HashMap<>();

  public NoteManager() {

  }

  public void addNote(Note note) {
    this.notes.put(note.getId(), note);
  }

  public void removeNote(int id) {
    this.notes.remove(id);
  }

  public Note getNote(int id) {
    return this.notes.get(id);
  }

  public int totalNotes() {
    return this.notes.size();
  }
}
