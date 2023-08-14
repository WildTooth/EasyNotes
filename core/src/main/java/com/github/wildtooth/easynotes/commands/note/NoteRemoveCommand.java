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

import static com.github.wildtooth.easynotes.commands.note.NoteCommand.commandUsage;

public class NoteRemoveCommand extends SubCommand {

  private final String commandPath;
  private final String errorPath;
  private NoteManager noteManager;

  protected NoteRemoveCommand(NoteManager noteManager, @NotNull String subPrefix, @NotNull String... aliases) {
    this(subPrefix, aliases);
    this.noteManager = noteManager;
  }

  /**
   * Instantiates a new sub command.
   *
   * @param subPrefix the prefix of the sub command
   * @param aliases   the aliases
   */
  protected NoteRemoveCommand(@NotNull String subPrefix, @NotNull String... aliases) {
    super(subPrefix, aliases);
    this.commandPath = EasyNotes.getLangPath() + "command.";
    this.errorPath = EasyNotes.getLangPath() + "error.";
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length == 1) {
      try {
        removeNote(
            Integer.parseInt(arguments[0]));
      } catch (NumberFormatException e) {
        displayMessage(I18n.translate(errorPath + "numberFormatException", arguments[0]));
        e.printStackTrace();
      }
    } else {
      displayMessage(Component.text(commandUsage(getPrefix()), NamedTextColor.GOLD));
    }
    return true;
  }

  private void removeNote(int id) {
    try {
      noteManager.removeNote(id);
      displayMessage(Component.text(I18n.translate(commandPath + "remove.success")));
    } catch (InvalidNoteException e) {
      displayMessage(I18n.translate(errorPath + "invalidNoteException",
          id));
      e.printStackTrace();
      return;
    }
    for (Map.Entry<Integer, Note> entry : noteManager.getNotes().entrySet()) {
      Note currentNote = entry.getValue();
      if (currentNote.getId() > id) {
        currentNote.setId(currentNote.getId() - 1);
      }
    }
  }
}
