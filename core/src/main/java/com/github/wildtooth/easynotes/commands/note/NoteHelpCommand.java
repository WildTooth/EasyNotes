package com.github.wildtooth.easynotes.commands.note;

import com.github.wildtooth.easynotes.EasyNotes;
import net.labymod.api.client.chat.command.SubCommand;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.util.I18n;
import org.jetbrains.annotations.NotNull;

import static com.github.wildtooth.easynotes.commands.note.NoteCommand.commandUsage;

public class NoteHelpCommand extends SubCommand {
  private final String commandPath;

  /**
   * Instantiates a new sub command.
   *
   * @param subPrefix the prefix of the sub command
   * @param aliases   the aliases
   */
  protected NoteHelpCommand(@NotNull String subPrefix, @NotNull String... aliases) {
    super(subPrefix, aliases);
    this.commandPath = EasyNotes.getLangPath() + "command.";
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    help();
    return true;
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

  public String commandDescription(String command) {
    return I18n.translate(commandPath + "help.commands." + command);
  }
}
