package turing.util.constant;

import java.util.regex.Pattern;

public class Constants {

    public static final char BLANK = '_';
    public static final char STATE = 'q';
    public static final char ARROW_START = '-';
    public static final char ARROW_END = '>';
    public static final char RIGHT = 'R';
    public static final char LEFT = 'L';
    public static final char NONE = 'N';
    public static final Pattern PATTERN = Pattern.compile("^("
            + Constants.STATE + ")(\\d+)(.)(\\"
            + Constants.ARROW_START + ")(\\"
            + Constants.ARROW_END + ")("
            + Constants.STATE + ")(\\d+)(.)("
            + Constants.RIGHT + "|"
            + Constants.LEFT+ "|"
            + Constants.NONE
            + ")$");

}
