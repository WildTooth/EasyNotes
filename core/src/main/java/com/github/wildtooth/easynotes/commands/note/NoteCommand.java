package com.github.wildtooth.easynotes.commands.note;

import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.client.chat.command.Command;
import net.labymod.api.util.I18n;

public class NoteCommand extends Command {
  private static String commandPath;

  public NoteCommand(NoteManager noteManager, String langPath) {
    super("note", "n", "write");
    commandPath = langPath + "command.";
    this.withSubCommand(new NoteViewCommand(noteManager, "view", "v"));
    this.withSubCommand(new NoteAddCommand(noteManager,"add", "a"));
    this.withSubCommand(new NoteRemoveCommand(noteManager, "remove", "r"));
    this.withSubCommand(new NoteHelpCommand("help", "h"));
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    displayMessage(I18n.translate(commandPath + "unknown"));
    return true;
  }

  public static String commandUsage(String command) {
    return I18n.translate(commandPath + command + ".usage");
  }
}
