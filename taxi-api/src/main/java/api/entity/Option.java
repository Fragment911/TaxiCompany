    package api.entity;

    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;

    @Entity
    public class Option {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String text;

        public Option() {
        }

        public Option(long id, String text) {
            this.id = id;
            this.text = text;
        }

        public long getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "Option{" +
                    "id=" + id +
                    ", text='" + text + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Option option = (Option) o;

            return text.equals(option.text);

        }

        @Override
        public int hashCode() {
            return text.hashCode();
        }
    }
