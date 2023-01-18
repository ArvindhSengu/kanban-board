class TaskDetails{
    public final String blank = "";
    private String name;
    private String description;
    private String urgency;
    private String category;
    TaskDetails(String aName, String aDescr, String aUrgency, String aCat){
        name = aName;
        description = aDescr;
        urgency = aUrgency;
        category = aCat;
    }
    TaskDetails(){
        name =  blank;
        description = blank;
        urgency = blank;
        category = blank;
    }
    //array size must be larger than 4
    public void setTask(String[] aString) throws Exception{
        if(aString.length < 4){
            System.err.println("at least 4 arguments are required");
            throw new Exception("bad formatting");
        } else{
            name = aString[0];
            description = aString[1];
            urgency = aString[2];
            category = aString[3];
        }
    }
    public void updateName(String newName){
        name = newName;
    }
    public void updateDescr(String newDescr){
        description = newDescr;
    }
    public void updateUrgency(String newUrg){
        urgency = newUrg;
    }
    public void updateCat(String newCat){
        category = newCat;
    }
    public String getName(){
        return name;
    }
    public String getDescr(){
        return description;
    }
    public String getUrg(){
        return urgency;
    }
    public String getCat(){
        return category;
    }
    public String toString(){
        return name + "|" + description + "|" + urgency + "|" + category;
    }
}
