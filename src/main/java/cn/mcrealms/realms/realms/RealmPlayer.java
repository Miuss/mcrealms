package cn.mcrealms.realms.realms;

import java.util.ArrayList;
import java.util.HashMap;

public class RealmPlayer {
    public static HashMap<String, RealmPlayer> realmPlayer = new HashMap<>();
    public static HashMap<String, RealmPlayer> realmPlayername = new HashMap<>();
    public ArrayList<String> voteduuid = new ArrayList<>();
    private Realm owned;
    private ArrayList<Realm> allrealm = new ArrayList<>();
    private String name;
    private String stringuuid;

    public RealmPlayer(String uuid,String name) {
        realmPlayer.put(uuid, this);
        realmPlayername.put(name.toLowerCase(),this);
        this.name = name;
        this.stringuuid = uuid;
    }

    public Realm getOwned() {
        return owned;
    }

    public void setOwned(Realm owned) {
        this.owned = owned;
    }
    public static RealmPlayer getPlayer(String uuid){
        return realmPlayer.get(uuid);
    }
    public static RealmPlayer getPlayerFromName(String name){
        return realmPlayername.get(name.toLowerCase());
    }
    public String getUniqueId(){
        return stringuuid;
    }
    public ArrayList<Realm> getAllRealm() {
        return allrealm;
    }
    public void addRealm(Realm realm){
        allrealm.add(realm);
    }
    public void remove(Realm realm){
        allrealm.remove(realm);
    }
    public String getName() {
        return name;
    }
    public void addRealmVoted(Realm r){
        voteduuid.add(r.getOwner().getUniqueId());
    }
    public void setName(String name) {
        this.name = name;
    }

    private void useless()
    {
        ArrayList<String> strs = new ArrayList<>();
        strs.forEach(System.out::println);
    }
}
