package com.github.wildtooth.easynotes;

import com.github.wildtooth.easynotes.commands.note.NoteCommand;
import com.github.wildtooth.easynotes.configuration.EasyNotesConfiguration;
import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import net.labymod.api.util.I18n;

@AddonMain
public class EasyNotes extends LabyAddon<EasyNotesConfiguration> {
  private static final String langPath = "easynotes.messages.";

  @Override
  protected void enable() {
    this.registerSettingCategory();

    final NoteManager noteManager = new NoteManager();

    this.registerCommand(new NoteCommand(noteManager));

    this.logger().info(I18n.translate(langPath + "enabled"));
  }

  @Override
  protected Class<EasyNotesConfiguration> configurationClass() {
    return EasyNotesConfiguration.class;
  }

  public static String getLangPath() {
    return langPath;
  }
}
