import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class plaintextToHtmlConverter {
    String source;
    List<String> result;
    List<String> convertedLine;
    String characterToConvert;

    public String toHtml() throws Exception{
        return basicHtmlEncode(read());
    }
    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get("sample.txt")));
    }

    private String basicHtmlEncode(String source) {
        this.source = source;
        char characterToConvert;
        result = new ArrayList<>();
        convertedLine = new ArrayList<>();

        for(int i =0;i<this.source.length();i++) {

            characterToConvert = source.charAt(i);

           if(characterToConvert == '<'){
               pushACharacterToTheOutput("&lt;");
               break;
           }
           else if (characterToConvert == '>') {
               pushACharacterToTheOutput("&gt");
               break;
           } else if (characterToConvert == '&') {
               pushACharacterToTheOutput("&amp");
               break;
           } else if (characterToConvert == '\n') {
               addANewLine();
               break;
           }
           else {
               pushACharacterToTheOutput(convertedLine.toString());
               break;
           }

        }

        addANewLine();
        return String.join("<br />", result);
    }

    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }

    private void pushACharacterToTheOutput(String character) {
        convertedLine.add(characterToConvert);
    }
}
