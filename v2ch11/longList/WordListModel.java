package longList;

import javax.swing.AbstractListModel;

/**
 * A model that dynamically generates n-letter words.
 */
public class WordListModel extends AbstractListModel<String> {
    private int length;
    public static final char FIRST = 'a';
    public static final char LAST = 'z';

    /**
     * Constructs the model.
     * @param n the word length
     */
    public WordListModel(int n) {
        length = n;
    }

    @Override
    public int getSize() {
        return (int) Math.pow(LAST - FIRST + 1, length);
    }

    @Override
    public String getElementAt(int n) {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = (char) (FIRST + n % (LAST - FIRST + 1));
            r.insert(0, c);
            n /= LAST - FIRST + 1;
        }
        return r.toString();
    }
}
