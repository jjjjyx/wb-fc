package jyx.model;

public enum ActivityStatus {
    ready("准备阶段"),
    end("结束"),
    cancel("取消"),
    del("删除"), // 删除
    go("进行中"); // 进行中
    private String label;

    ActivityStatus(String status) {
        this.label = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
