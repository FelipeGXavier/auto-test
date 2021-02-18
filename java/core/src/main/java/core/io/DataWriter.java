package core.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataWriter implements Writer {

    private final int CAPACITY = 1024;
    private OutputStream outputStream;
    private StringBuffer buffer = new StringBuffer(CAPACITY);

    private DataWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private DataWriter(File file) throws FileNotFoundException {
        this.outputStream = new FileOutputStream(file);
    }

    protected static DataWriter of(OutputStream outputStream) {
        return new DataWriter(outputStream);
    }

    protected static DataWriter of(File file) throws FileNotFoundException {
        return new DataWriter(file);
    }

    @Override
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    @Override
    public Writer append(String data) {
        return null;
    }

    @Override
    public Writer append(byte[] data) {
        return null;
    }

    @Override
    public Writer appendEscape(String data) {
        return null;
    }

    private boolean full() {
        return this.buffer.length() > CAPACITY;
    }

    private void flush() throws IOException {
        if (this.full()) {
            this.outputStream.write(this.buffer.toString().getBytes(StandardCharsets.UTF_8));
            this.buffer.setLength(0);
        }
    }
}
