package com.github.wildtooth.easynotes.commands;

import com.github.wildtooth.easynotes.note.Note;
import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.client.chat.command.Command;

public class NoteCommand extends Command {

  private final NoteManager noteManager;

  public NoteCommand(NoteManager noteManager) {
    super("note", "n", "write");
    this.noteManager = noteManager;
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length == 0) {
      return true;
    }
    final StringBuilder argConnector = new StringBuilder();
    for (String arg : arguments) {
      argConnector.append(arg);
      argConnector.append(' ');
    }
    final String connectedArguments = argConnector.toString();
    final Note note = new Note(connectedArguments, noteManager.totalNotes());
    noteManager.addNote(note);
    return true;
  }
}
