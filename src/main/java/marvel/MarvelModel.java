package marvel;
import com.opencsv.bean.CsvBindByName;


public class MarvelModel {
    //Abstraction Function:
    //  hero = name of the hero
    //  book = book that hero appears in
    //
    //Representation Invariant:
    //  this != null;
    //  hero != null;
    //  book != null;

    @CsvBindByName
    private String hero;

    @CsvBindByName
    private String book;

    public String getHero(){
        return hero;
    }

    public void setHero(String h){
        this.hero = h;
    }

    public String getBook(){
        return this.book;
    }

    public void setBook(String b){
        this.book = b;
    }
}
