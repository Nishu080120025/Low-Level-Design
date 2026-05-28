package org.example.service.impl;

import org.example.command.EditCommand;
import org.example.service.CommandService;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class CommandServiceImpl implements CommandService {
    private HashMap<String,Stack<EditCommand>> undoStackMap;
    private HashMap<String,Stack<EditCommand>>redoStackMap;

    public CommandServiceImpl() {
        this.undoStackMap=new HashMap<>();
        this.redoStackMap=new HashMap<>();
    }

    @Override
    public void executeCommand(String documentId,EditCommand command){
        // Whenever a new command is executed, we clear the redo stack because the history has changed and we can't redo commands that were undone before the new command was executed because they may no longer be valid in the new context.
        command.execute();
        undoStackMap.putIfAbsent(documentId,new Stack<>());
        undoStackMap.get(documentId).push(command);
        redoStackMap.clear();
        System.out.println("command executed");
    }

    @Override
    public String undoCommand(String documentId){
        if(!undoStackMap.containsKey(documentId)){
            System.out.println("Undo is not possible ");
            return null;
        }
        EditCommand command=undoStackMap.get(documentId).pop();
        command.undo();
        redoStackMap.putIfAbsent(documentId,new Stack<>());
        redoStackMap.get(documentId).push(command);
        return "command undone";
    }

    @Override
    public String redoCommand(String documentId){
       if(!redoStackMap.containsKey(documentId)){
           System.out.println("redo is not possible for this document");
           return null;
       }
        EditCommand command=redoStackMap.get(documentId).pop();
        command.execute();
        undoStackMap.putIfAbsent(documentId,new Stack<>()).push(command);
        return "command redone";
    }
}
