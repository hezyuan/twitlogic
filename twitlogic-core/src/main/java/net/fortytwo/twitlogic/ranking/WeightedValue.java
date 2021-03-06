package net.fortytwo.twitlogic.ranking;

/**
 * @author Joshua Shinavier (http://fortytwo.net).
 */
public class WeightedValue<T> implements Comparable<WeightedValue<T>> {
    public double weight;
    public T value;

    public WeightedValue() {
    }

    public WeightedValue(final T value, final double weight) {
        this.value = value;
        this.weight = weight;
    }

    public int compareTo(final WeightedValue<T> other) {
        return weight < other.weight
                ? -1
                : weight > other.weight
                ? 1 : 0;
    }

    public String toString() {
        return "WeightedValue(" + weight + ", " + value + ")";
    }
}
