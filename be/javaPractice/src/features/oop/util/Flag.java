package features.oop.util;

public enum Flag {
    STUDENT(1), TEACHER(2), MANAGER(3);

    private final int flag;

    private Flag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }
}
