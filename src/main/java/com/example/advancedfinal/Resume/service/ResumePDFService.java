package com.example.advancedfinal.Resume.service;

// import com.lowagie.text.;
import com.itextpdf.text.BaseColor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.advancedfinal.Resume.entity.Resume;
import java.awt.Color;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class ResumePDFService {

    public byte[] generateResumePDF(Resume resume) {
        try {
            Document doc = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(doc, out);
            doc.open();

            // Style variables
            Font headerFont;
            Font subHeader;
            Font bodyFont;
            BaseColor accentColor;

            switch (resume.getTemplateName().toLowerCase()) {
                case "modern":
                  headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
                    subHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new Color(0, 153, 204));
                    bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);
                    accentColor = BaseColor.BLUE;
                    break;

                case "creative":
                headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, Color.MAGENTA);
                subHeader = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, Color.MAGENTA);
                bodyFont = FontFactory.getFont(FontFactory.COURIER, 11, Color.DARK_GRAY);

                    accentColor = BaseColor.MAGENTA;
                    break;

                case "classic":
                default:
                   headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Color.BLACK);
                    subHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Color.BLACK);
                    bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Color.BLACK);
                    accentColor = BaseColor.BLACK;
                    break;
            }

            // -- HEADER
            Paragraph name = new Paragraph(resume.getFullName(), headerFont);
            name.setAlignment(Element.ALIGN_CENTER);
            doc.add(name);

            Paragraph contact = new Paragraph(resume.getEmail() + " | " + resume.getPhoneNumber(), bodyFont);
            contact.setAlignment(Element.ALIGN_CENTER);
            doc.add(contact);

            doc.add(new Paragraph(resume.getAddress(), bodyFont));
            doc.add(Chunk.NEWLINE);

            // -- SUMMARY
            doc.add(new Paragraph("Summary", subHeader));
            doc.add(new Paragraph(resume.getSummary(), bodyFont));
            doc.add(Chunk.NEWLINE);

            // -- EDUCATION
            if (!resume.getEducations().isEmpty()) {
                doc.add(new Paragraph("Education", subHeader));
                for (var edu : resume.getEducations()) {
                    doc.add(new Paragraph(
                            edu.getDegree() + " - " + edu.getInstitutionName() + " (" + edu.getStartDate() + " to " + edu.getEndDate() + ")",
                            bodyFont
                    ));
                }
                doc.add(Chunk.NEWLINE);
            }

            // -- EXPERIENCE
            if (!resume.getExperiences().isEmpty()) {
                doc.add(new Paragraph("Experience", subHeader));
                for (var exp : resume.getExperiences()) {
                    doc.add(new Paragraph(
                            exp.getJobTitle() + " at " + exp.getCompanyName() + " (" + exp.getStartDate() + " - " + exp.getEndDate() + ")",
                            bodyFont
                    ));
                    doc.add(new Paragraph(exp.getDescription(), bodyFont));
                }
                doc.add(Chunk.NEWLINE);
            }

            // -- SKILLS
            if (!resume.getSkills().isEmpty()) {
                doc.add(new Paragraph("Skills", subHeader));
                for (var skill : resume.getSkills()) {
                    doc.add(new Paragraph("• " + skill.getSkillName() + " (" + skill.getProficiency() + ")", bodyFont));
                }
            }

            doc.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate styled PDF", e);
        }
    }
}


// import com.itextpdf.text.BaseColor;
// import com.lowagie.text.*;
// import com.lowagie.text.pdf.*;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import  com.example.advancedfinal.Resume.entity.Resume;



// import java.io.ByteArrayOutputStream;


// @Service
// @RequiredArgsConstructor
// public class ResumePDFService {

//     public byte[] generateResumePDF(Resume resume) {
//         try {
//             Document doc = new Document();
//             ByteArrayOutputStream out = new ByteArrayOutputStream();
//             PdfWriter.getInstance(doc, out);
//             doc.open();

//             // Style variables
//             Font headerFont;
//             Font subHeader;
//             Font bodyFont;
//             BaseColor accentColor;

//             switch (resume.getTemplateName().toLowerCase()) {
//                 case "modern":
//                     headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
//                     subHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, new BaseColor(0, 153, 204));
//                     bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
//                     accentColor = new BaseColor(0, 153, 204); // teal
//                     break;

//                 case "creative":
//                     headerFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 20, BaseColor.MAGENTA);
//                     subHeader = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.MAGENTA);
//                     bodyFont = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.DARK_GRAY);
//                     accentColor = BaseColor.MAGENTA;
//                     break;

//                 case "classic":
//                 default:
//                     headerFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLACK);
//                     subHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
//                     bodyFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
//                     accentColor = BaseColor.BLACK;
//                     break;
//             }

//             // -- HEADER
//             Paragraph name = new Paragraph(resume.getFullName(), headerFont);
//             name.setAlignment(Element.ALIGN_CENTER);
//             doc.add(name);

//             Paragraph contact = new Paragraph(resume.getEmail() + " | " + resume.getPhoneNumber(), bodyFont);
//             contact.setAlignment(Element.ALIGN_CENTER);
//             doc.add(contact);

//             doc.add(new Paragraph(resume.getAddress(), bodyFont));
//             doc.add(Chunk.NEWLINE);

//             // -- SUMMARY
//             doc.add(new Paragraph("Summary", subHeader));
//             doc.add(new Paragraph(resume.getSummary(), bodyFont));
//             doc.add(Chunk.NEWLINE);

//             // -- EDUCATION
//             if (!resume.getEducations().isEmpty()) {
//                 doc.add(new Paragraph("Education", subHeader));
//                 for (var edu : resume.getEducations()) {
//                     doc.add(new Paragraph(
//                             edu.getDegree() + " - " + edu.getInstitutionName() + " (" + edu.getStartDate() + " to " + edu.getEndDate() + ")",
//                             bodyFont
//                     ));
//                 }
//                 doc.add(Chunk.NEWLINE);
//             }

//             // -- EXPERIENCE
//             if (!resume.getExperiences().isEmpty()) {
//                 doc.add(new Paragraph("Experience", subHeader));
//                 for (var exp : resume.getExperiences()) {
//                     doc.add(new Paragraph(
//                             exp.getJobTitle() + " at " + exp.getCompanyName() + " (" + exp.getStartDate() + " - " + exp.getEndDate() + ")",
//                             bodyFont
//                     ));
//                     doc.add(new Paragraph(exp.getDescription(), bodyFont));
//                 }
//                 doc.add(Chunk.NEWLINE);
//             }

//             // -- SKILLS
//             if (!resume.getSkills().isEmpty()) {
//                 doc.add(new Paragraph("Skills", subHeader));
//                 for (var skill : resume.getSkills()) {
//                     doc.add(new Paragraph("• " + skill.getSkillName() + " (" + skill.getProficiency() + ")", bodyFont));
//                 }
//             }

//             doc.close();
//             return out.toByteArray();

//         } catch (Exception e) {
//             throw new RuntimeException("Failed to generate styled PDF", e);
//         }
//     }
// }
