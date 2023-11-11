package remove;
import safar.basic.morphology.stemmer.factory.StemmerFactory;
import safar.basic.morphology.stemmer.interfaces.IStemmer;
import safar.basic.morphology.stemmer.model.WordStemmerAnalysis;
import safar.util.remover.Remover;

import java.util.List;


public class Stemmer {
    public static StringBuilder stemText(StringBuilder text) {
        IStemmer stemmer = StemmerFactory.getKhojaImplementation();
        String txt=text.toString();
        txt = Remover.removeStopWords(txt);
        List<WordStemmerAnalysis> listResult = stemmer.stem(txt); 
        StringBuilder stemmedText = new StringBuilder();
        for (WordStemmerAnalysis wsa : listResult) {
            String stem = wsa.getListStemmerAnalysis().get(0).getMorpheme();
            if (stem != null & stem != "ii"){
             stemmedText.append(stem).append(" ");
            }
        }
        // Retournez le StringBuilder sans le convertir en chaîne de caractères
        return stemmedText;
    }
}
