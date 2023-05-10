package com.github.wildtooth.easynotes.commands;

import com.github.wildtooth.easynotes.exceptions.InvalidNoteException;
import com.github.wildtooth.easynotes.note.Note;
import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class NoteCommand extends Command {
  private final NoteManager noteManager;
  private final String notePath;
  private final String commandPath;
  private final String errorPath;

  public NoteCommand(NoteManager noteManager, String langPath) {
    super("note", "n", "write");
    this.noteManager = noteManager;
    this.notePath = langPath + "note.";
    this.commandPath = langPath + "command.";
    this.errorPath = langPath + "error.";
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length == 0) {
      displayMessage(I18n.translate(commandPath + "unknown"));
      return true;
    }
    switch (arguments[0]) {
      case "view":
        if (arguments.length == 2) {
          try {
            viewNote(Integer.parseInt(arguments[1]));
          } catch (NumberFormatException e) {
            displayMessage(I18n.translate(errorPath + "numberFormatException", arguments[1]));
          }
        } else {
          viewNotes();
        }
        break;
      case "add":
        if (arguments.length < 2) {
          displayMessage(Component.text(commandUsage(arguments[0]), NamedTextColor.GOLD));
          break;
        }
        addNote(arguments);
        break;
      case "remove":
        if (arguments.length == 2) {
          try {
            removeNote(
                Integer.parseInt(arguments[1]));
          } catch (NumberFormatException e) {
            displayMessage(I18n.translate(errorPath + "numberFormatException", arguments[1]));
            e.printStackTrace();
          }
        } else {
          displayMessage(Component.text(commandUsage(arguments[0]), NamedTextColor.GOLD));
        }
        break;
      case "help":
        help();
        break;
      default:
        displayMessage(I18n.translate(commandPath + "unknown"));
        break;
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

  private void addNote(String @NotNull [] arguments) {
    String[] effectiveArgs = new String[arguments.length - 1];
    System.arraycopy(arguments, 1, effectiveArgs, 0, arguments.length - 1);
    final Note note = new Note(String.join(" ", effectiveArgs), noteManager.totalNotes() + 1);
    noteManager.addNote(note);
    displayMessage(Component.text(I18n.translate(commandPath + "add.success")));
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

  private void help() {
    displayMessage(Component.text(I18n.translate(commandPath + "help.title"), NamedTextColor.DARK_GREEN));

    displayHelp("view");
    displayHelp("add");
    displayHelp("remove");
    displayHelp("help");
  }

  private void displayHelp(String command) {
    TextComponent textComponent = Component.text(commandUsage(command), NamedTextColor.GOLD);
    textComponent.append(Component.text(" - ", NamedTextColor.GRAY));
    textComponent.append(Component.text(commandDescription(command), NamedTextColor.GREEN));
    displayMessage(textComponent);
  }

  private String commandUsage(String command) {
    return I18n.translate(commandPath + command + ".usage");
  }

  private String commandDescription(String command) {
    return I18n.translate(commandPath + "help.commands." + command);
  }
}
