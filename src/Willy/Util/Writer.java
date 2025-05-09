    package Willy.Util;

    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.OutputStreamWriter;
    import java.net.Socket;

    public class Writer {
        //Attributi
        private BufferedWriter bw;

        public Writer(Socket s) {
            try {
                this.bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void write(String msg) throws IOException {
            bw.write(msg);
            bw.newLine();
            bw.flush();
        }

        public void close() throws IOException {
            bw.close();
        }
    }
