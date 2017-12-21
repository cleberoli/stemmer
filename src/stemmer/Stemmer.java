package stemmer;

public abstract class Stemmer {
    public abstract boolean stem();
    public abstract void setCurrent(String value);
    public abstract String getCurrent();

    protected abstract boolean isVowel(char c);
    protected abstract String prelude();
    protected abstract void markRegions();
    protected abstract String postlude();
}
