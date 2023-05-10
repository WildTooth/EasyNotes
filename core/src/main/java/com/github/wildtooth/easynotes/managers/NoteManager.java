package com.github.wildtooth.easynotes.managers;

import com.github.wildtooth.easynotes.exceptions.InvalidNoteException;
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

  public void removeNote(int id) throws InvalidNoteException {
    if (!this.notes.containsKey(id)) {
      throw new InvalidNoteException("Note with id " + id + " does not exist");
    }
    this.notes.remove(id);
  }

  public Note getNote(int id) throws InvalidNoteException {
    if (!this.notes.containsKey(id)) {
      throw new InvalidNoteException("Note with id " + id + " does not exist");
    }
    return this.notes.get(id);
  }

  public int totalNotes() {
    return this.notes.size();
  }

  public Map<Integer, Note> getNotes() {
    return this.notes;
  }
}
