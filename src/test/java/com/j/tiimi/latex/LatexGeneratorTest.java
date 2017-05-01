package com.j.tiimi.latex;

import java.util.ArrayList;
import com.j.tiimi.service.ReferenceService;
import com.j.tiimi.entity.*;
import com.j.tiimi.latex.BibtexGenerator;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LatexGeneratorTest {

    @Autowired
    private ReferenceService service;
    Reference reference1;
    Reference reference2;
    BibtexGenerator generator;
    String latex1;
    String latex2;

    @Before
    public void initialize() {
        generator = new BibtexGenerator();
        
        reference1 = new Reference();
        reference1.setType("Book");
        reference1.setIdentifier("Martin09");
        ArrayList<Attribute> attributes1 = new ArrayList();
        attributes1.add(new Attribute().setAttributes("author", "Martin, Robert"));
        attributes1.add(new Attribute().setAttributes("title", "Clean Code: A Handbook of Agile Software Craftsmanship"));
        attributes1.add(new Attribute().setAttributes("year", "2008"));
        attributes1.add(new Attribute().setAttributes("publisher", "Prentice Hall"));
        reference1.setAttributes(attributes1);

        latex1 = "@book{Martin09,\n\tauthor = {Martin, Robert},\n\ttitle = {Clean Code: A Handbook of Agile Software Craftsmanship},\n\t"
                + "year = {2008},\n\tpublisher = {Prentice Hall},\n}\n\n";
        
        reference2 = new Reference();
        reference2 = new Reference();
        reference2.setType("Book");
        reference2.setIdentifier("ÄÄÄÄ");
        ArrayList<Attribute> attributes2 = new ArrayList();
        attributes2.add(new Attribute().setAttributes("author", "ÖÖÖÖÖÖ"));
        attributes2.add(new Attribute().setAttributes("title", "ÅÅÅÅÅÅ"));
        attributes2.add(new Attribute().setAttributes("year", "1999"));
        attributes2.add(new Attribute().setAttributes("publisher", "äöå"));
        reference2.setAttributes(attributes2);

        latex2 = "@book{\\\"{A}\\\"{A}\\\"{A}\\\"{A},\n\tauthor = {\\\"{O}\\\"{O}\\\"{O}\\\"{O}\\\"{O}\\\"{O}},\n"
                + "\ttitle = {\\\"{AA}\\\"{AA}\\\"{AA}\\\"{AA}\\\"{AA}\\\"{AA}},\n\t"
                + "year = {1999},\n\tpublisher = {\\\"{a}\\\"{o}\\\"{aa}},\n}\n\n";
    }

    @Test
    public void getLatexStringTest1() {
        ArrayList<Reference> reflist = new ArrayList();
        reflist.add(reference1);
        assertEquals(generator.getBibtex(reflist), latex1);
    }

    @Test
    public void getLatexStringTest2() {
        ArrayList<Reference> reflist = new ArrayList();
        reflist.add(reference2);
        assertEquals(generator.getBibtex(reflist), latex2);
    }
    
    @Test
    public void getLatexFileTest1() {
        ArrayList<Reference> reflist = new ArrayList();
        reflist.add(reference1);
        try {
            assertEquals(readFile(reflist), latex1);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String readFile(ArrayList<Reference> reflist) throws IOException {
        Scanner reader = new Scanner(generator.getBibtexFile(reflist));
        StringBuilder sb = new StringBuilder();

        while (reader.hasNextLine()) {
            sb.append(reader.nextLine() + "\n");
        }
        reader.close();
        return sb.toString();
    }

}
