package jyx.model;

public enum PostType {
    group("圈子"),
    mood("运动动态");

    private String label;
    PostType(String string) {
        this.label = string;
    }

}
