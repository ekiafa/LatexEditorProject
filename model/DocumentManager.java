package model;
import java.util.*;
import java.util.HashMap;

public class DocumentManager {
	private HashMap<String, Document> templates;
	private String [] templateNames= {"emptyTemplate","articleTemplate","bookTemplate","letterTemplate","reportTemplate","emptyTemplate"};
	private String [] templateContents= {"","\\documentclass[11pt,twocolumn,a4paper]{article}\n" + 
			"\n" + 
			"\\begin{document}\n" + 
			"\\title{Article: How to Structure a LaTeX Document}\n" + 
			"\\author{Author1 \\and Author2 \\and ...}\n" + 
			"\\date{\\today}\n" + 
			"\n" + 
			"\\maketitle\n" + 
			"\n" + 
			"\\section{Section Title 1}\n" + 
			"\n" + 
			"\\section{Section Title 2}\n" + 
			"\n" + 
			"\\section{Section Title.....}\n" + 
			"\n" + 
			"\\section{Conclusion}\n" + 
			"\n" + 
			"\\section*{References}\n" + 
			"\n" + 
			"\\end{document}","\\documentclass[11pt,a4paper]{book}\n" + 
					"\n" + 
					"\\begin{document}\n" + 
					"\\title{Book: How to Structure a LaTeX Document}\n" + 
					"\\author{Author1 \\and Author2 \\and ...}\n" + 
					"\\date{\\today}\n" + 
					"\n" + 
					"\\maketitle\n" + 
					"\n" + 
					"\\frontmatter\n" + 
					"\n" + 
					"\\chapter{Preface}\n" + 
					"% ...\n" + 
					"\n" + 
					"\\mainmatter\n" + 
					"\\chapter{First chapter}\n" + 
					"\\section{Section Title 1}\n" + 
					"\\section{Section Title 2}\n" + 
					"\\section{Section Title.....}\n" + 
					"\n" + 
					"\\chapter{....}\n" + 
					"\n" + 
					"\\chapter{Conclusion}\n" + 
					"\n" + 
					"\\chapter*{References}\n" + 
					"\n" + 
					"\n" + 
					"\\backmatter\n" + 
					"\\chapter{Last note}\n" + 
					"\n" + 
					"\\end{document}","\\documentclass{letter}\n" + 
							"\\usepackage{hyperref}\n" + 
							"\\signature{Sender's Name}\n" + 
							"\\address{Sender's address...}\n" + 
							"\\begin{document}\n" + 
							"\n" + 
							"\\begin{letter}{Destination address....}\n" + 
							"\\opening{Dear Sir or Madam:}\n" + 
							"\n" + 
							"I am writing to you .......\n" + 
							"\n" + 
							"\n" + 
							"\\closing{Yours Faithfully,}\n" + 
							"\n" + 
							"\\ps\n" + 
							"\n" + 
							"P.S. text .....\n" + 
							"\n" + 
							"\\encl{Copyright permission form}\n" + 
							"\n" + 
							"\\end{letter}\n" + 
							"\\end{document}","\\documentclass[11pt,a4paper]{report}\n" + 
									"\n" + 
									"\\begin{document}\n" + 
									"\\title{Report Template: How to Structure a LaTeX Document}\n" + 
									"\\author{Author1 \\and Author2 \\and ...}\n" + 
									"\\date{\\today}\n" + 
									"\\maketitle\n" + 
									"\n" + 
									"\\begin{abstract}\n" + 
									"Your abstract goes here...\n" + 
									"...\n" + 
									"\\end{abstract}\n" + 
									"\n" + 
									"\\chapter{Introduction}\n" + 
									"\\section{Section Title 1}\n" + 
									"\\section{Section Title 2}\n" + 
									"\\section{Section Title.....}\n" + 
									"\n" + 
									"\\chapter{....}\n" + 
									"\n" + 
									"\\chapter{Conclusion}\n" + 
									"\n" + 
									"\n" + 
									"\\chapter*{References}\n" + 
									"\n" + 
									"\\end{document}"};
	
	
	public DocumentManager() {
		templates = new HashMap<String, Document>();
		for(int i=0;i<5;i++) {
			Document document = new Document();
			document.setContents(getContents(templateNames[i]));
			templates.put(templateNames[i], document);	
		}

	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
	public String getContents(String type) {
		String template = null;
		for(int i=0; i<5;i++) {
			
				if(type.equals(templateNames[i])){
					template=templateContents[i];
				}
			}		
		return template;
	}
	
	
	//---atBash


    public String encode(String plaintext)
    {
        String ciphertext = "";
        plaintext = removeUnwantedChars(plaintext.toLowerCase());
        for(char c : plaintext.toCharArray())
        {
            if(Character.isLetter(c))
            {
                ciphertext += (char) ('a' + ('z' - c));
            }
            else
            {
                ciphertext += c;
            }
        }
        return getSubStrings(ciphertext).trim();
    }

    public String decode(String ciphertext)
    {
        String plaintext = "";
        ciphertext = removeUnwantedChars(ciphertext.toLowerCase());
        for(char c : ciphertext.toCharArray())
        {
            if(Character.isLetter(c))
            {
                plaintext += (char) ('z' + ('a' - c));
            }
            else
            {
                plaintext += c;
            }
        }
        return plaintext;
    }

    private String getSubStrings(String input)
    {
        String out = "";
        for(int i = 0; i < input.length(); i += 5)
        {
            if(i + 5 <= input.length())
            {
                out += (input.substring(i, i + 5) + " ");
            }
            else
            {
                out += (input.substring(i) + " ");
            }
        }
        return out;
    }

    private String removeUnwantedChars(String input)
    {
        String out = "";
        for(char c : input.toCharArray())
        {
            if(Character.isLetterOrDigit(c))
            {
                out += c;
            }
        }
        return out;
    }
    
    
    //-----rot13
    
    public String rot13(String value) {

        char[] values = value.toCharArray();
        for (int i = 0; i < values.length; i++) {
            char letter = values[i];

            if (letter >= 'a' && letter <= 'z') {
                // Rotate lowercase letters.

                if (letter > 'm') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            } else if (letter >= 'A' && letter <= 'Z') {
                // Rotate uppercase letters.

                if (letter > 'M') {
                    letter -= 13;
                } else {
                    letter += 13;
                }
            }
            values[i] = letter;
        }
        // Convert array to a new String.
        return new String(values);
    }
    
  
}
