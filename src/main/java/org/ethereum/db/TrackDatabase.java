package org.ethereum.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * www.ethereumJ.com
 *
 * @author: Roman Mandeleil
 * Created on: 11/06/2014 14:09
 */
public class TrackDatabase implements Database {

    private Database db;

    private boolean trackingChanges;
    private Map<ByteArrayWrapper, byte[]> changes;
    private List<ByteArrayWrapper> deletes;

    public TrackDatabase(Database db) {
        this.db = db;
    }

    public void startTrack() {
        changes = new HashMap<>();
        deletes = new ArrayList<>();
        trackingChanges = true;
    }

    public void commitTrack() {
        for(ByteArrayWrapper key : changes.keySet()) {
            db.put(key.getData(), changes.get(key));
        }
        for(ByteArrayWrapper key : deletes) {
            db.delete(key.getData());
        }
        changes = null;
        trackingChanges = false;
    }

    public void rollbackTrack() {
        changes = new HashMap<>();
        deletes = new ArrayList<>();
        changes = null;
        trackingChanges = false;
    }

    public void put(byte[] key, byte[] value) {
        if (trackingChanges) {
        	ByteArrayWrapper wKey = new ByteArrayWrapper(key);
			changes.put(wKey, value);
        } else {
            db.put(key, value);
        }
    }

    public byte[] get(byte[] key) {
    	if(trackingChanges) {
    		ByteArrayWrapper wKey = new ByteArrayWrapper(key);
            if (deletes.contains(wKey)) return null;
            if (changes.get(wKey) != null) return changes.get(wKey);
        }
       	return db.get(key);
    }

    /** Delete object (key) from db **/
    public void delete(byte[] key) {
        if (trackingChanges) {
            ByteArrayWrapper wKey = new ByteArrayWrapper(key);
            deletes.add(wKey);
        } else {
            db.delete(key);
        }
    }

    @Override
    public void close(){
        db.close();
    }
}
