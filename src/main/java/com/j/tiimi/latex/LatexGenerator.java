package com.j.tiimi.latex;

import com.j.tiimi.entity.*;
import org.springframework.stereotype.Component;

@Component
public class LatexGenerator {

    public String generateLatex(Reference reference) {
        StringBuilder latex = new StringBuilder();
        String refType = "@" + reference.getType().toLowerCase() + "{";
        latex.append(refType);
        buildString(reference.getIdentifier(), latex);
        latex.append(",\n");
        for (Attribute attr : reference.getAttributes()) {
            latex.append("\t");
            buildString(attr.getKey().toLowerCase(), latex);
            latex.append(" = {");
            buildString(attr.getValue(), latex);
            latex.append("},\n");
        }
        latex.append("}\n\n");
        return latex.toString();
    }
    
    private void buildString(String string, StringBuilder latex) {
        for (int i = 0; i < string.length(); i++) {
                switch (string.charAt(i)) {
                    case 'Ä':
                        latex.append("\\\"{A}");
                        break;
                    case 'ä':
                        latex.append("\\\"{a}");
                        break;
                    case 'Ö':
                        latex.append("\\\"{O}");
                        break;
                    case 'ö':
                        latex.append("\\\"{o}");
                        break;
                    case 'Å':
                        latex.append("\\\"{AA}");
                        break;
                    case 'å':
                        latex.append("\\\"{a}");
                        break;
                    default:
                        latex.append(string.charAt(i));
                }
            }
    }

}
