package entity;

public class TypologicalGroup {
    private int idTypologicalGroup;
    private int test_boyko;
    private int test_gage;
    private int client;
    private int group;
    private int subgroup;

    public int getIdTypologicalGroup() {
        return idTypologicalGroup;
    }

    public void setIdTypologicalGroup(int idTypologicalGroup) {
        this.idTypologicalGroup = idTypologicalGroup;
    }

    public int getTest_boyko(){
        return test_boyko;
    }

    public void setTest_boyko(int test_boyko){
        this.test_boyko=test_boyko;
    }

    public int getTest_gage(){
        return test_gage;
    }

    public void setTest_gage(int test_gage){
        this.test_gage=test_gage;
    }

    public int getClient(){
        return client;
    }

    public void setClient(int client){
        this.client=client;
    }

    public int getGroup(){
        return group;
    }

    public void setGroup(int group){
        this.group=group;
    }

    public int getSubgroup(){
        return subgroup;
    }

    public void setSubgroup(int subgroup){
        this.subgroup=subgroup;
    }
}
