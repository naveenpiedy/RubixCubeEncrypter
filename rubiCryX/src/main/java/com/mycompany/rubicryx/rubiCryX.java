package com.mycompany.rubicryx;

import java.awt.HeadlessException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Naveen Piedy Aka No Name
 */
public class rubiCryX extends javax.swing.JFrame {

    ArrayList<String> key = new ArrayList<>();
    String inputTxt, outputTxt;
    int[][][] rubi = new int[6][3][3];
    int keyCount = 0;

    public void setVal() {
        int q = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    rubi[i][j][k] = q;
                    q++;
                }
            }
        }
    }

    public void CWT(int i) {
        int[][][] Buffer = new int[1][3][3];
        for (int u = 0; u < 3; u++) {
            System.arraycopy(rubi[i][u], 0, Buffer[0][u], 0, 3);
        }
        for (int u = 0; u < 3; u++) {
            for (int z = 0; z < 3; z++) {
                rubi[i][u][z] = Buffer[0][2 - z][u];
            }
        }
    }

    public void ACWT(int i) {
        int[][][] Buffer = new int[1][3][3];
        for (int u = 0; u < 3; u++) {
            System.arraycopy(rubi[i][u], 0, Buffer[0][u], 0, 3);
        }
        for (int u = 0; u < 3; u++) {
            for (int z = 0; z < 3; z++) {
                rubi[i][u][z] = Buffer[0][z][2 - u];
            }
        }
    }

    public void topHorizonatlACW() {
        int[][][] Buffer = new int[3][1][3];
        for (int i = 0; i < 3; i++) {
            {
                System.arraycopy(rubi[i + 1][0], 0, Buffer[i][0], 0, 3);
            }
        }
        System.arraycopy(rubi[0][0], 0, rubi[1][0], 0, 3);
        System.arraycopy(Buffer[0][0], 0, rubi[2][0], 0, 3);
        System.arraycopy(Buffer[1][0], 0, rubi[3][0], 0, 3);
        System.arraycopy(Buffer[2][0], 0, rubi[0][0], 0, 3);
        ACWT(4);
    }

    public void bottomHorizontalACW() {
        int[][][] Buffer = new int[3][1][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(rubi[i + 1][2], 0, Buffer[i][0], 0, 3);
        }
        System.arraycopy(rubi[0][2], 0, rubi[1][2], 0, 3);
        System.arraycopy(Buffer[0][0], 0, rubi[2][2], 0, 3);
        System.arraycopy(Buffer[1][0], 0, rubi[3][2], 0, 3);
        System.arraycopy(Buffer[2][0], 0, rubi[0][2], 0, 3);
        ACWT(5);
    }

    public void topHorizontalCW() {
        int[][][] Buffer = new int[3][1][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(rubi[i + 1][0], 0, Buffer[i][0], 0, 3);
        }
        System.arraycopy(rubi[0][0], 0, rubi[3][0], 0, 3);
        System.arraycopy(Buffer[2][0], 0, rubi[2][0], 0, 3);
        System.arraycopy(Buffer[1][0], 0, rubi[1][0], 0, 3);
        System.arraycopy(Buffer[0][0], 0, rubi[0][0], 0, 3);
        CWT(4);
    }

    public void bottomHorizontalCW() {
        int[][][] Buffer = new int[3][1][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(rubi[i + 1][2], 0, Buffer[i][0], 0, 3);
        }
        System.arraycopy(rubi[0][2], 0, rubi[3][2], 0, 3);
        System.arraycopy(Buffer[2][0], 0, rubi[2][2], 0, 3);
        System.arraycopy(Buffer[1][0], 0, rubi[1][2], 0, 3);
        System.arraycopy(Buffer[0][0], 0, rubi[0][2], 0, 3);
        CWT(5);
    }

    public void verticalLeftACW() {
        int[][][] Buffer = new int[3][1][3];
        int[] arrr = {0, 4, 2, 5};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Buffer[i][0][j] = rubi[arrr[i + 1]][j][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            rubi[4][i][0] = rubi[0][i][0];
        }
        for (int i = 0; i < 3; i++) {
            rubi[2][i][0] = Buffer[0][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[5][i][0] = Buffer[1][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[0][i][0] = Buffer[2][0][i];
        }
        ACWT(3);
    }

    public void verticalLeftCW() {
        int[][][] Buffer = new int[3][1][3];
        int[] arrr = {0, 5, 2, 4};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Buffer[i][0][j] = rubi[arrr[i + 1]][j][0];
            }
        }
        for (int i = 0; i < 3; i++) {
            rubi[5][i][0] = rubi[0][i][0];
        }
        for (int i = 0; i < 3; i++) {
            rubi[2][i][0] = Buffer[0][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[4][i][0] = Buffer[1][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[0][i][0] = Buffer[2][0][i];
        }
        CWT(3);
    }

    public void verticalRightACW() {
        int[][][] Buffer = new int[3][1][3];
        int[] arrr = {0, 4, 2, 5};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Buffer[i][0][j] = rubi[arrr[i + 1]][j][2];
            }
        }
        for (int i = 0; i < 3; i++) {
            rubi[4][i][2] = rubi[0][i][2];
        }
        for (int i = 0; i < 3; i++) {
            rubi[2][i][2] = Buffer[0][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[5][i][2] = Buffer[1][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[0][i][2] = Buffer[2][0][i];
        }
        ACWT(1);
    }

    public void verticalRightCW() {
        int[][][] Buffer = new int[3][1][3];
        int[] arrr = {0, 5, 2, 4};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Buffer[i][0][j] = rubi[arrr[i + 1]][j][2];
            }
        }
        for (int i = 0; i < 3; i++) {
            rubi[5][i][2] = rubi[0][i][2];
        }
        for (int i = 0; i < 3; i++) {
            rubi[2][i][2] = Buffer[0][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[4][i][2] = Buffer[1][0][i];
        }
        for (int i = 0; i < 3; i++) {
            rubi[0][i][2] = Buffer[2][0][i];
        }
        CWT(1);
    }

    public static String readTextFile(String fileName) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        return content;
    }

    public static List<String> readTextFileByLines(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        return lines;
    }

    public static void writeToTextFile(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
    }

    public void encryptingTextFiles() throws IOException {
        String fileLocation = location.getText();
        try {
            String extract = readTextFile(fileLocation);
            String fileWrite = encrpypting(extract);
            writeToTextFile(fileLocation, fileWrite);
        } catch (Exception e) {
        }
    }

    public void fileEncrypt(String s) throws FileNotFoundException, IOException {
        File file = new File(s);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        int len = bytes.length;
        char[] a = new char[len];
        for (int i = 0; i < len; i++) {
            a[i] = (char) bytes[i];
        }
        //System.out.print(Arrays.toString(a));
        a = encrpypting(a);
        //byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) a[i];
        }

        File someFile = new File(s);
        try (FileOutputStream fos = new FileOutputStream(someFile)) {
            fos.write(bytes);
            fos.flush();
        }
        JOptionPane.showMessageDialog(this, "Completed.");
    }

    public char[] encrpypting(char[] input) {
        int[] sequence = new int[54];
        int q = 0;
        String[] Key = key.toArray(new String[key.size()]);
        String finalKey = Arrays.deepToString(Key);
        keyDisplay.setText(finalKey);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    sequence[q] = rubi[i][j][k];
                    //System.out.println(sequence[q]);
                    q++;
                }
            }
        }
        char[] extract = input;
        int fileLength = extract.length;
        //System.out.print(textLength);
        int fileLoop = fileLength / 54;
        int fileLeft = fileLength % 54;
        //System.out.print(fileLoop + " " + fileLeft);
        char[] outputChar = new char[fileLength + 1];
        char[] leftoverChar = new char[fileLeft + fileLoop];
        int startOfLeftoverLoop = (54 * fileLoop);
        //System.out.print(startOfLeftoverLoop);
        for (int i = 0; i < fileLeft; i++) {

            leftoverChar[i] = extract[(startOfLeftoverLoop)];
            startOfLeftoverLoop++;

        }
        startOfLeftoverLoop = 54*fileLoop;
        int loopHelper = 0;
        for (int i = 0; i < fileLoop; i++) {
            for (int j = 0; j < 54; j++) {
                outputChar[loopHelper + j] = extract[loopHelper + sequence[j]];
                //System.out.println((loopHelper + j)+" "+ extract[loopHelper + sequence[j]]+" "+(loopHelper + sequence[j]));
            }
            loopHelper = 54 + loopHelper;

        }
        for (int i = 0; i < fileLeft; i++) {

            outputChar[startOfLeftoverLoop] = leftoverChar[i];
            //System.out.println(outputChar[startOfLeftoverLoop]);
            startOfLeftoverLoop++;
        }
        
        return outputChar;
    }

    public String encrpypting(String input) {
        int[] sequence = new int[54];
        int q = 0;
        String finalText = "";
        String[] Key = key.toArray(new String[key.size()]);
        String finalKey = Arrays.deepToString(Key);
        keyDisplay.setText(finalKey);
        //System.out.println("Start");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    sequence[q] = rubi[i][j][k];
                    //System.out.println(sequence[q]);
                    q++;
                }
            }
        }
        char[] extract = input.toCharArray();
        int textLength = extract.length;
        //System.out.println(textLength);
        int textLoop = textLength / 54;
        int textLeft = textLength % 54;
        //System.out.println(textLoop + " " + textLeft);
        char[] outputChar = new char[textLength + 1];
        char[] leftoverChar = new char[textLeft + textLoop];
        int startOfLeftoverLoop = (54 * textLoop);
        //System.out.println(startOfLeftoverLoop);
        for (int i = 0; i < textLeft; i++) {

            leftoverChar[i] = extract[(startOfLeftoverLoop)];
            //System.out.println(extract[(startOfLeftoverLoop)]);
            startOfLeftoverLoop++;

        }
        startOfLeftoverLoop = (54 * textLoop);
        int loopHelper = 0;
        for (int i = 0; i < textLoop; i++) {
            for (int j = 0; j < 54; j++) {
                outputChar[loopHelper + j] = extract[loopHelper + sequence[j]];
                //System.out.println((loopHelper + j)+" "+ extract[loopHelper + sequence[j]]+" "+(loopHelper + sequence[j]));

            }
            loopHelper = 54 + loopHelper;
        }
        for (int i = 0; i < textLeft; i++) {

            outputChar[startOfLeftoverLoop] = leftoverChar[i];
            //System.out.println(outputChar[startOfLeftoverLoop]);
            startOfLeftoverLoop++;
        }
        for (int i = 0; i < textLength; i++) {
            char tempChar = outputChar[i];
            String temp = Character.toString(tempChar);
            finalText = finalText.concat(temp);
        }
        //outputChar = null;
        //leftoverChar= null;
        return finalText;
    }

    public rubiCryX() {
        setVal();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encDec = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        keyDisplay = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputText = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        topHorizontalAntiClockWise = new javax.swing.JButton();
        bottomHorizontalAntiClockWise = new javax.swing.JButton();
        verticalRightAntiClockWise = new javax.swing.JButton();
        verticalLeftAntiClockWise = new javax.swing.JButton();
        topHorizontalClockWise = new javax.swing.JButton();
        bottomHorizontalClockWise = new javax.swing.JButton();
        verticalRightClockWise = new javax.swing.JButton();
        verticalLeftClockWise = new javax.swing.JButton();
        textBoxEncryption = new javax.swing.JButton();
        encryptionButton = new javax.swing.JRadioButton();
        decryptionButton = new javax.swing.JRadioButton();
        keyReset = new javax.swing.JButton();
        textFileButton = new javax.swing.JButton();
        location = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fileEncryptButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Input ");

        inputText.setColumns(20);
        inputText.setLineWrap(true);
        inputText.setRows(5);
        jScrollPane1.setViewportView(inputText);

        outputText.setColumns(20);
        outputText.setLineWrap(true);
        outputText.setRows(5);
        jScrollPane2.setViewportView(outputText);

        jLabel2.setText("Output");

        jLabel3.setText("Key");

        topHorizontalAntiClockWise.setText("THACW");
        topHorizontalAntiClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topHorizontalAntiClockWiseActionPerformed(evt);
            }
        });

        bottomHorizontalAntiClockWise.setText("BHACW");
        bottomHorizontalAntiClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottomHorizontalAntiClockWiseActionPerformed(evt);
            }
        });

        verticalRightAntiClockWise.setText("VRACW");
        verticalRightAntiClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalRightAntiClockWiseActionPerformed(evt);
            }
        });

        verticalLeftAntiClockWise.setText("VLACW");
        verticalLeftAntiClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalLeftAntiClockWiseActionPerformed(evt);
            }
        });

        topHorizontalClockWise.setText("THCW");
        topHorizontalClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topHorizontalClockWiseActionPerformed(evt);
            }
        });

        bottomHorizontalClockWise.setText("BHCW");
        bottomHorizontalClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottomHorizontalClockWiseActionPerformed(evt);
            }
        });

        verticalRightClockWise.setText("VRCW");
        verticalRightClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalRightClockWiseActionPerformed(evt);
            }
        });

        verticalLeftClockWise.setText("VLCW");
        verticalLeftClockWise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verticalLeftClockWiseActionPerformed(evt);
            }
        });

        textBoxEncryption.setText("Text Box");
        textBoxEncryption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBoxEncryptionActionPerformed(evt);
            }
        });

        encDec.add(encryptionButton);
        encryptionButton.setText("Encryption");

        encDec.add(decryptionButton);
        decryptionButton.setText("Decryption");

        keyReset.setText("Clear Key");
        keyReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyResetActionPerformed(evt);
            }
        });

        textFileButton.setText("Text File");
        textFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFileButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("File Location");

        fileEncryptButton.setText("File");
        fileEncryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileEncryptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(120, 120, 120))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(textBoxEncryption)
                .addGap(77, 77, 77)
                .addComponent(textFileButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fileEncryptButton)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(encryptionButton)
                                .addGap(47, 47, 47)
                                .addComponent(decryptionButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(200, 200, 200)
                                .addComponent(keyReset)))
                        .addGap(202, 202, 202))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(topHorizontalAntiClockWise)
                                    .addComponent(topHorizontalClockWise))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bottomHorizontalClockWise)
                                    .addComponent(bottomHorizontalAntiClockWise))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(verticalRightClockWise)
                                        .addGap(73, 73, 73)
                                        .addComponent(verticalLeftClockWise))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(verticalRightAntiClockWise)
                                        .addGap(61, 61, 61)
                                        .addComponent(verticalLeftAntiClockWise))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(keyDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(encryptionButton)
                    .addComponent(decryptionButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topHorizontalAntiClockWise)
                    .addComponent(bottomHorizontalAntiClockWise)
                    .addComponent(verticalRightAntiClockWise)
                    .addComponent(verticalLeftAntiClockWise))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(topHorizontalClockWise)
                    .addComponent(bottomHorizontalClockWise)
                    .addComponent(verticalRightClockWise)
                    .addComponent(verticalLeftClockWise))
                .addGap(18, 18, 18)
                .addComponent(keyReset)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBoxEncryption)
                    .addComponent(textFileButton)
                    .addComponent(fileEncryptButton))
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topHorizontalAntiClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topHorizontalAntiClockWiseActionPerformed

        key.add("THACW");
    }//GEN-LAST:event_topHorizontalAntiClockWiseActionPerformed

    private void bottomHorizontalAntiClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottomHorizontalAntiClockWiseActionPerformed

        key.add("BHACW");

    }//GEN-LAST:event_bottomHorizontalAntiClockWiseActionPerformed

    private void topHorizontalClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topHorizontalClockWiseActionPerformed

        key.add("THCW");

    }//GEN-LAST:event_topHorizontalClockWiseActionPerformed

    private void bottomHorizontalClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottomHorizontalClockWiseActionPerformed

        key.add("BHCW");
    }//GEN-LAST:event_bottomHorizontalClockWiseActionPerformed

    private void verticalRightAntiClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalRightAntiClockWiseActionPerformed

        key.add("VRACW");

    }//GEN-LAST:event_verticalRightAntiClockWiseActionPerformed

    private void verticalRightClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalRightClockWiseActionPerformed

        key.add("VRCW");
    }//GEN-LAST:event_verticalRightClockWiseActionPerformed

    private void verticalLeftAntiClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalLeftAntiClockWiseActionPerformed

        key.add("VLACW");
    }//GEN-LAST:event_verticalLeftAntiClockWiseActionPerformed

    private void verticalLeftClockWiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verticalLeftClockWiseActionPerformed

        key.add("VLCW");
    }//GEN-LAST:event_verticalLeftClockWiseActionPerformed

    private void textBoxEncryptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBoxEncryptionActionPerformed
        if (encryptionButton.isSelected()) {
            setVal();
            String[] Key = key.toArray(new String[key.size()]);
            int Keylen = Key.length;
            for (int i = 0; i < Keylen; i++) {
                switch (Key[i]) {
                    case "THACW":
                        topHorizonatlACW();
                        break;
                    case "THCW":
                        topHorizontalCW();
                        break;
                    case "BHACW":
                        bottomHorizontalACW();
                        break;
                    case "BHCW":
                        bottomHorizontalCW();
                        break;
                    case "VRACW":
                        verticalRightACW();
                        break;
                    case "VRCW":
                        verticalRightCW();
                        break;
                    case "VLACW":
                        verticalLeftACW();
                        break;
                    case "VLCW":
                        verticalLeftCW();
                        break;
                }
            }
            inputTxt = inputText.getText();
            outputText.setText(encrpypting(inputTxt));
        } else if (decryptionButton.isSelected()) {
            setVal();
            String[] Key = key.toArray(new String[key.size()]);
            int Keylen = Key.length;
            for (int i = Keylen - 1; i > -1; i--) {
                switch (Key[i]) {
                    case "THACW":
                        topHorizontalCW();
                        break;
                    case "THCW":
                        topHorizonatlACW();
                        break;
                    case "BHACW":
                        bottomHorizontalCW();
                        break;
                    case "BHCW":
                        bottomHorizontalACW();
                        break;
                    case "VRACW":
                        verticalRightCW();
                        break;
                    case "VRCW":
                        verticalRightACW();
                        break;
                    case "VLACW":
                        verticalLeftCW();
                        break;
                    case "VLCW":
                        verticalLeftACW();
                        break;
                }
            }
            inputTxt = inputText.getText();
            outputText.setText(encrpypting(inputTxt));
        } else {
            JOptionPane.showMessageDialog(this, "Click the radio button first");
        }
    }//GEN-LAST:event_textBoxEncryptionActionPerformed

    private void keyResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyResetActionPerformed
        key.clear();
        keyDisplay.setText("");
        setVal();
    }//GEN-LAST:event_keyResetActionPerformed

    private void textFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFileButtonActionPerformed
        try {
            if (encryptionButton.isSelected()) {
                setVal();
                String[] Key = key.toArray(new String[key.size()]);
                int Keylen = Key.length;
                for (int i = 0; i < Keylen; i++) {
                    switch (Key[i]) {
                        case "THACW":
                            topHorizonatlACW();
                            break;
                        case "THCW":
                            topHorizontalCW();
                            break;
                        case "BHACW":
                            bottomHorizontalACW();
                            break;
                        case "BHCW":
                            bottomHorizontalCW();
                            break;
                        case "VRACW":
                            verticalRightACW();
                            break;
                        case "VRCW":
                            verticalRightCW();
                            break;
                        case "VLACW":
                            verticalLeftACW();
                            break;
                        case "VLCW":
                            verticalLeftCW();
                            break;
                    }
                }
                encryptingTextFiles();
            } else if (decryptionButton.isSelected()) {
                String[] Key = key.toArray(new String[key.size()]);
                int Keylen = Key.length;
                setVal();
                for (int i = Keylen - 1; i > -1; i--) {
                    switch (Key[i]) {
                        case "THACW":
                            topHorizontalCW();
                            break;
                        case "THCW":
                            topHorizonatlACW();
                            break;
                        case "BHACW":
                            bottomHorizontalCW();
                            break;
                        case "BHCW":
                            bottomHorizontalACW();
                            break;
                        case "VRACW":
                            verticalRightCW();
                            break;
                        case "VRCW":
                            verticalRightACW();
                            break;
                        case "VLACW":
                            verticalLeftCW();
                            break;
                        case "VLCW":
                            verticalLeftACW();
                            break;
                    }
                }
                encryptingTextFiles();
            } else {
                JOptionPane.showMessageDialog(this, "Click the radio button first");
            }
        } catch (IOException | HeadlessException E) {
        }
    }//GEN-LAST:event_textFileButtonActionPerformed

    private void fileEncryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileEncryptButtonActionPerformed
        try {
            if (encryptionButton.isSelected()) {
                setVal();
                String[] Key = key.toArray(new String[key.size()]);
                int Keylen = Key.length;
                for (int i = 0; i < Keylen; i++) {
                    switch (Key[i]) {
                        case "THACW":
                            topHorizonatlACW();
                            break;
                        case "THCW":
                            topHorizontalCW();
                            break;
                        case "BHACW":
                            bottomHorizontalACW();
                            break;
                        case "BHCW":
                            bottomHorizontalCW();
                            break;
                        case "VRACW":
                            verticalRightACW();
                            break;
                        case "VRCW":
                            verticalRightCW();
                            break;
                        case "VLACW":
                            verticalLeftACW();
                            break;
                        case "VLCW":
                            verticalLeftCW();
                            break;
                    }
                }
                String fileLocation = location.getText();
                fileEncrypt(fileLocation);
            } else if (decryptionButton.isSelected()) {
                setVal();
                String[] Key = key.toArray(new String[key.size()]);
                int Keylen = Key.length;
                for (int i = Keylen - 1; i > -1; i--) {
                    switch (Key[i]) {
                        case "THACW":
                            topHorizontalCW();
                            break;
                        case "THCW":
                            topHorizonatlACW();
                            break;
                        case "BHACW":
                            bottomHorizontalCW();
                            break;
                        case "BHCW":
                            bottomHorizontalACW();
                            break;
                        case "VRACW":
                            verticalRightCW();
                            break;
                        case "VRCW":
                            verticalRightACW();
                            break;
                        case "VLACW":
                            verticalLeftCW();
                            break;
                        case "VLCW":
                            verticalLeftACW();
                            break;
                    }
                }
                String fileLocation = location.getText();
                fileEncrypt(fileLocation);
            } else {
                JOptionPane.showMessageDialog(this, "Click the radio button first");
            }
        } catch (IOException | HeadlessException E) {
        }
    }//GEN-LAST:event_fileEncryptButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(rubiCryX.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new rubiCryX().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bottomHorizontalAntiClockWise;
    private javax.swing.JButton bottomHorizontalClockWise;
    private javax.swing.JRadioButton decryptionButton;
    private javax.swing.ButtonGroup encDec;
    private javax.swing.JRadioButton encryptionButton;
    private javax.swing.JButton fileEncryptButton;
    private javax.swing.JTextArea inputText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField keyDisplay;
    private javax.swing.JButton keyReset;
    private javax.swing.JTextField location;
    private javax.swing.JTextArea outputText;
    private javax.swing.JButton textBoxEncryption;
    private javax.swing.JButton textFileButton;
    private javax.swing.JButton topHorizontalAntiClockWise;
    private javax.swing.JButton topHorizontalClockWise;
    private javax.swing.JButton verticalLeftAntiClockWise;
    private javax.swing.JButton verticalLeftClockWise;
    private javax.swing.JButton verticalRightAntiClockWise;
    private javax.swing.JButton verticalRightClockWise;
    // End of variables declaration//GEN-END:variables
}
