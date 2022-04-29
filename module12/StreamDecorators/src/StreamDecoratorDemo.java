import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class StreamDecoratorDemo {
  public static void main(String [] args) {
    FileOutputStream out = null;

    try {
      out = new FileOutputStream("out.txt");
    }
    catch (FileNotFoundException e) {

    }
    String input = "I love Program Design Paradigms. "
                   + "THIS IS A GREAT COURSE!";

    //a regular print stream, writing to file.
    PrintStream ps = new PrintStream(out);
    ps.println(input);

    //a printing, lower-casing output stream. Note, to the same file!
    ps = new PrintStream(new LowerCaseOutputStream((out)));
    ps.println(input);

    //a printing, shift-ciphering output stream. Note, to the same file!
    ps = new PrintStream(new ShiftCipherOutputStream((out)));
    ps.println(input);

    //a printing, lower-casing,shift-ciphering output stream. Note, to the
    // same file!
    ps = new PrintStream(new LowerCaseOutputStream(new ShiftCipherOutputStream(out)));

    ps.println(input);


    ps.close();
  }

  /**
   * This class implements a simple one-character shift cipher on bytes in an
   * output stream. This means 'a' will be 'b', 'b' will be 'c', and so on
   * with 'z' being 'a'. The same happens to upper case characters.
   * Non-character bytes are left untouched.
   */

  static class ShiftCipherOutputStream extends FilterOutputStream {

    /**
     * Creates an output stream filter built on top of the specified underlying
     * output stream.
     *
     * @param out the underlying output stream to be assigned to the field
     *            <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public ShiftCipherOutputStream(OutputStream out) {
      super(out);
    }

    @Override
    public void write(int b) throws IOException{
      if (Character.isUpperCase((char)b)) {
        int offset = b - (int)'A';
        offset = (int)'A' + (offset + 1) % 26;
        super.write(offset);
      }
      else if (Character.isLowerCase((char)b)) {
        int offset = b - (int)'a';
        offset = (int)'a' + (offset + 1) % 26;
        super.write(offset);
      }
      else {
        super.write(b);
      }

    }
  }

  /**
   * This class implements a lower case output stream. Each character is
   * converted to lower case before sending it out. If the byte is a
   * non-character then it is left untouched.
   */

  static class LowerCaseOutputStream extends FilterOutputStream {
    /**
     * Creates an output stream filter built on top of the specified underlying
     * output stream.
     *
     * @param out the underlying output stream to be assigned to the field
     *            <tt>this.out</tt> for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public LowerCaseOutputStream(OutputStream out) {
      super(out);
    }

    @Override
    public void write(int b) throws IOException{
      if (Character.isAlphabetic((char)b)) {
        super.write((int)Character.toLowerCase((char)b));
      }
      else {
        super.write(b);
      }
    }
  }
}
