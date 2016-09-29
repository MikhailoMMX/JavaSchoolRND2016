package ru.microbyte.strategy;

public class Client {
    public static void main(String[] args) {
        Developer developer = new Developer();

        developer.setActivity(new Sleeping());
        developer.doIt();

        developer.setActivity(new Working());
        developer.doIt();

        developer.setActivity(new Training());
        developer.doIt();

        developer.setActivity(new Sleeping());
        developer.doIt();
    }
}


interface Activity {
    void justDoIt();
}

class Sleeping implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Sleeping...");
    }
}
class Reading implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Reading...");
    }
}
class Working implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Working...");
    }
}
class Training implements Activity {
    @Override
    public void justDoIt() {
        System.out.println("Training...");
    }
}

class Developer {
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void doIt() {
        activity.justDoIt();
    }
}