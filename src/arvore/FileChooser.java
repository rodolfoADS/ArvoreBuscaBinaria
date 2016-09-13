package arvore;

import javax.swing.JFileChooser;

public class FileChooser {

    public static void main(String[] args) {
        JFileChooser arquivo = new JFileChooser();
        arquivo.showOpenDialog(null);
    }
}
