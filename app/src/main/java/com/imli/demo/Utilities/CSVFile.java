package com.imli.demo.Utilities;

import android.util.Log;

import com.imli.demo.ViewModels.CSVFileGetterSetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by developers on 23/04/16.
 */
public class CSVFile {

    private InputStream inputStream;
    private ArrayList<CSVFileGetterSetter> list;

    public CSVFile(InputStream inputStream) {
        this.inputStream = inputStream;
        list = new ArrayList<>();
    }

    public ArrayList<CSVFileGetterSetter> readFile() {

        int i = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                i++;
                if (i == 1)
                    continue;
                else {
                    String[] row = csvLine.split(",");

                    CSVFileGetterSetter csv = new CSVFileGetterSetter();
                    csv.setSongName(row[0]);
                    csv.setArtistName(row[1]);
                    csv.setAlbumName(row[2]);
                    csv.setArtistTaken(0);
                    csv.setAlbumTaken(0);
                    list.add(csv);

                }

            }
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }

        return list;
    }
}
