package c02_access_modifiers;

/**
 * The handbook's analogy as code: one member for each access level.
 * Each comment says who can see that member.
 */
public class Household {

    private String diary = "Dear diary...";              // this class only
    String fridgeCode = "1234";                          // same package only (no keyword)
    protected String familyRecipe = "Grandma's dal";     // same package + subclasses anywhere
    public String noticeBoard = "Garage sale on Sunday"; // everyone

    /** Private data can still be shared on purpose, through a public method. */
    public String readDiary() {
        return diary;
    }

    /** Private means "private to the class", not "private to the object". */
    public boolean hasSameDiaryAs(Household other) {
        return diary.equals(other.diary); // reads ANOTHER object's private field
    }

    public void writeDiary(String text) {
        diary = text;
    }
}
