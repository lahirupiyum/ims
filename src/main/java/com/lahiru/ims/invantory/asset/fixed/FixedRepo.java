package com.lahiru.ims.invantory.asset.fixed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedRepo extends JpaRepository<Fixed, Integer> {
}
