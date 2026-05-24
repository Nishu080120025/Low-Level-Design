package org.example.command;

import org.example.entity.Document;

public class InsertCommand implements EditCommand{

    private final Document document;
    private int position;
    private String text;

    public InsertCommand(Document document, int position, String text) {
        this.document = document;
        this.position = position;
        this.text = text;
    }

    @Override
    public void execute() {
        if(position==-1 || position>document.getContent().length()){
            position=document.getContent().length();
        }
        if(position==document.getContent().length()){
            document.getContent().append(text);
            return;
        }
        document.getContent().insert(position,text);
        System.out.println("Inserted '"+text+"' at position "+position);
    }

    @Override
    public void undo(){
        document.getContent().delete(position, position + text.length());
        System.out.println("Undo Insert: Removed '"+text+"' from position "+position);
    }
}
