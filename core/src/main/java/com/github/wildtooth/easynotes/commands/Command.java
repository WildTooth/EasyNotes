package com.github.wildtooth.easynotes.commands;

import com.github.wildtooth.easynotes.EasyNotes;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command {

  private final ArrayList<SubCommand> commands = new ArrayList<>();
  private final EasyNotes addon;

  public Command(EasyNotes addon) {
    this.addon = addon;
  }

  protected void addSubCommand(SubCommand command) {
    this.commands.add(command);
  }

  protected CommandResult execute(String prefix, String[] args) {
    if (args.length == 0) {
      return CommandResult.noSubCommandFound();
    }

    SubCommand subCommand = getSubCommandFromAliasOrNull(args[0]);
    if (subCommand == null) {
      return CommandResult.noSubCommandFound();
    }

    String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
    return subCommand.execute(prefix, newArgs);
  }

  protected SubCommand getSubCommandFromAliasOrNull(String alias) {
    for (SubCommand command : this.commands) {
      if (command.containsAlias(alias)) {
        return command;
      }
    }

    return null;
  }

  protected ArrayList<SubCommand> getSubCommands() {
    return this.commands;
  }

  protected EasyNotes getAddon() {
    return this.addon;
  }
}

