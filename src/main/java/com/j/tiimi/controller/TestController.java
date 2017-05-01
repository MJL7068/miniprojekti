package com.j.tiimi.controller;

import java.util.ArrayList;
import com.j.tiimi.entity.*;
import com.j.tiimi.repository.ReferenceRepository;
import com.j.tiimi.service.ReferenceService;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private ReferenceService referenceService;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String bibtex() throws IOException {
        seedReferences();
        String latex = referenceService.getBibtexString();
        System.out.println("\n\nlatex : \n\n" + latex);
        System.out.println("----------------------------\n\n");
        String bibtex = readFile(referenceService.getBibtexFile());
        System.out.println("bibtex: \n\n" + bibtex);
        return latex;
    }
    
    private String readFile(File file) throws IOException {
        Scanner reader = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        
        while (reader.hasNextLine()) {
            sb.append(reader.nextLine() + "\n"); //nextLine syö rivinvaihdot >_>
        }
        
        reader.close();
        return sb.toString();
    }
    
    //puts references to repo
    private void seedReferences() {
        Reference reference = new Reference();
        reference.setType("Book");
        reference.setIdentifier("Martin09");
        ArrayList<Attribute> attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "Martin, Robert"));
        attributes.add(new Attribute().setAttributes("title", "Clean Code: A Handbook of Agile Software Craftsmanship"));
        attributes.add(new Attribute().setAttributes("year", "2008"));
        attributes.add(new Attribute().setAttributes("publisher", "Prentice Hall"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        
        reference = new Reference();
        reference.setType("Article");
        reference.setIdentifier("fox");
        attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "Fox, Armando and Patterson, David"));
        attributes.add(new Attribute().setAttributes("title", "Crossing the software education chasm"));
        attributes.add(new Attribute().setAttributes("journal", "Communications of ACM"));
        attributes.add(new Attribute().setAttributes("year", "2012"));
        attributes.add(new Attribute().setAttributes("volume", "55"));
        attributes.add(new Attribute().setAttributes("address", "New York, NY, USA"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        
        reference = new Reference();
        reference.setType("Inproceedings");
        reference.setIdentifier("Begel_2008");
        attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "Begel, Andrew and Simon, Beth"));
        attributes.add(new Attribute().setAttributes("title", "Struggles of new college graduates in their first software development job"));
        attributes.add(new Attribute().setAttributes("year", "2008"));
        attributes.add(new Attribute().setAttributes("booktitle", "Proceedings of the SIGCSE '08"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        
        reference = new Reference();
        reference.setType("Book");
        reference.setIdentifier("SWEBOK");
        attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "Big, Guy"));
        attributes.add(new Attribute().setAttributes("title", "Guide to the Software Engineering Body of Knownledge"));
        attributes.add(new Attribute().setAttributes("year", "2004"));
        attributes.add(new Attribute().setAttributes("publisher", "IEEE Computer Society"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        
        reference = new Reference();
        reference.setType("Book");
        reference.setIdentifier("scrum");
        attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "ÖÖÖÖÖÖ"));
        attributes.add(new Attribute().setAttributes("title", "ÅÅÅÅÅÅ"));
        attributes.add(new Attribute().setAttributes("year", "1999"));
        attributes.add(new Attribute().setAttributes("publisher", "ÄÖÅ"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        
        /*
        reference = new Reference();
        reference.setType("Inproceedings");
        reference.setIdentifier("scrum");
        attributes = new ArrayList();
        attributes.add(new Attribute().setAttributes("author", "Kölling, Michael and Barnes, David J."));
        attributes.add(new Attribute().setAttributes("title", "Enhancing apprentice-based learning of Java"));
        attributes.add(new Attribute().setAttributes("booktitle", "SIGCSE '04: Proceedings of the 35th SIGCSE technical symposium on Computer science education"));
        attributes.add(new Attribute().setAttributes("year", "2004"));
        attributes.add(new Attribute().setAttributes("pages", "286--290"));
        attributes.add(new Attribute().setAttributes("publisher", "ACM"));
        reference.setAttributes(attributes);
        referenceService.createReference(reference);
        */
    }
    
}
