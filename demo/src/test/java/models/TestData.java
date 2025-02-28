package models;

public record TestData(String duration, String method, String name, String startTime, String endTime, String status) {
    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        TestData data = (TestData)other;

        return data.duration.equals(this.duration)
            && data.method.equals(this.method)
            && data.name.equals(this.name)
            && data.startTime.equals(this.startTime)
            && ((data.endTime == null && this.endTime == null) || data.endTime.equals(this.endTime))
            && data.status.toUpperCase().equals(this.status.toUpperCase());
    }

    @Override
    public int hashCode() {
        return duration.hashCode()
            + method.hashCode()
            + name.hashCode()
            + startTime.hashCode()
            + endTime.hashCode()
            + status.hashCode();
    }
}
