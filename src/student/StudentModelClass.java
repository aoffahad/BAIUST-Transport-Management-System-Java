package student;

public class StudentModelClass {

    String name, dept, level, term, type, pass;
    int id, busFee;
    public StudentModelClass(){
        
    }
    public StudentModelClass(int id, String name, String dept, String level, String term, String type, int busFee, String pass){
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.level = level;
        this.term = term;
        this.type = type;
        this.busFee = busFee;
        this.pass = pass;
    }
    public StudentModelClass(int id, String name, String dept, String level, String term, String type, int busFee){
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.level = level;
        this.term = term;
        this.type = type;
        this.busFee = busFee;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

   

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusFee(int busFee) {
        this.busFee = busFee;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getLevel() {
        return level;
    }

    public String getTerm() {
        return term;
    }

    public String getType() {
        return type;
    }

    public int getBusFee() {
        return busFee;
    }
    public int getId(){
        return id;
    }
    
}
