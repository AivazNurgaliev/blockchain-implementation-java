import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private volatile int zeroesToStartHash;

    private final List<Block> blockList = new ArrayList<>();

    public int getZeroesToStartHash() {
        return zeroesToStartHash;
    }

    public Block getLastBlock() {
        return blockList.isEmpty() ? null : blockList.get(blockList.size() - 1);
    }

    public synchronized boolean addBlock(Block block) {
        String zeroes = "0".repeat(zeroesToStartHash);
        String previousHash = block.getPreviousHash();
        String hash = block.getHash();

        boolean isBlockValid = hash.startsWith(zeroes) &&
                previousHash.equals(blockList.isEmpty() ? "0" : getLastBlock().getHash())
                && HashGeneratorUtil.doSha256(block.getBlockId() +
                block.getTimestamp() + block.getNonce()
                + block.getPreviousHash()).equals(hash);

        if (isBlockValid) {
            long timeToGenerate = block.getCreationTime();
            if (timeToGenerate < 10000) {
                zeroesToStartHash++;
                block.setAmountOfZeroes(zeroesToStartHash);
            } else if (timeToGenerate > 60000) {
                zeroesToStartHash--;
                block.setAmountOfZeroes(zeroesToStartHash);
            }
            blockList.add(block);
        }

        return isBlockValid;
    }

    @Override
    public String toString() {
        String resultString = "";
        for (Block block : blockList) {
            resultString += block.toString();
        }

        return resultString;
    }
}