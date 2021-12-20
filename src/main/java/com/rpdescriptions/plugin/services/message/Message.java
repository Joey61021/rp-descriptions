package com.rpdescriptions.plugin.services.message;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message {

    GENERAL_PLAYER_CLICK_MESSAGES("General.Player-Click.Messages"),

    GENERAL_NO_CONSOLE("General.Messages.No-Console"),
    GENERAL_NO_PERMISSION("General.Messages.No-Permission"),
    GENERAL_ENTER_PLAYER("General.Messages.Enter-Player"),
    GENERAL_NO_PLAYER("General.Messages.No-Player"),

    CMD_VIEWDESC_MESSAGES_DESCRIPTION("Commands.ViewDesc.Messages.Description"),
    CMD_SETDESC_MESSAGES_DESCRIPTION_SET("Commands.SetDesc.Messages.Description-Set"),
    CMD_SETDESC_MESSAGES_DESCRIPTION_RESET("Commands.SetDesc.Messages.Description-Reset"),
    CMD_SETDESC_MESSAGES_ENTER_DESCRIPTION("Commands.SetDesc.Messages.Enter-Description"),
    CMD_FORCEDESC_MESSAGES_DESCRIPTION_SET("Commands.ForceDesc.Messages.Description-Set"),
    CMD_FORCEDESC_MESSAGES_DESCRIPTION_RESET("Commands.ForceDesc.Messages.Description-Reset"),
    CMD_FORCEDESC_MESSAGES_NO_DESCRIPTION("Commands.ForceDesc.Messages.No-Description");

    @Getter
    @NonNull
    private final String path;
}
