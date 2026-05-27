package org.example.command;

import org.example.entity.Document;

public class DeleteCommand implements EditCommand {
    private final Document document;
    private int startPosition;
    private int endPosition;
    private String deletedText;

    public DeleteCommand(Document document, int startPosition, int endPosition) {
        this.document = document;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    @Override
    public void execute() {
        if (startPosition > endPosition) {
            System.out.println("Invalid positions for delete operation.");
            return;
        }
        if (startPosition == -1 || startPosition > document.getContent().length()) {
            System.out.println("Invalid start position for delete operation.");
            return;
        }
        if (endPosition == -1 || endPosition > document.getContent().length()) {
            System.out.println("Invalid end position for delete operation.");
            return;
        }
        deletedText = document.getContent().substring(startPosition, endPosition + 1);
        document.getContent().delete(startPosition, endPosition + 1);
        System.out.println("Deleted '" + deletedText + "' from position " + startPosition + " to " + endPosition);

    }

    @Override
    public void undo() {
        if (deletedText != null) {
            document.getContent().insert(startPosition, deletedText);
            System.out.println("Undo Delete: Restored text at position " + startPosition + " to " + endPosition);
        }

    }

}
