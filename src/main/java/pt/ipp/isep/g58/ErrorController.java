package pt.ipp.isep.g58;

import java.util.ArrayList;

public class ErrorController {
    static final ArrayList<String> allErrors = new ArrayList<String>() {{
        add("Password is too short, please enter a 6+ length password");
        add("Password has not enough lower letters, please enter 1+ lower case password");
        add("Password has not enough upper letters, please enter 1+ upper case password");
        add("Password has not enough special characters, please enter 1+ special character \n" +
                "Example of special characters: !@#$%&*()'+,-./:;<=>?[]^_`{|}");
        add("Invalid SNS Number");
        add(""); //for further errors idk
    }};

    public void errorInterpretation(int errorCode) {
        int i = 0;
        while (errorCode != 0) {
            if ((errorCode & 1) != 0) { //means we have an error at i position
                System.out.println(allErrors.get(i));
                i++;
                errorCode = errorCode >> 1;
            }
        }
    }
}
