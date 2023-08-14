package com.github.wildtooth.easynotes.commands.note;

import com.github.wildtooth.easynotes.EasyNotes;
import com.github.wildtooth.easynotes.exceptions.InvalidNoteException;
import com.github.wildtooth.easynotes.managers.NoteManager;
import com.github.wildtooth.easynotes.note.Note;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class NoteViewCommand extends SubCommand {

  private final String notePath;
  private final String errorPath;
  private NoteManager noteManager;

  protected NoteViewCommand(NoteManager noteManager, @NotNull String subPrefix, @NotNull String... aliases) {
    this(subPrefix, aliases);
    this.noteManager = noteManager;
  }

  /**
   * Instantiates a new sub command.
   *
   * @param subPrefix the prefix of the sub command
   * @param aliases   the aliases
   */
  protected NoteViewCommand(@NotNull String subPrefix, @NotNull String... aliases) {
    super(subPrefix, aliases);
    this.notePath = EasyNotes.getLangPath() + "note.";
    this.errorPath = EasyNotes.getLangPath() + "error.";
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length == 1) {
      try {
        viewNote(Integer.parseInt(arguments[0]));
      } catch (NumberFormatException e) {
        displayMessage(I18n.translate(errorPath + "numberFormatException", arguments[0]));
      }
    } else {
      viewNotes();
    }
    return true;
  }

  private void viewNotes() {
    displayMessage(Component.text(
        I18n.translate(notePath + "displayTotal", noteManager.totalNotes()),
        NamedTextColor.GOLD));
    for (Map.Entry<Integer, Note> entry : noteManager.getNotes().entrySet()) {
      Note currentNote = entry.getValue();
      displayMessage(Component.text(
          I18n.translate(notePath + "displayMessage",
              currentNote.getId(), currentNote.getNoteAsString()), NamedTextColor.GREEN));
    }
  }

  private void viewNote(int id) {
    try {
      Note note = noteManager.getNote(id);
      displayMessage(Component.text(
          I18n.translate(notePath + "displayMessage",
              note.getId(), note.getNoteAsString()), NamedTextColor.GREEN));
    } catch (InvalidNoteException e) {
      displayMessage(I18n.translate(errorPath + "invalidNoteException",
          id));
      e.printStackTrace();
    }
  }
}
