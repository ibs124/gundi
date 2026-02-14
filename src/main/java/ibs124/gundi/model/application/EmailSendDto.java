package ibs124.gundi.model.application;

import jakarta.validation.constraints.NotNull;

@NotNull
public record EmailSendDto(
        String from,
        String displayName,
        String[] to,
        String subject,
        String text,
        boolean isHtml) {

    public EmailSendDto {
        if (displayName == null || displayName.isBlank()) {
            displayName = "";
        }

        if (subject == null || subject.isBlank()) {
            subject = "";
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String from;
        private String displayName;
        private String[] to;
        private String subject;
        private String text;
        private boolean isHtml;

        private Builder() {
        }

        public EmailSendDto build() {
            return new EmailSendDto(
                    this.from,
                    this.displayName,
                    this.to,
                    this.subject,
                    this.text,
                    this.isHtml);
        }

        public Builder from(String from) {
            this.from = from;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder to(String... to) {
            this.to = to;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder isHtml(boolean isHtml) {
            this.isHtml = isHtml;
            return this;
        }

    }

}
