package de.cofinpro.sorting;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

public class PDFMerger {

    public static void main(String[] args) {
        PDFMerger merger = new PDFMerger();
        merger.mergePDFs("/Users/jwisskirchen/IdeaProjects/SortingTool/src/main/resources/pdf/CV_Anhang_Wißkirchen_2024.pdf",
                "/Users/jwisskirchen/IdeaProjects/SortingTool/src/main/resources/pdf/CV_Wisskirchen_2024.pdf",
                "/Users/jwisskirchen/IdeaProjects/SortingTool/src/main/resources/pdf/CV_Juergen_Wisskirchen.pdf");
    }

    public void mergePDFs(String pdfFilePath2, String pdfFilePath1, String destinationFilePath) {
        try {
            PDFMergerUtility pdfMerger = new PDFMergerUtility();
            pdfMerger.addSource(new File(pdfFilePath1));
            pdfMerger.addSource(new File(pdfFilePath2));
            pdfMerger.setDestinationFileName(destinationFilePath);
            pdfMerger.mergeDocuments(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
