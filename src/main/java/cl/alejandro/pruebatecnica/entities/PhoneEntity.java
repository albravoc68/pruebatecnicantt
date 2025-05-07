package cl.alejandro.pruebatecnica.entities;

import cl.alejandro.pruebatecnica.dto.PhoneDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PHONES")
@Data
@NoArgsConstructor
public class PhoneEntity {

    public PhoneEntity(PhoneDTO dto) {
        this.phoneNumber = Integer.parseInt(dto.getNumber());
        this.cityCode = Integer.parseInt(dto.getCitycode());
        this.countryCode = Integer.parseInt(dto.getContrycode());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONES_GENERATOR")
    @SequenceGenerator(name = "PHONES_GENERATOR", sequenceName = "PHONES_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "PHONE_NUMBER")
    private Integer phoneNumber;

    @Column(name = "CITYCODE")
    private Integer cityCode;

    @Column(name = "COUNTRYCODE")
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity user;

}
