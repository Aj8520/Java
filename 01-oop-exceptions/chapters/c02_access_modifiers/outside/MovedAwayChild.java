package c02_access_modifiers.outside;

import c02_access_modifiers.Household;

/** Different package, but a subclass: "your children who moved away". */
public class MovedAwayChild extends Household {

    public void showWhatICanSee() {
        System.out.println("child sees public:    " + noticeBoard);
        System.out.println("child sees protected: " + familyRecipe); // inherited, so this works
        // System.out.println(fridgeCode); // compile error: package-private, different package
        // System.out.println(diary);      // compile error: private
    }

    public void visit(Household parent) {
        System.out.println("child, via a Household reference: " + parent.noticeBoard);
        // System.out.println(parent.familyRecipe); // compile error: outside the package, protected
        //                                          // works only through MovedAwayChild, not Household
    }

    public void visitSibling(MovedAwayChild sibling) {
        System.out.println("child, via a MovedAwayChild reference: " + sibling.familyRecipe);
    }
}
