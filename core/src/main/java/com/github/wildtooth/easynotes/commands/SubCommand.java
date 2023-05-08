package com.github.wildtooth.easynotes.commands;

import com.github.wildtooth.easynotes.EasyNotes;

public abstract class SubCommand extends Command {


  private final String usage;

  private final String description;

  private final String[] aliases;

  public SubCommand(EasyNotes addon, String description, String usage, String... aliases) {
    super(addon);

    this.description = description;
    this.usage = usage;
    this.aliases = aliases;
  }

  protected boolean containsAlias(String alias) {
    for (String s : this.aliases) {
      if (s.equalsIgnoreCase(alias)) {
        return true;
      }
    }

    return false;
  }

  public abstract CommandResult execute(String prefix, String[] args);

  public String getUsage(String label) {
    return "/" + label + " " + this.usage;
  }

  public String getDescription() {
    return this.description;
  }

  public String[] getAliases() {
    return this.aliases;
  }
}
