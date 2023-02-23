package com.odinzsteven.springsandbox.Repository;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MutableUserRepo extends JpaRepository<MutableUser, Long> {

}
