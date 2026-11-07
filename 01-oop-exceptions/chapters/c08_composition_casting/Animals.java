package c08_composition_casting;

class Animal {
    String sound() { return "..."; }
}

class Dog extends Animal {
    private final String name;
    private final boolean trained;

    Dog(String name, boolean trained) {
        this.name = name;
        this.trained = trained;
    }

    @Override
    String sound() { return "Woof"; }

    String fetch() { return name + " fetches the ball"; }

    String name() { return name; }

    boolean isTrained() { return trained; }
}

class Cat extends Animal {
    @Override
    String sound() { return "Meow"; }
}
