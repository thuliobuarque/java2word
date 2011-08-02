package word.w2004.style;

import word.api.interfaces.ISuperStylin;


/**
 * @author anyone
 *
 * Use this class to apply style to the Paragraph - the whole paragraph. At the moment is only possible to apply Align.
 * If you want to apply bold, italic or underline, use a ParagraphPiece to do that.
 *
 */
public class ParagraphStyle extends AbstractStyle implements ISuperStylin{

    private Align align = Align.LEFT;
    private String bgColor = "";
    private String bidi = "";

    public enum Align {
        CENTER("center"), LEFT("left"), RIGHT("right"), JUSTIFIED("both");
        private String value;

        Align(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public String getNewContentWithStyle(String txt) {
        StringBuilder style = new StringBuilder("");

        //There will be always align 'Left' by default
        doStyleAlignment(style);
        doStyleBgColor(style);
        doStyleBidi(style);
        
        return doStyleReplacement(style, txt);
    }


    private void doStyleBidi(StringBuilder style) {
        if (!this.bidi.equals("")) style.append("<w:lang w:bidi=\"" + this.bidi + "\" />");
    }

    private void doStyleAlignment(StringBuilder style) {
        style.append("  <w:jc w:val=\"" + align.getValue()+ "\"/> \n    " + "       {styleText}\n   ");
    }

    private void doStyleBgColor(StringBuilder style) {
        if (!bgColor.equals("")) {
            style.append("\n            <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\""+ bgColor +"\" />\n");
        }
    }

    private String doStyleReplacement(StringBuilder style, String txt) {

       // if (!"".equals(style.toString())) {
            style.insert(0, "\n  <w:pPr>");
            style.append("\n     </w:pPr>");
            txt = txt.replace("{styleText}", style.toString());// Convention:
                                                               // apply styles
       // }

        //txt = txt.replace("{styleText}", styleValue);
        txt = txt.replaceAll("[{]style(.*)[}]", ""); //Convention: replace unused styles after...
        return txt;
    }


    //### Getters and setters... ###

    /**
     * If you know the color code, just to straight to the point! Eg.: yellow:
     * FFFF00, black: 000000, red: FF0000, blue: 0000FF, green: 008000, etc...
     *
     * If you want, you can use the class Color.whatever_color.
     *
     * @param hexadecimal
     *            color code
     */
    public ParagraphStyle bgColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }
    
    /**
     * Sets align for the whole paragraph
     * @param align
     * @return
     */
    public ParagraphStyle align(Align align) {
        this.align = align;
        return this;
    }
    
    /**
     * Use this to specify special characters. Eg.: Hebreus, use HE
     * @param style
     */
    public ParagraphStyle bidi(String bidi) {
        this.bidi = bidi;
        return this;
    }    

}
