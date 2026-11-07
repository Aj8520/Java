package c02_access_modifiers;

import c02_access_modifiers.outside.MovedAwayChild;
import c02_access_modifiers.outside.Stranger;

/**
 * 01.02 · Access modifiers
 * Handbook: OOP, Exceptions & Modern Java (oop.html#access)
 *
 * Run: mvn -q -pl 01-oop-exceptions compile exec:java -Dexec.mainClass=c02_access_modifiers.Practice
 *
 * To see a compile error yourself, uncomment any line marked "compile error".
 */
public class Practice {

    public static void main(String[] args) {
        System.out.println("== Access modifiers ==");

        coreExample();
        predictTheOutput();
        realLifeExample();
        interviewQuestion();
    }

    // TODO 1 (done): core example. Who can see each member of Household?
    static void coreExample() {
        System.out.println("\n-- 1. Who can see what --");
        Household home = new Household();

        // Practice is in the SAME package as Household: everything except private.
        System.out.println("same package sees public:          " + home.noticeBoard);
        System.out.println("same package sees protected:       " + home.familyRecipe);
        System.out.println("same package sees package-private: " + home.fridgeCode);
        // System.out.println(home.diary); // compile error: diary has private access
        System.out.println("private, through a public method:  " + home.readDiary());

        MovedAwayChild child = new MovedAwayChild();
        child.showWhatICanSee();
        child.visit(home);
        child.visitSibling(new MovedAwayChild());

        new Stranger().showWhatICanSee(home);
    }

    // TODO 2 (done): predict each line BEFORE running.
    static void predictTheOutput() {
        System.out.println("\n-- 2. Predict --");
        Household a = new Household();
        Household b = new Household();

        // Prediction 1: can one object read another object's private field?
        System.out.println("same diary? " + a.hasSameDiaryAs(b));

        // Prediction 2: the one thing changed is b's diary.
        b.writeDiary("Secret plans");
        System.out.println("same diary after writing in b? " + a.hasSameDiaryAs(b));

        // Prediction 3: MovedAwayChild is in another package. Does this line compile?
        // It does: familyRecipe is declared in Household, and Practice shares Household's package.
        MovedAwayChild child = new MovedAwayChild();
        System.out.println("recipe through a child object: " + child.familyRecipe);
    }

    // TODO 3 (done): my own real-life example.
    static void realLifeExample() {
        System.out.println("\n-- 3. Bank account --");
        BankAccount account = new BankAccount();
        account.deposit(500);
        // account.balance = -1000; // compile error: private stops the invalid state

        System.out.println("withdraw 800 allowed? " + account.withdraw(800));
        System.out.println("withdraw 200 allowed? " + account.withdraw(200));
        System.out.println("balance: " + account.getBalance());

        try {
            account.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("rejected: " + e.getMessage());
        }
    }

    // TODO 4 (done): interview question as code.
    // Q: Can an override change the access level? Can a private method be overridden?
    static class Parent {
        protected String role() {
            return "parent role";
        }

        private String secret() {
            return "parent secret";
        }

        String revealSecret() {
            return secret();
        }
    }

    static class Child extends Parent {
        @Override
        public String role() { // protected -> public: widening is allowed
            // Try it: change "public" above to "private". Compile error: weaker access.
            return "child role";
        }

        // Not an override: Parent.secret() is private, so this is a brand-new method.
        // Adding @Override here is a compile error.
        String secret() {
            return "child secret";
        }
    }

    static void interviewQuestion() {
        System.out.println("\n-- 4. Interview question --");
        Parent p = new Child();
        System.out.println("overridden method: " + p.role());
        System.out.println("private method:    " + p.revealSecret());
    }
}
