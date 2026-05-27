package org.example.service.impl;

import org.example.command.EditCommand;
import org.example.service.CommandService;

import java.util.List;
import java.util.Stack;

public class CommandServiceImpl implements CommandService {
    private Stack<EditCommand>undoStack;
    private Stack<EditCommand>redoStack;

    public CommandServiceImpl() {
        this.undoStack=new Stack<>();
        this.redoStack=new Stack<>();
    }

    @Override
    public void executeCommand(EditCommand command){
        // Whenever a new command is executed, we clear the redo stack because the history has changed and we can't redo commands that were undone before the new command was executed because they may no longer be valid in the new context.
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        System.out.println("command executed");
    }

    @Override
    public String undoCommand(){
        if(undoStack.isEmpty()){
            System.out.println("No commands to undo.");
            return null;
        }
        EditCommand command=undoStack.pop();
        command.undo();
        redoStack.push(command);
        return "command undone";
    }

    @Override
    public String redoCommand(){
        if(redoStack.isEmpty()){
            System.out.println("No commands to redo.");
            return null;
        }
        EditCommand command=redoStack.pop();
        command.execute();
        undoStack.push(command);
        return "command redone";
    }
}
