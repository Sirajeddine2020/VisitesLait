package tn.Sindibad.SVA_Back.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Enumerated(EnumType.STRING)
        @Column(length = 30)
        private ERole name;
        public Role() {
        }
        public Role(ERole name) {
            this.name = name;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public ERole getName() {
            return name;
        }
        public void setName(ERole name) {
            this.name = name;
        }
}
