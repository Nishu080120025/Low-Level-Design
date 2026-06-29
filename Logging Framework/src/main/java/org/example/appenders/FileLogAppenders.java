package org.example.appenders;

import lombok.extern.java.Log;
import org.example.formatters.LogFormatters;
import org.example.model.LogMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogAppenders implements LogAppenders{
    private final LogFormatters logFormatters;
    private final BufferedWriter writer;
    public FileLogAppenders(LogFormatters logFormatters){
        this.logFormatters=logFormatters;
        try{
            this.writer=new BufferedWriter(new FileWriter(new File("log.txt"),true));
        }
        catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to open the file",e);
        }
    }
    @Override
    public synchronized void append(LogMessage message){
        try{
            writer.append(logFormatters.formatLog(message));
            writer.newLine();
            writer.flush();
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to write to the file",e);
        }
    }

    public synchronized void close(){
        try{
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to close the file",e);
        }
    }
}
