package core.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DataWriter implements Writer {

    private final int CAPACITY = 1024;
    private final OutputStream outputStream;
    private final StringBuffer buffer = new StringBuffer(CAPACITY);

    private DataWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private DataWriter(File file) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(file);
    }

    public static DataWriter of(OutputStream outputStream) {
        return new DataWriter(outputStream);
    }

    public static DataWriter of(File file) throws FileNotFoundException {
        return new DataWriter(file);
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override
    public Writer append(String data) throws IOException {
        this.buffer.append(data);
        flush();
        return this;
    }

    @Override
    public Writer append(byte[] data) throws IOException {
        this.buffer.append(Arrays.toString(data));
        flush();
        return this;
    }

    public void finish() throws IOException {
        this.writeOutput();
        this.outputStream.close();
    }

    @Override
    public Writer appendEscape(String data) {
        return null;
    }

    public void flush() throws IOException {
        if (this.full()) {
            this.writeOutput();
        }
    }

    private boolean full() {
        return this.buffer.length() > CAPACITY;
    }

    private void writeOutput() throws IOException {
        this.outputStream.write(this.buffer.toString().getBytes(StandardCharsets.UTF_8));
        this.buffer.setLength(0);
    }

}
