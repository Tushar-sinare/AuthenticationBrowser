package com.netwin.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="SystemDetails")
public class SystemInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String macAddress;
    private String productId;
    private String hardDiskId;
    private String ipAddress;
    private String hostName;
    // Other fields as needed
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemInfo other = (SystemInfo) obj;
		return Objects.equals(hardDiskId, other.hardDiskId) && Objects.equals(hostName, other.hostName)
				&& Objects.equals(id, other.id) && Objects.equals(ipAddress, other.ipAddress)
				&& Objects.equals(macAddress, other.macAddress) && Objects.equals(productId, other.productId);
	}
	@Override
	public int hashCode() {
		return Objects.hash(hardDiskId, hostName, id, ipAddress, macAddress, productId);
	}
    
    // Getters and setters
    
}