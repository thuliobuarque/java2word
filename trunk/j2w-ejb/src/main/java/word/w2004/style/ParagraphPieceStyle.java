package word.w2004.style;

import word.api.interfaces.ISuperStylin;
import word.w2004.elements.ParagraphPiece;
import word.w2004.style.Font;

/**
 * @author anyone
 *
 *         Use this class in order to apply specifics style to paragraph. Eg.:
 *         one word in bold, other in italic.
 *
 */
public class ParagraphPieceStyle extends AbstractStyle implements ISuperStylin {

    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;
    private String textColor = "";
    private Color color;
    public Font font;
    private String fontSize = "";
    private String bgColor = "";


    @Override
    public String getNewContentWithStyle(String txt) {
        StringBuilder style = new StringBuilder("");

        // 'doStyleFont' has to be before 'doStyleBold' and 'doStyleItalic'
        // because of the 'smart bold/italic' based on font type.
        doStyleFont(style);
        doStyleBold(style);
        doStyleItalic(style);
        doStyleUnderline(style);
        doStyleTextColorHexa(style);
        doStyleColorEnum(style);
        doStyleFontSize(style);
        doStyleBgColor(style);

        return doStyleReplacement(style, txt);
    }

    private void doStyleBgColor(StringBuilder style) {
        if (!bgColor.equals("")) {
            style.append("\n            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\""+ bgColor +"\" />\n");
        }
    }

    private void doStyleBold(StringBuilder style) {
        if (bold) {
            style.append("\n            	<w:b/>");
        }
    }

    private void doStyleItalic(StringBuilder style) {
        if (italic) {
            style.append("\n            	<w:i/>");
        }
    }

    private void doStyleUnderline(StringBuilder style) {
        if (underline) {
            style.append("\n			<w:u w:val=\"single\"/>");
        }
    }

    private void doStyleTextColorHexa(StringBuilder style) {
        if (!textColor.equals("")) {
            style.append("\n			<w:color w:val=\"" + textColor + "\"/>");
        }
    }

    private void doStyleColorEnum(StringBuilder style) {
        if (color != null && !color.getValue().equals("")) {
            style.append("\n			<w:color w:val=\"" + color.getValue() + "\"/>");
        }
    }

    private void doStyleFont(StringBuilder style) {
        // Smart Italic/Bold: This will make the font bold/italic according to
        // this.font
        String fontName = "";
        if (font != null) {
            fontName = font.getValue();
            if (fontName.contains("Bold")) {
                bold = true;
            } else {
                //if is manually 'bold', I also change the font name
                if (bold) {
                    fontName += " Bold";
                }
            }

            if (fontName.contains("Italic")) {
                italic = true;
            } else {
                if (italic) {
                    fontName += " Italic";
                }
            }
        }

        if (font != null) {
            style.append("\n			<w:rFonts w:ascii=\"" + fontName + "\" w:h-ansi=\"" + fontName + "\"/>\n");
            style.append("\n			<wx:font wx:val=\"" + fontName + "\"/>");
        }
    }

    private void doStyleFontSize(StringBuilder style) {
        if (!"".equals(fontSize)) {
            String ffsize = "\n               <w:sz w:val=\"" + fontSize
                    + "\" />\n";
            ffsize += "\n               <w:sz-cs w:val=\"" + fontSize
                    + "\" />\n";
            style.append(ffsize);
        }
    }

    private String doStyleReplacement(StringBuilder style, String txt) {
        if (!"".equals(style.toString())) {
            style.insert(0, "\n	 <w:rPr>");
            style.append("\n	 </w:rPr>");
            txt = txt.replace("{styleText}", style.toString());// Convention:
                                                               // apply styles
        }
        // Convention: replace unused styles after...
        txt = txt.replaceAll("[{]style(.*)[}]", "");
        return txt;
    }

    // ### Getters and setters... ###

    /**
     *
     * This is the ParagraphPiece! I am using Covariant Return Type!!! to be
     * honest, I have never thought how to use and finally here we go!!! It will
     * give the chance to eliminate the necessity of type cast for elements.
     *
     */
    @Override
    public ParagraphPiece create() {
        return (ParagraphPiece) super.create();
    }

    public ParagraphPieceStyle setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public ParagraphPieceStyle setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public ParagraphPieceStyle setUnderline(boolean underline) {
        this.underline = underline;
        return this;
    }

    /**
     * If you know the color code, just to straight to the point! Eg.: yellow:
     * FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
     *
     * If you want, you can use the class Color.whatever_color.
     *
     * @param hexadecimal
     *            color code
     */
    public ParagraphPieceStyle setTextColor(String textColor) {
        this.textColor = textColor;
        return this;
    }

    /**
     * If you know the color code, just to straight to the point! Eg.: yellow:
     * FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
     *
     * If you want, you can use the class Color.whatever_color.
     *
     * @param hexadecimal
     *            color code
     */
    public ParagraphPieceStyle setBgColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public ParagraphPieceStyle setTextColor(Color color) {
        this.color = color;
        return this;
    }

    public ParagraphPieceStyle setFont(Font font) {
        this.font = font;
        return this;
    }

    /***
     * It should be the double of you would normally do in the MS Word. Eg.:
     * Default fontsize is 12. If you want to do it via Java2word, specify 24. 
     */
    public ParagraphPieceStyle setFontSize(String fontSize) {
        this.fontSize = fontSize;
        return this;
    }

}
