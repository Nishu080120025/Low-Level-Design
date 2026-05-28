package org.example.service;

import org.example.command.EditCommand;

public interface CommandService {

    void executeCommand(String documentId,EditCommand command);
    String  undoCommand(String documentId);
    String  redoCommand(String documentId);
}
