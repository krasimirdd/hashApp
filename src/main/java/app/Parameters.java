package app;

/**
 * The parameters defining the standard FIPS 202.
 *
 * @author romus
 */
public enum Parameters {

    SHA3_512(576, 0x06, 512);

    private final int rate;

    /**
     * Delimited suffix.
     */
    public final int d;

    /**
     * Output length (bits).
     */
    public final int outputLen;

    Parameters(int rate, int d, int outputLen) {
        this.rate = rate;
        this.d = d;
        this.outputLen = outputLen;
    }

    public int getRate() {
        return rate;
    }

    public int getD() {
        return d;
    }

    public int getOutputLen() {
        return outputLen;
    }
}
