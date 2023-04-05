package ru.netology;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;
public class ClientLog {
    public ClientLog(File textFile) {
        try (FileWriter fw = new FileWriter(textFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)){
            out.println("productNum,amount");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void log (int productNum, int amount, File textFile) {
        try (FileWriter fw = new FileWriter(textFile, true);
             BufferedWriter bw = new BufferedWriter(fw)){
             bw.write(productNum + "," + amount);
             bw.newLine();
             bw.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void exportAsCSV(File textFile, File csvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                String text;
                while ((text = br.readLine()) != null) {
                    writer.writeNext(text.split(","));
                }
            } catch (Exception exCsv) {
                System.out.println(exCsv.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
