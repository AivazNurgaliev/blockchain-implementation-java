import java.util.Date;

public class Miner extends Thread{

    public void mineBlock(Blockchain blockchain) {

        Block block = blockchain.getLastBlock();

        int blockId;
        long timestamp = new Date().getTime();
        String previousHash;
        String hash;

        if (block == null) {
            blockId = 1;
            previousHash = "0";
        } else {
            blockId = block.getBlockId() + 1;
            previousHash = block.getHash();
        }

        String zeroes = "0".repeat(blockchain.getZeroesToStartHash());
        int nonce = 0;

        do {
            nonce = (int) (Math.random() * 1000000);
            hash = HashGeneratorUtil.doSha256(blockId + timestamp + nonce + previousHash);
        } while(!hash.startsWith(zeroes));

        long creationTime = (new Date().getTime() - timestamp) / 1000;

        long minerId = Thread.currentThread().getId();
        boolean isSuccessfullyAdded = blockchain.addBlock(
                new Block(minerId, blockId, timestamp, nonce, previousHash, hash, creationTime)
        );
        if (!isSuccessfullyAdded) {
            mineBlock(blockchain);
        }
    }
}
