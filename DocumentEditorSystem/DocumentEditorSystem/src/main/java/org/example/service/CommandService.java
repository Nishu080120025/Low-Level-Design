package org.example.service;

import org.example.command.EditCommand;

public interface CommandService {

    void executeCommand(EditCommand command);
    void undoCommand();
    void redoCommand();
}
