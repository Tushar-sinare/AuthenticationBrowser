package com.netwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netwin.dto.SystemInfoDto;
import com.netwin.entity.SystemInfo;

public interface SystemInfoRepository extends JpaRepository<SystemInfo, Long> {

	SystemInfo findByHardDiskIdAndMacAddress(String hardDiskId, String macAddress);
    // Add custom queries if needed
}
