public class Block {
    private final long minerId;
    private final int blockId;

    private final long timestamp;
    private final int nonce;
    private final String previousHash;
    private final String hash;
    private final long creationTime;

    private int amountOfZeroes;

    public Block(long minerId, int blockId, long timestamp, int nonce,
                 String previousHash, String hash,
                 long creationTime) {
        this.minerId = minerId;
        this.blockId = blockId;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.previousHash = previousHash;
        this.hash = hash;
        this.creationTime = creationTime;
    }

    public long getMinerId() {
        return minerId;
    }

    public int getBlockId() {
        return blockId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public int getAmountOfZeroes() {
        return amountOfZeroes;
    }

    public void setAmountOfZeroes(int amountOfZeroes) {
        this.amountOfZeroes = amountOfZeroes;
    }

    @Override
    public String toString() {
        String complexityPrint;
        if (amountOfZeroes > 0) {
            complexityPrint = "N was increased to " + amountOfZeroes;
        } else if (amountOfZeroes < 0) {
            complexityPrint = "N was decreased by 1";
        } else {
            complexityPrint = "N stays the same";
        }
        return "Block:" +
                "\nCreated by miner # " + minerId +
                "\nId: " + blockId +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + nonce +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + hash +
                "\nBlock was generating for " + creationTime + " seconds" +
                "\n" + complexityPrint + "\n\n";
    }
}
