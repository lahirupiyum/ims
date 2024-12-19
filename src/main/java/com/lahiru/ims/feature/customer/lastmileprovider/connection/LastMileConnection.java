package com.lahiru.ims.feature.customer.lastmileprovider.connection;

import com.lahiru.ims.feature.customer.lastmileprovider.media.LastMileMedia;
import com.lahiru.ims.feature.customer.lastmileprovider.provider.LastMileProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LastMileConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String switchPort;
    private String circuitId;
    private String bandwidth;

    @ManyToOne(fetch = FetchType.LAZY)
    private LastMileProvider lastMileProvider;
    @ManyToOne(fetch = FetchType.LAZY)
    private LastMileMedia media;
}
