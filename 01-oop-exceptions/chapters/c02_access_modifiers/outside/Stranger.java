package c02_access_modifiers.outside;

import c02_access_modifiers.Household;

/** Different package and not a subclass: only public members are visible. */
public class Stranger {

    public void showWhatICanSee(Household home) {
        System.out.println("stranger sees public: " + home.noticeBoard);
        // System.out.println(home.familyRecipe); // compile error: protected
        // System.out.println(home.fridgeCode);   // compile error: package-private
        // System.out.println(home.diary);        // compile error: private
    }
}
