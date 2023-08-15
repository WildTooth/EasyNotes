package com.github.wildtooth.easynotes.commands.note;

import com.github.wildtooth.easynotes.EasyNotes;
import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.util.I18n;

public class NoteCommand extends Command {
  public NoteCommand(NoteManager noteManager) {
    super("note", "n", "write");
    this.withSubCommand(new NoteViewCommand(noteManager, "view", "v"));
    this.withSubCommand(new NoteAddCommand(noteManager,"add", "a"));
    this.withSubCommand(new NoteRemoveCommand(noteManager, "remove", "r"));
    this.withSubCommand(new NoteHelpCommand("help", "h"));
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    displayMessage(I18n.translate(EasyNotes.getLangPath() + "command." + "unknown"));
    return true;
  }

  public static String commandUsage(String command) {
    return I18n.translate(EasyNotes.getLangPath() + "command." + command + ".usage");
  }
}
