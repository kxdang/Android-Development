package com.example.kien.workout;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
            new Workout("The Loosener", "5 Handstand pushups\n10 1-legged squats\n15 Pull-ups"),
            new Workout("Core Agnoy", "100 Pull-ups\n100 Push-ups\n100 Sit-ups \n100 Squats"),
            new Workout("The Wimp Special","5 Pull-ups \n10 Push-ups \n Squats"),
            new Workout("Strength and Length", "500 meter run\n21 x 1.5 pood kettleball swing \n21 x pull-ups")
    };

    private Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return this.name;
    }
}
