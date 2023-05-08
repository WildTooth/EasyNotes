package com.github.wildtooth.easynotes;

import com.github.wildtooth.easynotes.commands.NoteCommand;
import com.github.wildtooth.easynotes.configuration.EasyNotesConfiguration;
import com.github.wildtooth.easynotes.listener.ExampleGameTickListener;
import com.github.wildtooth.easynotes.managers.NoteManager;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class EasyNotes extends LabyAddon<EasyNotesConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    final NoteManager noteManager = new NoteManager();

    this.registerListener(new ExampleGameTickListener(this));
    this.registerCommand(new NoteCommand(noteManager));

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<EasyNotesConfiguration> configurationClass() {
    return EasyNotesConfiguration.class;
  }
}
