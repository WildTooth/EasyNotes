package com.github.wildtooth.easynotes.commands.note;

import com.github.wildtooth.easynotes.EasyNotes;
import com.github.wildtooth.easynotes.managers.NoteManager;
import com.github.wildtooth.easynotes.note.Note;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;
import org.jetbrains.annotations.NotNull;

import static com.github.wildtooth.easynotes.commands.note.NoteCommand.commandUsage;

public class NoteAddCommand extends SubCommand {

  private static String commandPath;
  private NoteManager noteManager;

  protected NoteAddCommand(NoteManager noteManager, @NotNull String subPrefix, @NotNull String... aliases) {
    this(subPrefix, aliases);
    this.noteManager = noteManager;
  }

  /**
   * Instantiates a new sub command.
   *
   * @param subPrefix the prefix of the sub command
   * @param aliases   the aliases
   */
  protected NoteAddCommand(@NotNull String subPrefix, @NotNull String... aliases) {
    super(subPrefix, aliases);
    commandPath = EasyNotes.getLangPath() + "command.";
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    if (arguments.length < 1) {
      displayMessage(Component.text(commandUsage(getPrefix()), NamedTextColor.GOLD));
      return true;
    }
    addNote(arguments);
    return true;
  }

  private void addNote(String @NotNull [] arguments) {
    final Note note = new Note(String.join(" ", arguments), noteManager.totalNotes() + 1);
    noteManager.addNote(note);
    displayMessage(Component.text(I18n.translate(commandPath + "add.success")));
  }
}
