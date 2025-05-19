package ed.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E02AutocompleteSystem {

    private class Sentence {
        String sentence;
        int times;

        Sentence(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
    }

    private Map<String, Integer> sentenceTimesMap;
    private StringBuilder currentSentence;

    public E02AutocompleteSystem(String[] sentences, int[] times) {
        sentenceTimesMap = new HashMap<>();
        currentSentence = new StringBuilder();

        for (int i = 0; i < sentences.length; i++) {
            sentenceTimesMap.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String sentence = currentSentence.toString();
            sentenceTimesMap.put(sentence, sentenceTimesMap.getOrDefault(sentence, 0) + 1);
            currentSentence = new StringBuilder();
            return Collections.emptyList();
        }

        currentSentence.append(c);
        String prefix = currentSentence.toString();

        List<Sentence> candidates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sentenceTimesMap.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                candidates.add(new Sentence(entry.getKey(), entry.getValue()));
            }
        }

        Collections.sort(candidates, new Comparator<Sentence>() {
            @Override
            public int compare(Sentence s1, Sentence s2) {
                if (s1.times != s2.times) {
                    return Integer.compare(s2.times, s1.times);
                } else {
                    return s1.sentence.compareTo(s2.sentence);
                }
            }
        });

        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(3, candidates.size()); i++) {
            result.add(candidates.get(i).sentence);
        }

        return result;
    }
}
