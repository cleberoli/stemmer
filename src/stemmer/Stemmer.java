package stemmer;

public abstract class Stemmer {
    public abstract boolean stem();
    public abstract void setCurrent(String value);
    public abstract String getCurrent();
}
