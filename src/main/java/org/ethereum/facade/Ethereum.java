package org.ethereum.facade;

import org.ethereum.core.Block;
import org.ethereum.listener.EthereumListener;
import org.ethereum.net.client.PeerData;

import java.net.InetAddress;

/**
 * www.ethereumJ.com
 *
 * @author: Roman Mandeleil
 * Created on: 27/07/2014 09:11
 */

public interface Ethereum {

    public PeerData findPeer(PeerData peerData);
    public PeerData findPeer();

    public void stopPeerDiscover();

    public void connect(InetAddress addr, int port);
    public void connect(String ip, int port);

    public Block getBlockByIndex(long index);

    public long getBlockChainSize();

    public void addListener(EthereumListener listener);

}
